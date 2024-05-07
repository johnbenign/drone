package com.benign.drone.service;

import com.benign.drone.dto.request.EditMedicationRequest;
import com.benign.drone.dto.request.MedicationRequest;
import com.benign.drone.dto.response.ResponseDto;

public interface MedicationService {
    ResponseDto save(MedicationRequest request);
    ResponseDto edit(Long id, EditMedicationRequest request);
    ResponseDto checkForLoadedMedication(Long droneId);
    ResponseDto getMedication(Long id);
    ResponseDto getAllMedication();
}
