# Projeto Sisvetor

> API para o sistema Sisvetor, projeto de avaliação de domínio básico no desenvolvimento de aplicações do Arbocontrol.

# Sobre o sistema
  O projeto ArboControl tem o objetivo de avaliar os sistemas de vigilância existentes
  para o contexto entomológico e de desenvolver um produto de software que permita
  identificar e monitorar situações em que o controle entomológico e vetorial seja necessário,
  gerando informação para tomada de decisões. Esse projeto está sendo desenvolvido pela
  Universidade de Brasília – UNB juntamente com o Ministério da Saúde

# Implementação
Para a implementação foi utilizado o framework Spring Boot (https://spring.io/projects/spring-boot) e 
o banco de dados banco de dados PostgreSQL (https://www.postgresql.org/). Destaque para a ferramenta de migração Flyway (https://flywaydb.org/documentation/plugins/springboot) para criação e controle e o Spring Data (https://spring.io/projects/spring-data) para acesso à base de dados.
As demais dependências encontram-se no arquivo de configuração pom.xml.

# Pré-Requisitos para deployment

1. No diretório /src/main/resources, crie uma cópia do arquivo **application.properties.example** com o nome **application.properties** e coloque as informações para conexão com um datasource;
2. No diretório raiz, crie uma cópia do arquivo **flyway.conf.example** com o nome **flyway.conf** e coloque as informações restantes para conexão com a base de dados.

# Deployment
> Ambiente de desenvolvimento

No diretório raiz execute: 
  - **mvnw spring-boot:run**, caso esteja utilizando SO windows ou 
  - **.\mvnw spring-boot:run**, caso esteja utilizando sistemas Unix.

# API
 Após subir o servidor, consulte os endpoints acessando o caminho: /swagger-ui/.
 
 Ex: http://localhost:8080/swagger-ui/
