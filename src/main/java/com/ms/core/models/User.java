package com.ms.core.models;

import com.ms.core.models.enums.AccessStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseEntity {

  private String name;
  @Email(message = "Invalid email format")
  private String email;
  private String countryCode;
  private String localNumber;
  @Enumerated(EnumType.STRING)
  private AccessStatus status;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  private Tenant tenant;

}
