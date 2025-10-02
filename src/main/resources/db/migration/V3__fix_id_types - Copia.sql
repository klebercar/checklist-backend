BEGIN;

-- 1) descubra todas as FKs que apontam para checklist_items(id)
CREATE TEMP TABLE _fk_to_recreate AS
SELECT
  c.conname,
  c.conrelid::regclass AS fk_table,
  a.attname           AS fk_column
FROM pg_constraint c
JOIN pg_attribute a
  ON a.attrelid = c.conrelid
 AND a.attnum  = ANY (c.conkey)
WHERE c.contype  = 'f'
  AND c.confrelid = 'public.checklist_items'::regclass;

-- 2) para cada FK encontrada: drop da FK + altera a coluna filha para BIGINT
DO $$
DECLARE r record;
BEGIN
  FOR r IN SELECT * FROM _fk_to_recreate LOOP
    EXECUTE format('ALTER TABLE %s DROP CONSTRAINT %I;', r.fk_table, r.conname);
    EXECUTE format('ALTER TABLE %s ALTER COLUMN %I TYPE BIGINT USING %I::bigint;',
                   r.fk_table, r.fk_column, r.fk_column);
  END LOOP;
END $$;

-- 3) altera o ID da tabela pai para BIGINT
ALTER TABLE public.checklist_items
  ALTER COLUMN id TYPE BIGINT;

-- 4) recria as FKs com o mesmo nome apontando para o novo BIGINT
DO $$
DECLARE r record;
BEGIN
  FOR r IN SELECT * FROM _fk_to_recreate LOOP
    EXECUTE format(
      'ALTER TABLE %s ADD CONSTRAINT %I FOREIGN KEY (%I) REFERENCES public.checklist_items(id);',
      r.fk_table, r.conname, r.fk_column
    );
  END LOOP;
END $$;

COMMIT;

-- conferência (opcional): deve retornar "bigint"
SELECT column_name, data_type
FROM information_schema.columns
WHERE table_schema='public' AND table_name='checklist_items' AND column_name='id';

