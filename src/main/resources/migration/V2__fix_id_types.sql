-- adiciona a coluna que a aplicação espera
ALTER TABLE public.photos
  ADD COLUMN IF NOT EXISTS s3key TEXT;  -- mude para VARCHAR(255) se preferir

-- se a sua entidade estiver com @Column(nullable = false),
-- então torne NOT NULL e dê um default (opcional):
-- UPDATE public.photos SET s3key = '' WHERE s3key IS NULL;
-- ALTER TABLE public.photos ALTER COLUMN s3key SET NOT NULL;
