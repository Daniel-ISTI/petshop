insert into TB_UNIDADE VALUES
(1, 'Cristovão', 'Rua Cristovão Colombo');

insert into TB_UNIDADE VALUES
(2, 'Centro', 'Rua Alberto Bins');

insert into TB_UNIDADE VALUES
(3, 'Zona Sul', 'Rua Mario Totta');

INSERT INTO TB_CLIENTE VALUES
(1, 'Fulano Silva', '111.222.333-44', TRUE, 1);

INSERT INTO TB_CLIENTE VALUES
(2, 'Ciclano Silva', '222.333.444-55', TRUE, 2);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (1, '2019-01-02', 'MAMIFERO', 'Pluto', 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (2, '2017-01-03', 'MAMIFERO', 'Pluto', 2);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (2, '2016-01-03', 'MAMIFERO', 'Rex', 2);