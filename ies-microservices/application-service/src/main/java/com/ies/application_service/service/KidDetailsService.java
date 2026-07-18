package com.ies.application_service.service;

import java.util.List;

import com.ies.application_service.dto.KidRequest;
import com.ies.application_service.dto.KidResponse;

public interface KidDetailsService {

    KidResponse addKid(
            KidRequest request,
            String token);

    List<KidResponse> getKids(Long applicationId);

}