package com.ies.citizen_service.service;

import java.util.List;

import com.ies.citizen_service.dto.KidRequest;
import com.ies.citizen_service.dto.KidResponse;

public interface KidService {

    KidResponse addKid(KidRequest request);

    List<KidResponse> getKids(Long applicationId);

}
