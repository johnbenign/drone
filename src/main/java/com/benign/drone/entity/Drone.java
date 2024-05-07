package com.benign.drone.entity;

import com.benign.drone.constants.ModelConstant;
import com.benign.drone.constants.StateConstant;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private ModelConstant model;

    private double weightLimit;
    private double batteryPercent;

    @Enumerated(EnumType.STRING)
    private StateConstant state;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
