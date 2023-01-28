# Person API

 ## API para gerenciamento de Pessoas
 ### Endpoints:
  - Criar pessoa.
  - Editar pessoa.
  - Consultar uma pessoa.
  - Listar pessoas.
  - Criar endereço para pessoa.
  - Listar endereços da pessoa.
  - Poder informar qual endereço é o principal da pessoa.
  
  
 ### Atributos
  - Nome
  - CPF
  - Data de nascimento
  - Endereço:
    - Logradouro
    - CEP
    - Número
    - Cidade
    
 ### Características
  - Respostas da API são em JSON
  - Banco de dados H2
  - Java 11
  - Spring Framework 2.7.8
  - Maven
  - Swagger UI

 ### Como testar
    - Clone o repositório para algum diretório da sua máquina
    - Abra o projeto utilizando o IntelijIDEA
    - Após a sincronização das dependencias do maven rode a Classe PersonalApprication
    - Teste os endpoints no link: 
    localhost:8080/swagger-ui.html