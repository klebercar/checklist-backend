-- adiciona a coluna que a aplicação espera
ALTER TABLE public.photos
  ADD COLUMN IF NOT EXISTS s3key TEXT;  -- mude para VARCHAR(255) se preferir

  -- ajuste o nome da sequência se o seu for diferente
ALTER TABLE public.checklist_items
  ALTER COLUMN id SET DEFAULT nextval('checklist_items_id_seq');

  -- adiciona a coluna que a aplicação espera
ALTER TABLE public.photos
  ADD COLUMN IF NOT EXISTS s3key TEXT;  -- mude para VARCHAR(255) se preferir

  -- se já houver dados em s3key, migre-os para s3_key antes
UPDATE public.photos
SET s3_key = COALESCE(s3_key, s3key)
WHERE s3key IS NOT NULL;

-- garanta o NOT NULL se sua entidade exige
ALTER TABLE public.photos
  ALTER COLUMN s3_key SET NOT NULL;

-- remova a coluna duplicada
ALTER TABLE public.photos
  DROP COLUMN IF EXISTS s3key;

-- se a sua entidade estiver com @Column(nullable = false),
-- então torne NOT NULL e dê um default (opcional):
-- UPDATE public.photos SET s3key = '' WHERE s3key IS NULL;
-- ALTER TABLE public.photos ALTER COLUMN s3key SET NOT NULL;
