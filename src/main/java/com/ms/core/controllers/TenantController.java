package com.ms.core.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.core.dtos.ApiResponse;
import com.ms.core.dtos.tenants.TenantCreateRequest;
import com.ms.core.services.tenants.CreateTenant;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tenants")
public class TenantController {

  @Autowired
  private CreateTenant createTenant;

  @PostMapping()
  public ResponseEntity<ApiResponse<UUID>> createTenant(@RequestBody @Valid TenantCreateRequest request) {
    UUID id = createTenant.execute(request);
    return ResponseEntity.ok(ApiResponse.success(id, HttpStatus.CREATED.value()));
  }

}
