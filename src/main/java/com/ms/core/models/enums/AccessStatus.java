package com.ms.core.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessStatus {
  PENDING("PENDING"),
  ACTIVE("ACTIVE"),
  EXPIRED("EXPIRED"),
  BLOCKED("BLOCKED"),
  ARCHIVED("ARCHIVED");

  private final String value;

  AccessStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static AccessStatus fromValue(String value) {
    for (AccessStatus status : AccessStatus.values()) {
      if (status.value.equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Invalid status: " + value);
  }
}
