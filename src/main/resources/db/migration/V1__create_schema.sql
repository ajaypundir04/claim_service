CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    CONSTRAINT uk_users_username UNIQUE (username),
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS claims (
    id UUID PRIMARY KEY,
    claim_number VARCHAR(50) NOT NULL,
    title VARCHAR(255),
    description TEXT,
    remarks TEXT,
    status VARCHAR(50),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id UUID,
    CONSTRAINT uk_claims_number UNIQUE (claim_number),
    CONSTRAINT fk_user_claims
    FOREIGN KEY(user_id)
    REFERENCES users(id)
);