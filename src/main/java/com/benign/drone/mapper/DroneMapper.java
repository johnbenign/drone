package com.benign.drone.mapper;

import com.benign.drone.dto.request.DroneRequest;
import com.benign.drone.dto.request.EditDroneRequest;
import com.benign.drone.dto.response.DroneDto;
import com.benign.drone.entity.Drone;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;

public class DroneMapper {

    private DroneMapper(){}
    public static Drone mapToEntity(DroneRequest dto) {
        Drone entity = new Drone();
        entity.setModel(dto.getModel());
        entity.setState(dto.getState());
        entity.setBatteryPercent(dto.getBatteryPercent());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setWeightLimit(dto.getWeightLimit());

        return entity;
    }

    public static Drone mapToEntity(EditDroneRequest dto, Drone entity) {
        if(dto.getModel() != null)
            entity.setModel(dto.getModel());
        if(dto.getState() != null)
            entity.setState(dto.getState());
        if(dto.getBatteryPercent() != null)
            entity.setBatteryPercent(dto.getBatteryPercent());
        if(!TextUtils.isEmpty(dto.getSerialNumber()))
            entity.setSerialNumber(dto.getSerialNumber());
        if(dto.getWeightLimit() != null)
            entity.setWeightLimit(dto.getWeightLimit());
        return entity;
    }

    public static DroneDto mapToDto(Drone entity) {
        DroneDto dto = new DroneDto();
        dto.setBatteryPercent(entity.getBatteryPercent());
        dto.setModel(entity.getModel());
        dto.setState(entity.getState());
        dto.setId(entity.getId());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setWeightLimit(entity.getWeightLimit());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}
