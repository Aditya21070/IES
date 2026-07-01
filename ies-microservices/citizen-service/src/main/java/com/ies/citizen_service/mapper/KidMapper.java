package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.KidResponse;
import com.ies.citizen_service.entity.Kid;

@Component
public class KidMapper {

    public KidResponse toResponse(Kid kid) {

        return KidResponse.builder()
                .id(kid.getId())
                .applicationId(kid.getApplication().getId())
                .kidName(kid.getKidName())
                .dob(kid.getDob())
                .gender(kid.getGender())
                .build();
    }
}
