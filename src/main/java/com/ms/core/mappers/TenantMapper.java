package com.ms.core.mappers;

import com.ms.core.dtos.tenants.TenantCreateRequest;
import com.ms.core.models.Tenant;

public class TenantMapper {
  
  public static Tenant toEntity(TenantCreateRequest request) {
    Tenant tenant = new Tenant(
      request.getName(),
      request.getEmail(),
      request.getCountryCode(),
      request.getLocalNumber(),
      request.getDomain(),
      request.getStatus()
    );

    return tenant;
  }
}
