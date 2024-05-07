package com.benign.drone.dto.response;

import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DroneDto {
    private Long id;
    private String serialNumber;
    private ModelConstant model;
    private double weightLimit;
    private double batteryPercent;
    private StateConstant state;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime modifiedAt;
}
