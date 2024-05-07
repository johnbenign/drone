package com.benign.drone.dao;

import com.benign.drone.entity.Drone;
import com.benign.drone.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findAllByDrone(Drone drone);
}
