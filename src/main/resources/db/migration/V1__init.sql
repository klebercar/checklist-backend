CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(120) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL
);
CREATE TABLE rooms (
  id SERIAL PRIMARY KEY,
  code VARCHAR(40) UNIQUE NOT NULL,
  description TEXT,
  floor INT
);
CREATE TABLE checklist_templates (
  id SERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL
);
CREATE TABLE template_items (
  id SERIAL PRIMARY KEY,
  template_id INT NOT NULL REFERENCES checklist_templates(id) ON DELETE CASCADE,
  label VARCHAR(200) NOT NULL,
  required BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE checklists (
  id SERIAL PRIMARY KEY,
  room_id INT NOT NULL REFERENCES rooms(id),
  template_id INT NOT NULL REFERENCES checklist_templates(id),
  housekeeper_id INT NOT NULL REFERENCES users(id),
  status VARCHAR(10) NOT NULL DEFAULT 'OPEN',
  started_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  finished_at TIMESTAMPTZ
);
CREATE TABLE checklist_items (
  id SERIAL PRIMARY KEY,
  checklist_id INT NOT NULL REFERENCES checklists(id) ON DELETE CASCADE,
  label VARCHAR(200) NOT NULL,
  required BOOLEAN NOT NULL DEFAULT TRUE,
  checked BOOLEAN NOT NULL DEFAULT FALSE,
  note TEXT
);
CREATE TABLE photos (
  id SERIAL PRIMARY KEY,
  checklist_id INT NOT NULL REFERENCES checklists(id) ON DELETE CASCADE,
  s3_key VARCHAR(255) NOT NULL,
  url TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
-- Admin inicial (senha: admin123) — troque após o primeiro login
INSERT INTO users (name,email,password_hash,role) VALUES
('Admin','admin@hotel.com','$2a$10$w99p7FZ2o2.fw0QzFqg8vea0S7i7h5FqJjE3O6yQ2N3tP3o0zK3c2','ADMIN');
