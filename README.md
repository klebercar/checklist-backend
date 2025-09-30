# checklist-backend

Spring Boot 3 (Java 21) + Supabase (Postgres + Storage).

## Rodando local
1. Copie `.env.example` para `.env` e preencha as variáveis.
2. Exporte as variáveis (ou use sua IDE):
   - DB_URL, DB_USER, DB_PASSWORD
   - JWT_SECRET
   - SUPABASE_URL, SUPABASE_SERVICE_KEY, SUPABASE_BUCKET
3. Rode:
```bash
mvn spring-boot:run
```

## Build JAR
```bash
mvn clean package
```

## Deploy Railway
- Crie o serviço a partir deste repo.
- Adicione as ENVs acima.
- Exponha a porta 8080.

## Endpoints
- POST `/api/auth/login` { email, password }
- POST `/api/auth/signup` (ADMIN) { name, email, password, role }
- GET/POST `/api/rooms` (ADMIN)
- GET/POST `/api/templates` (ADMIN)
- POST `/api/checklists` (ADMIN|HOUSEKEEPER)
- GET `/api/checklists/{id}`
- POST `/api/checklists/{id}/items/{itemId}/toggle`
- POST `/api/checklists/{id}/finish`
- POST `/api/photos/upload` (multipart)
