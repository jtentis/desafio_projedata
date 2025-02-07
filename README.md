# Sistema de funcionários

Este projeto é uma aplicação web que gerencia e exibe informações sobre funcionários. Ele permite realizar operações como agrupar funcionários por função, calcular salários, ordenar por nome e outros. A aplicação utiliza **Spring Boot** no back-end e **Tailwind CSS** no front-end. Desenvolvido para o teste técnico da projedata.

---

## Tecnologias usadas

- **Back-end**:
    - Spring Boot
    - Spring Security (para autenticação JWT)
    - Spring Data JPA
    - PostgreSQL
    - Docker (para hospedagem do banco de dados)
    - Swagger (para documentação de API)

- **Front-end**:
    - HTML
    - Tailwind CSS
    - JavaScript (Fetch API para comunicação com o back-end)

## Funcionalidades

- **Agrupar funcionários por função**: Agrupa os funcionários em categorias com base em sua função.
- **Listar funcionário mais velho**: Exibe o funcionário mais velho da lista.
- **Aniversariantes de outubro e dezembro**: Exibe os funcionários que fazem aniversário nesses meses.
- **Ordenação alfabética**: Ordena os funcionários por nome.
- **Aumento percentual de salário**: Aumenta o salário dos funcionários percentualmente.
- **Cálculo do total de salários**: Exibe a soma total de todos os salários.
- **Cálculo de salários mínimos**: Exibe quantos salários mínimos cada funcionário ganha.

## Como rodar localmente

### 2. Configuração do Back-End

- **Banco de Dados**: O projeto usa **PostgreSQL**. Para configurar o banco de dados, crie um banco com as credenciais necessárias.

  Para o PostgreSQL, você pode usar o seguinte comando para criar o banco de dados:

  ```sql
  CREATE DATABASE funcionarios;
  ```

- **Configuração do `application.properties`**: No arquivo `src/main/resources/application.properties`, configure as credenciais do banco de dados.

  Para PostgreSQL:

  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/projedata
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  spring.datasource.driver-class-name=org.postgresql.Driver
  
  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update
  ```

### 3. Rodando o Back-End

- Execute o **Spring Boot** com o comando:

  ```bash
  ./mvnw spring-boot:run
  ```

- O servidor estará rodando na URL `http://localhost:8080`.

### 4. Rodando o Front-End

- Navegue até a pasta `src/main/resources/static` e abra o arquivo **index.html** no navegador. O front-end irá consumir a API do back-end.

## Como usar a aplicação

1. **Acesse o Swagger**:
    - A documentação da API está disponível através do Swagger em `http://localhost:8080/swagger-ui.html`.

   ![Swagger Screenshot](/src/main/resources/assets/swagger.png)

2. **Acessando a Página de Funcionários**:
    - A página de funcionários é acessada ao abrir o arquivo **index.html**.
    - A tabela será preenchida dinamicamente com os dados dos funcionários após interações com os botões.

   ![Funcionários Table Screenshot](/src/main/resources/assets/diagrama.png)

3. **Banco de Dados**:
    - O banco de dados armazena as informações dos funcionários. O esquema contém a tabela `funcionarios` com os campos: `id`, `nome`, `funcao`, `salario`, `dataNascimento`.

   ![Funcionários Table Screenshot](/src/main/resources/assets/tabela.png)

---

## Endpoints
- **POST /auth/register**: Registrar novo usuário.
- **POST /auth/login**: Autenticar usuário.
- **POST /funcionarios**: Cria um novo funcionário.
- **GET /funcionarios**: Lista todos os funcionários.
- **DELETE /funcionarios**: Deleta um funcionário.
- **PUT /funcionarios/aumentar-salario**: Aumenta salario dos funcionarios por percentual.
- **GET /funcionarios/agrupar-por-funcao**: Agrupa os funcionários por função.
- **GET /funcionarios/mais-velho**: Retorna o funcionário mais velho.
- **GET /funcionarios/aniversariantes-out-dez**: Lista os aniversariantes de outubro e dezembro.
- **GET /funcionarios/ordenados**: Retorna a lista de funcionários ordenada alfabeticamente.
- **GET /funcionarios/total-salarios**: Retorna o total de salários dos funcionários.
- **GET /funcionarios/quantidade-salarios-minimos**: Retorna quantos salários mínimos cada funcionário possui.

## Uso do Docker

O projeto utiliza **Docker** para facilitar o gerenciamento do banco de dados PostgreSQL. O **docker-compose** foi configurado para levantar o banco de dados sem necessidade de instalação manual.

### Como rodar com Docker:

1. Certifique-se de que você tem **Docker** e **Docker Compose** instalados.
2. Na raiz do projeto, execute o comando:
```bash
docker-compose up -d
```
3. O PostgreSQL estará disponível na porta `5432`.

Para parar os containers:
```bash
docker-compose down
```

## Decisões de Implementação

- **Front-end em Tailwind + HTML**: Optei por usar HTML e Tailwind CSS, pois é uma abordagem simples e eficaz para um projeto pequeno. Tecnologias como React ou Vue seriam mais robustas, mas não eram essenciais para este escopo.
- **Spring Boot para escalabilidade**: Escolhi **Spring Boot** porque ele oferece uma estrutura modular e escalável, facilitando futuras expansões do projeto.
- **Docker para ambiente de desenvolvimento**: Utilizei **Docker** para facilitar a configuração do banco de dados e permitir desenvolvimento em qualquer máquina sem necessidade de instalação manual do PostgreSQL.
- **Swagger para documentação**: O **Swagger** foi integrado para facilitar a documentação da API, sendo útil para desenvolvedores back-end e front-end entenderem melhor as requisições e respostas disponíveis.
- **JWT para autenticação**: Optei por **JWT (JSON Web Token)** pois é uma solução segura e eficiente para autenticação de usuários, eliminando a necessidade de sessões no servidor.

---

## Próximos Passos

Caso o projeto continue sendo desenvolvido, algumas melhorias podem ser implementadas:

1. **Front-end com tecnologias modernas**:
    - Migrar o front-end para **React, Vue ou Angular**, tornando-o mais dinâmico e modular.
    - Implementar um sistema de **componentes reutilizáveis** para UI.

2. **Separação do projeto em módulos**:
    - Criar dois repositórios separados: **um para o back-end (API REST) e outro para o front-end**.
    - Isso permitiria melhor organização e escalabilidade do sistema.

3. **Melhoria na segurança**:
    - Implementar **Spring Security com roles de usuário** (ADMIN, USER) para restringir ações específicas.

4. **Testes automatizados**:
    - Adicionar testes unitários e de integração usando **JUnit e Mockito**.

5. **Deploy na nuvem**:
    - Utilizar **AWS, Heroku ou Railway** para hospedar o back-end e **Vercel ou Netlify** para o front-end.

6. **Adicionar variaveis de ambiente para maior segurança**
    - Atualmente, as configurações sensíveis, como credenciais do banco de dados e chaves secretas para JWT, estão armazenadas diretamente no código. Isso pode representar um risco de segurança caso o repositório seja público ou compartilhado. Seria interessante a criação de um .env para o projeto.

