package com.benign.drone.dto.request;

import com.benign.drone.constants.MessageConstant;
import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneRequest {
    @Size(max = 100, message = MessageConstant.SERIAL_NUMBER_TOO_LONG)
    @NotEmpty(message = MessageConstant.SERIAL_NUMBER_REQUIRED)
    private String serialNumber;

    @NotNull(message = MessageConstant.MODEL_REQUIRED)
    private ModelConstant model;

    @NotNull(message = MessageConstant.WEIGHT_LIMIT_REQUIRED)
    @Max(value = 500, message = MessageConstant.WEIGHT_LIMIT_EXCEEDED)
    private Double weightLimit;

    @NotNull(message = MessageConstant.BATTERY_PERCENT_REQUIRED)
    private Double batteryPercent;

    @NotNull(message = MessageConstant.DRONE_STATE_REQUIRED)
    private StateConstant state;
}
