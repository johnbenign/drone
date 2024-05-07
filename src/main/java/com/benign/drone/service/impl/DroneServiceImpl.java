package com.benign.drone.service.impl;

import com.benign.drone.constants.MessageConstant;
import com.benign.drone.constants.StateConstant;
import com.benign.drone.dao.MedicationRepository;
import com.benign.drone.dto.request.DroneLoadingRequest;
import com.benign.drone.dto.request.EditDroneRequest;
import com.benign.drone.dto.response.DroneDto;
import com.benign.drone.entity.Medication;
import com.benign.drone.exception.BadRequestException;
import com.benign.drone.mapper.DroneMapper;
import com.benign.drone.dao.DroneRepository;
import com.benign.drone.dto.request.DroneRequest;
import com.benign.drone.dto.response.ResponseDto;
import com.benign.drone.entity.Drone;
import com.benign.drone.exception.NotFoundException;
import com.benign.drone.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepository dao;
    private final MedicationRepository medicationRepository;

    @Override
    public ResponseDto save(DroneRequest request){
        Drone drone = DroneMapper.mapToEntity(request);
        drone.setState(StateConstant.IDLE);
        drone = dao.save(drone);
        return ResponseDto.builder()
                .statusCode(200)
                .message("Drone saved successfully")
                .data(DroneMapper.mapToDto(drone))
                .build();
    }

    @Override
    public ResponseDto edit(Long id, EditDroneRequest request){
        Drone drone = dao.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstant.DRONE_NOT_FOUND));
        drone = DroneMapper.mapToEntity(request, drone);
        drone = dao.save(drone);
        return ResponseDto.builder()
                .statusCode(200)
                .message("Drone edited successfully")
                .data(DroneMapper.mapToDto(drone))
                .build();
    }

    @Override
    public ResponseDto loadDrone(DroneLoadingRequest request) {
        Drone drone = dao.findById(request.getDroneId())
                .orElseThrow(() -> new NotFoundException("Drone not found with given id"));
        if(drone.getBatteryPercent() < 25)
            throw new BadRequestException("Drone is below 25%, it is unable to carry any load");
        List<StateConstant> busyStates = List.of(StateConstant.LOADED, StateConstant.LOADING, StateConstant.DELIVERING);
        if(busyStates.contains(drone.getState()))
            throw new BadRequestException("Drone is currently busy, unable to carry medications at the moment");
        List<Medication> medications = request.getMedicationIds()
                .stream()
                .map(medId -> medicationRepository.findById(medId).orElseThrow(() -> new NotFoundException("No medication found with this id: " + medId)))
                .collect(Collectors.toList());
        double totalMedicationWeight = medications.stream().mapToDouble(Medication::getWeight).sum();
        if(totalMedicationWeight > drone.getWeightLimit())
            throw new BadRequestException("Drone weight limit has been exceeded");
        drone.setState(StateConstant.LOADING);
        medications.forEach(medication -> {
            medication.setDrone(drone);
            medicationRepository.save(medication);
        });
        drone.setState(StateConstant.LOADED);
        DroneDto dto = DroneMapper.mapToDto(dao.save(drone));
        return ResponseDto.builder()
                .message("Drone loaded successfully")
                .statusCode(200)
                .data(dto)
                .build();
    }

    @Override
    public ResponseDto checkAvailableDrone(){
        List<StateConstant> availableDroneStates = List.of(StateConstant.IDLE, StateConstant.DELIVERED);
        List<Drone> availableDrones = dao.findAllByStateIn(availableDroneStates);
        List<DroneDto> availableDroneDtos = availableDrones.stream()
                .map(DroneMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .message("Successful")
                .statusCode(200)
                .data(availableDroneDtos)
                .build();
    }

    @Override
    public ResponseDto checkDroneBatteryLevel(Long droneId){
        Drone drone = dao.findById(droneId)
                .orElseThrow(() -> new NotFoundException("Drone not found with given id"));
        double batteryLevel = drone.getBatteryPercent();
        return ResponseDto.builder()
                .message("Drone battery level is " + batteryLevel)
                .statusCode(200)
                .data(batteryLevel)
                .build();
    }

    @Override
    public ResponseDto getDrone(Long id){
        Drone drone = dao.findById(id)
                .orElseThrow(() -> new NotFoundException("Drone not found with given id"));
        DroneDto dto = DroneMapper.mapToDto(drone);
        return ResponseDto.builder()
                .data(dto)
                .statusCode(HttpStatus.OK.value())
                .message("Successful")
                .build();
    }

    @Override
    public ResponseDto getAllDrones(){
        List<DroneDto> drones = dao.findAll()
                .stream()
                .map(DroneMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .data(drones)
                .statusCode(HttpStatus.OK.value())
                .message("Successful")
                .build();
    }
}
