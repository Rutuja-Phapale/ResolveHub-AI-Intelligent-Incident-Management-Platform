CREATE TABLE app_users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(30) NOT NULL,
    priority VARCHAR(30) NOT NULL,
    category VARCHAR(30) NOT NULL,
    created_by_user_id UUID NOT NULL,
    assigned_to_user_id UUID NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_tickets_created_by
        FOREIGN KEY (created_by_user_id)
        REFERENCES app_users(id),

    CONSTRAINT fk_tickets_assigned_to
        FOREIGN KEY (assigned_to_user_id)
        REFERENCES app_users(id)
);

CREATE INDEX idx_tickets_status ON tickets(status);
CREATE INDEX idx_tickets_priority ON tickets(priority);
CREATE INDEX idx_tickets_created_by_user_id ON tickets(created_by_user_id);
CREATE INDEX idx_tickets_created_at ON tickets(created_at);

INSERT INTO app_users (
    id,
    email,
    password_hash,
    role
) VALUES (
    '00000000-0000-0000-0000-000000000001',
    'demo@resolvehub.local',
    'TEMP_PASSWORD_NOT_FOR_LOGIN',
    'USER'
);