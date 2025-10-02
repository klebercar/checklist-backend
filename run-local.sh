#!/usr/bin/env bash
set -e

# Banco (Neon)
export DB_URL='jdbc:postgresql://ep-small-hill-ach72a17-pooler.sa-east-1.aws.neon.tech:5432/checklist-postgres?sslmode=require&preferQueryMode=simple'
export DB_USER='neondb_owner'
export DB_PASSWORD='npg_fpaoDWZTS9E3'

# CORS para o front local
export APP_CORS_ALLOWED_ORIGINS='http://localhost:5173'

# JWT (>= 32 chars em dev)
export JWT_SECRET='MSo5UBHgL9n20LZN+i+PZwPzL5Y5PRoQSqVjtJACV15jgmvjQ5NUJqn7yn4c8MMTve7Hp0Mj6BcXoY6EEiiMHA=='

# Porta opcional
# export PORT=8080

mvn -q -DskipTests spring-boot:run
