package com.ms.core.models;

import java.util.List;

import com.ms.core.models.enums.AccessStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tenants")
public class Tenant extends BaseEntity {

  private String name;
  private String slug;
  private String email;
  private String countryCode;
  private String localNumber;
  private String domain;
  @Enumerated(EnumType.STRING)
  private AccessStatus status;
  @OneToMany(mappedBy = "tenant")
  private List<User> users;

  public Tenant(
    String name,
    String email,
    String countryCode,
    String localNumber,
    String domain,
    AccessStatus status
  ) {
    this.name = name;
    this.slug = name.toLowerCase().replaceAll("\\s+", "_");
    this.email = email;
    this.countryCode = countryCode;
    this.localNumber = localNumber;
    this.domain = domain;
    this.status = status;
  }
}
