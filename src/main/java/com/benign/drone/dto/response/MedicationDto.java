package com.benign.drone.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class MedicationDto {
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime modifiedAt;
}
