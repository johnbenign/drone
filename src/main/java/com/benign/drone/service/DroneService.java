package com.benign.drone.service;

import com.benign.drone.dto.request.DroneLoadingRequest;
import com.benign.drone.dto.request.DroneRequest;
import com.benign.drone.dto.request.EditDroneRequest;
import com.benign.drone.dto.response.ResponseDto;

public interface DroneService {
    ResponseDto save(DroneRequest request);
    ResponseDto edit(Long id, EditDroneRequest request);
    ResponseDto loadDrone(DroneLoadingRequest request);
    ResponseDto checkAvailableDrone();
    ResponseDto checkDroneBatteryLevel(Long droneId);

    ResponseDto getDrone(Long id);

    ResponseDto getAllDrones();
}
