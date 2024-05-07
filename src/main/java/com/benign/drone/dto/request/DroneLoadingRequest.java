package com.benign.drone.dto.request;

import com.benign.drone.constants.MessageConstant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class DroneLoadingRequest {
    @NotNull(message = MessageConstant.DRONE_REQUIRED)
    private Long droneId;

    @NotEmpty(message = MessageConstant.MEDICATION_ID_REQUIRED)
    private Set<Long> medicationIds;
}
