CREATE TABLE app_health_check (
  id BIGSERIAL PRIMARY KEY,
  message VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
  );