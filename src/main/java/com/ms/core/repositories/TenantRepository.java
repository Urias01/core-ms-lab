package com.ms.core.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.core.models.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
  
  boolean existsBySlug(String slug);
}
