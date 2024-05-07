package com.benign.drone.dto.request;

import com.benign.drone.constants.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class EditMedicationRequest {
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = MessageConstant.INVALID_MEDICATION_NAME)
    private String name;
    private double weight;

    @Pattern(message = MessageConstant.INVALID_CODE, regexp = "^[A-Z0-9_]+$")
    private String code;

    //valid base64
    private String image;
}
