INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (1999999,'João Muller','2000-09-24','24720191061',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (2999999,'Asdrubal Videira Carlos da Silva','1975-09-24','13855837031',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (3999999,'Manuela Valverde','1995-11-04','75671962048',null);
INSERT INTO person (id_person, name, birth_date, cpf, address_id) VALUES (4999999,'Pamela Barbosa','2001-09-30','96555474009',null);


INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (1999999,'Rua São Paulo - Bairro Victor Konder', '89012030', 940, 'Blumenau - SC',1999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (2999999,'Rua Marina - Bairro Itoupava Norte', '89013456', 1258, 'Tubarão - SC',1999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (3999999,'Rua Oswaldo Hulse - Bairro Canasvieiras', '88054470', 10, 'Florianópolis - SC',1999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (4999999,'Rua Sansão Gomes - Bairro Aventureiro', '89225593', 98, 'Joinville - SC',2999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (5999999,'Rua do Sarandi - Bairro Monte Verde', '88032350', 66, 'Florianópolis - SC',2999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (6999999,'Servidão Jucélia Martins Leal - Bairro Lagoa da Conceição', '88062570', 1470, 'Florianópolis - SC',3999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (7999999,'Avenida Fernando Machado - D - Bairro Centro', '89803003', 77, 'Chapecó - SC',4999999);
INSERT INTO address (id_address, logradouro, cep, number, city_name, person_id) VALUES (8999999,'Rua General Ozório - Bairro Agua Verde', '89083103', 2441, 'Blumenau - SC',4999999);


UPDATE person SET address_id = 1999999 WHERE id_person = 1999999;
UPDATE person SET address_id = 4999999 WHERE id_person = 2999999;
UPDATE person SET address_id = 6999999 WHERE id_person = 3999999;