package com.benign.drone.dto.request;

import com.benign.drone.constants.MessageConstant;
import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
public class EditDroneRequest {
    @Size(max = 100, message = MessageConstant.SERIAL_NUMBER_TOO_LONG)
    private String serialNumber;
    private ModelConstant model;
    @Max(value = 500, message = MessageConstant.WEIGHT_LIMIT_EXCEEDED)
    private Double weightLimit;
    private Double batteryPercent;
    private StateConstant state;
}
