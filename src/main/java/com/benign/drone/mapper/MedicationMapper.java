package com.benign.drone.mapper;

import com.benign.drone.dto.request.EditMedicationRequest;
import com.benign.drone.dto.request.MedicationRequest;
import com.benign.drone.dto.response.MedicationDto;
import com.benign.drone.entity.Medication;
import org.apache.http.util.TextUtils;

public class MedicationMapper {
    public static Medication mapToEntity(MedicationRequest request) {
        Medication entity = new Medication();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setWeight(request.getWeight());
        return entity;
    }

    public static Medication mapToEntity(EditMedicationRequest request, Medication entity) {
        if(!TextUtils.isEmpty(request.getCode()))
            entity.setCode(request.getCode());
        if(!TextUtils.isEmpty(request.getName()))
            entity.setName(request.getName());
        if(!TextUtils.isEmpty(request.getImage()))
            entity.setImage(request.getImage());
        entity.setWeight(request.getWeight());
        return entity;
    }

    public static MedicationDto mapToDto(Medication entity) {
        MedicationDto dto = new MedicationDto();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setWeight(entity.getWeight());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setId(entity.getId());
        return dto;
    }
}
