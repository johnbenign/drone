package com.benign.drone.service.impl;

import com.benign.drone.dao.DroneRepository;
import com.benign.drone.dto.request.EditMedicationRequest;
import com.benign.drone.dto.response.MedicationDto;
import com.benign.drone.entity.Drone;
import com.benign.drone.exception.NotFoundException;
import com.benign.drone.dao.MedicationRepository;
import com.benign.drone.dto.request.MedicationRequest;
import com.benign.drone.dto.response.ResponseDto;
import com.benign.drone.entity.Medication;
import com.benign.drone.mapper.MedicationMapper;
import com.benign.drone.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository dao;
    private final DroneRepository droneRepository;

    @Override
    public ResponseDto save(MedicationRequest request){
        Medication medication = MedicationMapper.mapToEntity(request);
        medication = dao.save(medication);
        return ResponseDto.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Medication saved successfully")
                .data(MedicationMapper.mapToDto(medication))
                .build();
    }

    @Override
    public ResponseDto edit(Long id, EditMedicationRequest request) {
        Medication medication = dao.findById(id)
                .orElseThrow(() -> new NotFoundException("Medication not found with given id"));
        medication = MedicationMapper.mapToEntity(request, medication);
        medication = dao.save(medication);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Medication edited successfully")
                .data(MedicationMapper.mapToDto(medication))
                .build();
    }

    @Override
    public ResponseDto checkForLoadedMedication(Long droneId){
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new NotFoundException("Drone not found with given id"));
        List<Medication> medications = dao.findAllByDrone(drone);
        List<MedicationDto> dtos = medications.stream()
                .map(MedicationMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successful")
                .data(dtos)
                .build();
    }

    @Override
    public ResponseDto getMedication(Long id){
        Medication medication = dao.findById(id)
                .orElseThrow(() -> new NotFoundException("Medication not found with given id"));
        MedicationDto dto = MedicationMapper.mapToDto(medication);
        return ResponseDto.builder()
                .message("Successful")
                .statusCode(HttpStatus.OK.value())
                .data(dto)
                .build();
    }

    @Override
    public ResponseDto getAllMedication(){
        List<MedicationDto> medications = dao.findAll()
                .stream()
                .map(MedicationMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .data(medications)
                .message("Successful")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
