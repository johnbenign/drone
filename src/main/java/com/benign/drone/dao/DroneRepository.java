package com.benign.drone.dao;

import com.benign.drone.constants.StateConstant;
import com.benign.drone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findAllByStateIn(List<StateConstant> availableDroneStates);
}
