-- Converte IDs de várias tabelas para BIGINT e ajusta TODAS as FKs que apontam para elas
DO $$
DECLARE
  parent regclass;
  fk RECORD;
BEGIN
  -- <<< LISTA DE TABELAS CUJO id VAI VIRAR BIGINT >>>
  FOREACH parent IN ARRAY ARRAY[
    'public.checklist_templates'::regclass,
    'public.template_items'::regclass,
    'public.rooms'::regclass,
    'public.checklists'::regclass,
    'public.users'::regclass,
    'public.photos'::regclass
  ]
  LOOP
    -- guarda as FKs que APONTAM para parent(id) antes de derrubar
    CREATE TEMP TABLE _fks_to_recreate ON COMMIT DROP AS
    SELECT
      c.conname,
      c.conrelid::regclass AS fk_table,
      a.attname            AS fk_column
    FROM pg_constraint c
    JOIN pg_attribute a
      ON a.attrelid = c.conrelid
     AND a.attnum  = ANY (c.conkey)
    WHERE c.contype  = 'f'
      AND c.confrelid = parent;

    -- 1) drop das FKs + altera cada coluna filha para BIGINT
    FOR fk IN SELECT * FROM _fks_to_recreate LOOP
      EXECUTE format('ALTER TABLE %s DROP CONSTRAINT %I;', fk.fk_table, fk.conname);
      EXECUTE format('ALTER TABLE %s ALTER COLUMN %I TYPE BIGINT USING %I::bigint;',
                     fk.fk_table, fk.fk_column, fk.fk_column);
    END LOOP;

    -- 2) altera o id da tabela pai para BIGINT (se já for BIGINT, não tem problema)
    EXECUTE format('ALTER TABLE %s ALTER COLUMN id TYPE BIGINT;', parent);

    -- 3) recria as FKs exatamente com o mesmo nome
    FOR fk IN SELECT * FROM _fks_to_recreate LOOP
      EXECUTE format(
        'ALTER TABLE %s ADD CONSTRAINT %I FOREIGN KEY (%I) REFERENCES %s(id);',
        fk.fk_table, fk.conname, fk.fk_column, parent
      );
    END LOOP;

    DROP TABLE _fks_to_recreate;
  END LOOP;
END $$;

