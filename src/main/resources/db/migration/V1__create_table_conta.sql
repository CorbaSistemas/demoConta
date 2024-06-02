CREATE TABLE conta (
    id SERIAL PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DOUBLE PRECISION NOT NULL,
    descricao VARCHAR(255),
    situacao VARCHAR(50)
);