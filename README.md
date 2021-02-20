# Backend

### Stack
- Java versão 11
- Spring Boot versão 2.3.9.RELEASE
- Spring Data
- Flyway migrations
- Swagger UI
- MySQL Imagem Latest
- JWT Token

### Setup
- Instalar docker e docker-compose
- Entre na pasta do projeto
- Importar os arquivos do postman na pasta postman (coleção e variáveis) poupachef.postman_collection.json e poupachef.postman_environment.json
- Digite o comando docker-compose up -d
- Digite o comando ./mvnw clean install

### Como executar
- Digite o comando ./mvnw spring-boot:run

### Documentação
- Swagger UI http://localhost:8080/swagger-ui.html

### Testes dos endpoints
- No postman, selecione a variáveis poupachef
- Execute os endpoints

- login ``gerar o token, o mesmo irá setar o valor na variável token para ir no header Authorization``
- create ``criar o produto``
- get by id ``verificar o produto criado``
- update ``atualizar o produto com o name, price e supplierId``
- get by id ``verificar o produto atualizado``
- add ``adicionar produtos ao estoque``
- subtract ``subtrair produtos ao estoque``
- get all ``verificar todos os produtos cadastrados``
- delete ``deletar o produto``
- get all ``verificar todos os produtos cadastrados``

### TODO
- Mais unit tests
- Logs
- Testes de integração
- BDD com cucumber
- Sonar
