CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario_base NUMERIC(10, 2) NOT NULL,
    senha TEXT NOT NULL
);

CREATE TABLE folhas_pagamento (
    id SERIAL PRIMARY KEY,
    funcionario_id INTEGER NOT NULL REFERENCES funcionarios(id) ON DELETE CASCADE,
    mes INTEGER NOT NULL CHECK (mes >= 1 AND mes <= 12),
    ano INTEGER NOT NULL,
    proventos NUMERIC(10, 2) DEFAULT 0,
    descontos NUMERIC(10, 2) DEFAULT 0,
    irrf NUMERIC(10, 2) DEFAULT 0,
    salario_liquido NUMERIC(10, 2) DEFAULT 0
);

CREATE OR REPLACE FUNCTION calcula_irrf(salario_base NUMERIC, outros_proventos NUMERIC)
RETURNS NUMERIC AS $$
DECLARE
    base_calculo DECIMAL;
    imposto DECIMAL := 0;
BEGIN
    base_calculo := salario + proventos;
    
    IF base_calculo <= 1903.98 THEN
        imposto := 0;
    ELSIF base_calculo <= 2826.65 THEN
        imposto := (base_calculo * 0.075) - 142.80;
    ELSIF base_calculo <= 3751.05 THEN
        imposto := (base_calculo * 0.15) - 354.80;
    ELSIF base_calculo <= 4664.68 THEN
        imposto := (base_calculo * 0.225) - 636.13;
    ELSE
        imposto := (base_calculo * 0.275) - 869.36;
    END IF;
    
    RETURN GREATEST(imposto, 0);
END;
$$ LANGUAGE plpgsql;
