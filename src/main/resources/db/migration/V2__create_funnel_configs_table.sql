CREATE TABLE IF NOT EXISTS funnel_configs (
     slug VARCHAR(100) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    config_json JSONB NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO funnel_configs (slug, name, config_json) VALUES (
 'default-claim-funnel',
 'Standard Claim Process',
 '{
   "title": "Insurance Claim Portal",
   "steps": [
     { "id": "step_user", "type": "select", "name": "userId", "label": "Who is filing this claim?", "description": "Please select a registered user." },
     { "id": "step_title", "type": "text", "name": "title", "label": "Claim Subject", "placeholder": "e.g., Medical Emergency" },
     { "id": "step_desc", "type": "textarea", "name": "description", "label": "Incident Details", "placeholder": "Provide as much detail as possible..." }
   ]
 }'
                                                            );