package com.benign.drone;

import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import com.benign.drone.dto.request.DroneRequest;
import com.benign.drone.dto.request.EditDroneRequest;
import com.benign.drone.dto.response.ResponseDto;
import com.benign.drone.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DroneTest {

    @Autowired
    private DroneService service;

    @Test
    void createNewDrone(){
        DroneRequest request = new DroneRequest();
        request.setBatteryPercent(100.0);
        request.setState(StateConstant.IDLE);
        request.setModel(ModelConstant.LIGHT_WEIGHT);
        request.setWeightLimit(300.0);
        request.setSerialNumber("1234567899");
        ResponseDto response = service.save(request);
        log.info(""+response);
    }

    @Test
    void editDrone(){
        EditDroneRequest request = new EditDroneRequest();
        request.setState(StateConstant.LOADING);
        ResponseDto response = service.edit(1L, request);
        log.info(""+response);
    }
}
