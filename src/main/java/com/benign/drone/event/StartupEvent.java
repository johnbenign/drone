package com.benign.drone.event;

import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import com.benign.drone.dto.request.DroneRequest;
import com.benign.drone.dto.request.MedicationRequest;
import com.benign.drone.service.DroneService;
import com.benign.drone.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartupEvent {

    @Autowired
    private DroneService droneService;

    @Autowired
    private MedicationService medicationService;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup(){
        log.info(" --- Server startup, populating db --- ");
        populateDbWithTestData();
    }

    private void populateDbWithTestData() {
        DroneRequest droneRequest = createDroneRequest();
        droneService.save(droneRequest);
        MedicationRequest medicationRequest = createMedicationRequest();
        medicationService.save(medicationRequest);
    }

    private MedicationRequest createMedicationRequest() {
        MedicationRequest request = new MedicationRequest();
        request.setName("Tissue");
        request.setWeight(100.9);
        request.setCode("T150");
        return request;
    }

    private DroneRequest createDroneRequest() {
        DroneRequest request = new DroneRequest();
        request.setSerialNumber("1234567899");
        request.setModel(ModelConstant.LIGHT_WEIGHT);
        request.setState(StateConstant.IDLE);
        request.setWeightLimit(500.0);
        request.setBatteryPercent(100.0);
        return request;
    }
}
