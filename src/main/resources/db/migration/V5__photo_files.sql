CREATE TABLE IF NOT EXISTS photo_files (
    id           BIGSERIAL PRIMARY KEY,
    checklist_id BIGINT NOT NULL,           -- se quiser relacionar
    filename     TEXT NOT NULL,
    content_type TEXT NOT NULL,
    size_bytes   BIGINT NOT NULL,
    data         BYTEA NOT NULL,
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Índices úteis
CREATE INDEX IF NOT EXISTS idx_photo_files_checklist_id ON photo_files(checklist_id);
CREATE INDEX IF NOT EXISTS idx_photo_files_created_at   ON photo_files(created_at);
