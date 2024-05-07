package com.benign.drone.dto.request;

import com.benign.drone.constants.MessageConstant;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicationRequest {
    @NotEmpty(message = MessageConstant.NAME_REQUIRED)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = MessageConstant.INVALID_MEDICATION_NAME)
    private String name;
    private double weight;

    @NotEmpty(message = MessageConstant.CODE_REQUIRED)
    @Pattern(message = MessageConstant.INVALID_CODE, regexp = "^[A-Z0-9_]+$")
    private String code;

    //valid base64
    private String image;
}
