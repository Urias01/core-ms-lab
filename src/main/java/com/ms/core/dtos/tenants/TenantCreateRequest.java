package com.ms.core.dtos.tenants;

import com.ms.core.models.enums.AccessStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantCreateRequest {
  @NotBlank(message = "Name is required")
  private String name;
  @Email(message = "Invalid email format")
  @NotBlank(message = "Email is required")
  private String email;
  private String countryCode;
  private String localNumber;
   @NotBlank(message = "Domain is required")
  private String domain;
  private AccessStatus status;
}
