package com.ies.application_service.service.security;

import com.ies.application_service.entity.Application;

public interface ApplicationSecurityService {

    Application validateOwnership(
            Long applicationId,
            String token);

}