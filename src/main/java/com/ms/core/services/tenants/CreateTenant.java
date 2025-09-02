package com.ms.core.services.tenants;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.core.dtos.tenants.TenantCreateRequest;
import com.ms.core.exceptions.SlugAlreadyExistsException;
import com.ms.core.mappers.TenantMapper;
import com.ms.core.models.Tenant;
import com.ms.core.repositories.TenantRepository;

@Service
public class CreateTenant {

  @Autowired
  private TenantRepository tenantRepository;

  public UUID execute(TenantCreateRequest request) {
    Tenant tenant = TenantMapper.toEntity(request);

    if (tenantRepository.existsBySlug(tenant.getSlug())) {
      throw new SlugAlreadyExistsException(tenant.getSlug());
    }

    tenantRepository.save(tenant);

    return tenant.getId();
  }
}
