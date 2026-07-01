package com.ies.citizen_service.mapper;

import org.springframework.stereotype.Component;

import com.ies.citizen_service.dto.CitizenRequest;
import com.ies.citizen_service.dto.CitizenResponse;
import com.ies.citizen_service.entity.Citizen;

@Component
public class CitizenMapper {

    public Citizen toEntity(CitizenRequest request) {

        return Citizen.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .portalAccess(request.getPortalAccess())
                .dob(request.getDob())
                .gender(request.getGender())
                .aadhaarNumber(request.getAadhaarNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .build();
    }

    public CitizenResponse toResponse(Citizen citizen) {

        return CitizenResponse.builder()
                .id(citizen.getId())
                .citizenNumber(citizen.getCitizenNumber())
                .fullName(citizen.getFullName())
                .email(citizen.getEmail())
                .phoneNumber(citizen.getPhoneNumber())
                .portalAccess(citizen.getPortalAccess())
                .dob(citizen.getDob())
                .gender(citizen.getGender())
                .aadhaarNumber(citizen.getAadhaarNumber())
                .address(citizen.getAddress())
                .city(citizen.getCity())
                .state(citizen.getState())
                .pincode(citizen.getPincode())
                .createdAt(citizen.getCreatedAt())
                .build();
    }
}
