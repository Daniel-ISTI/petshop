CREATE TABLE TB_ANIMAL (
CODIGO BIGINT GENERATED BY DEFAULT AS IDENTITY,
ID_CLIENTE BIGINT,
DATA_NASCIMENTO DATE,
ESPECIE VARCHAR2(10),
NOME VARCHAR2(100),
ID_UNIDADE BIGINT,
PRIMARY KEY (CODIGO)
);

CREATE TABLE TB_CLIENTE (
 ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
 NOME_CLIENTE VARCHAR2(100),
 CPF_CLIENTE VARCHAR2(14),
 INADIMPLENTE BOOLEAN,
 ID_UNIDADE BIGINT,
PRIMARY KEY (ID)
);

CREATE TABLE TB_UNIDADE (
 ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
 NOME VARCHAR2(100),
 ENDERECO VARCHAR2(300),
PRIMARY KEY (ID)
);

CREATE TABLE TB_PRODUTO (
 ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
 VALOR NUMBER(8,2),
 DESCRICAO VARCHAR2(100),
 ID_ANIMAL BIGINT,
PRIMARY KEY (ID)
);

ALTER TABLE TB_PRODUTO ADD CONSTRAINT FK_PRODUTO_ANIMAL
FOREIGN KEY (ID_ANIMAL) REFERENCES TB_ANIMAL;

ALTER TABLE TB_ANIMAL ADD CONSTRAINT FK_ANIMAL_UNIDADE
FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE;

ALTER TABLE TB_CLIENTE ADD CONSTRAINT FK_CLIENTE_UNIDADE
FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE;