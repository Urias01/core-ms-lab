CREATE TABLE
  tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    country_code VARCHAR(10),
    local_number VARCHAR(20),
    domain VARCHAR(100),
    status VARCHAR(20),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
  );

CREATE TABLE
  users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    country_code VARCHAR(10),
    local_number VARCHAR(20),
    status VARCHAR(20),
    tenant_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (tenant_id) REFERENCES tenants (id)
  );