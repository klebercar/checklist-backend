-- Converte o ID da tabela para BIGINT
ALTER TABLE checklist_items
  ALTER COLUMN id TYPE BIGINT;

-- Se existir FK para checklist_items.id em outra tabela, altere também a coluna FK:
-- Exemplos (ajuste os nomes conforme o seu esquema):
-- ALTER TABLE photos ALTER COLUMN checklist_item_id TYPE BIGINT;
-- ALTER TABLE checklist_items_photos ALTER COLUMN checklist_item_id TYPE BIGINT;
