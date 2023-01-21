CREATE TABLE address
(
    id_address  IDENTITY NOT NULL PRIMARY KEY,
    logradouro  VARCHAR(250) NOT NULL,
    cep         VARCHAR(20) NOT NULL,
    number      INT NOT NULL,
    city_name   VARCHAR(50) NOT NULL,
    person_id   INT NOT NULL
);


CREATE TABLE person
(
    id_person   IDENTITY NOT NULL PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    birth_date  DATE NOT NULL,
    cpf         VARCHAR(20) NOT NULL,
    address_id  INT,

        CONSTRAINT FK_PERSON
        FOREIGN KEY (address_id)
        REFERENCES address(id_address)
);

ALTER TABLE address ADD CONSTRAINT FK_ADDRESS FOREIGN KEY (person_id) REFERENCES person(id_person);


INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (1,'João Muller','2000-09-24','24720191061',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (2,'Asdrubal Videira Carlos da Silva','1975-09-24','13855837031',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (3,'Manuela Valverde','1995-11-04','75671962048',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (4,'Pamela Barbosa','2001-09-30','96555474009',null);


INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (1,'Rua São Paulo - Bairro Victor Konder', '89012030', 940, 'Blumenau - SC',1);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (2,'Rua Marina - Bairro Itoupava Norte', '89013456', 1258, 'Tubarão - SC',1);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (3,'Rua Oswaldo Hulse - Bairro Canasvieiras', '88054470', 10, 'Florianópolis - SC',1);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (4,'Rua Sansão Gomes - Bairro Aventureiro', '89225593', 98, 'Joinville - SC',2);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (5,'Rua do Sarandi - Bairro Monte Verde', '88032350', 66, 'Florianópolis - SC',2);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (6,'Servidão Jucélia Martins Leal - Bairro Lagoa da Conceição', '88062570', 1470, 'Florianópolis - SC',3);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (7,'Avenida Fernando Machado - D - Bairro Centro', '89803003', 77, 'Chapecó - SC',4);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (8,'Rua General Ozório - Bairro Agua Verde', '89083103', 2441, 'Blumenau - SC',4);


UPDATE person SET address_id = 1 WHERE id_person = 1;
UPDATE person SET address_id = 4 WHERE id_person = 2;
UPDATE person SET address_id = 6 WHERE id_person = 3;