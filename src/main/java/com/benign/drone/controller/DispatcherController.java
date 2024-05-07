package com.benign.drone.controller;

import com.benign.drone.constants.ApiRoute;
import com.benign.drone.dto.request.*;
import com.benign.drone.dto.response.ResponseDto;
import com.benign.drone.service.DroneService;
import com.benign.drone.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DispatcherController {

    private final DroneService droneService;
    private final MedicationService medicationService;

    @PostMapping(value = ApiRoute.DRONE)
    public ResponseEntity<ResponseDto> registerDrone(@RequestBody @Valid @NotNull DroneRequest request){
        ResponseDto response = droneService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = ApiRoute.DRONE + "/{id}")
    public ResponseEntity<ResponseDto> editDrone(@PathVariable Long id, @RequestBody @Valid @NotNull EditDroneRequest request){
        ResponseDto response = droneService.edit(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = ApiRoute.MEDICATION + "/{id}")
    public ResponseEntity<ResponseDto> editMedication(@PathVariable Long id, @RequestBody @Valid @NotNull EditMedicationRequest request){
        ResponseDto response = medicationService.edit(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.DRONE + ApiRoute.LOAD)
    public ResponseEntity<ResponseDto> loadDrone(@RequestBody @Valid @NotNull DroneLoadingRequest request){
        ResponseDto response = droneService.loadDrone(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.MEDICATION + ApiRoute.CHECK_LOADED_MEDICATION)
    public ResponseEntity<ResponseDto> checkLoadedMedications(Long droneId){
        ResponseDto response = medicationService.checkForLoadedMedication(droneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.DRONE + ApiRoute.CHECK_AVAILABLE_DRONE)
    public ResponseEntity<ResponseDto> checkAvailableDrones(){
        ResponseDto response = droneService.checkAvailableDrone();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.MEDICATION)
    public ResponseEntity<ResponseDto> createMedications(@RequestBody @Valid @NotNull MedicationRequest request){
        ResponseDto response = medicationService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = ApiRoute.DRONE + ApiRoute.CHECK_BATTERY_LEVEL + "/{droneId}")
    public ResponseEntity<ResponseDto> checkBatteryLevel(@PathVariable Long droneId){
        ResponseDto response = droneService.checkDroneBatteryLevel(droneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.DRONE + "/{droneId}")
    public ResponseEntity<ResponseDto> getDrone(@PathVariable Long droneId){
        ResponseDto response = droneService.getDrone(droneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.DRONE)
    public ResponseEntity<ResponseDto> getAllDrones(){
        ResponseDto response = droneService.getAllDrones();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.MEDICATION + "/{medicationId}")
    public ResponseEntity<ResponseDto> getMedication(@PathVariable Long medicationId){
        ResponseDto response = medicationService.getMedication(medicationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ApiRoute.MEDICATION)
    public ResponseEntity<ResponseDto> getAllMedications(){
        ResponseDto response = medicationService.getAllMedication();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
