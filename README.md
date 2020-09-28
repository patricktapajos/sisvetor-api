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
o banco de dados banco de dados PostgreSQL (https://www.postgresql.org/). Destaque para a utilização do JWT (https://jwt.io/) para incrmentar a segurança das requisições do cliente, a ferramenta de migração Flyway (https://flywaydb.org/documentation/plugins/springboot) para criação e controle da base e o Spring Data (https://spring.io/projects/spring-data) para acesso à base de dados.
As demais dependências encontram-se no arquivo de configuração pom.xml.

# Deployment
Vá ao diretório raiz e execute: 
  - **mvnw spring-boot:run**, caso esteja utilizando SO windows ou 
  - **.\mvnw spring-boot:run**, caso esteja utilizando sistemas Unix.
