# Academia API

API REST desenvolvida com **Java e Spring Boot** para simular o sistema backend de uma academia.

O projeto foi desenvolvido com foco em **desenvolvimento de APIs, persistência de dados, autenticação e autorização**, aplicando conceitos utilizados no desenvolvimento backend profissional.

---

## Tecnologias

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Bean Validation

---

## Funcionalidades

### Usuários e autenticação

* Cadastro de usuários
* Login
* Autenticação utilizando JWT
* Senhas protegidas com BCrypt
* Controle de acesso baseado em roles
* Roles `ALUNO` e `ADMIN`
* Autorização utilizando `@PreAuthorize`

### Academia

* Cadastro e gerenciamento de alunos
* Cadastro de exercícios
* Criação de treinos
* Associação de exercícios aos treinos
* Avaliação física dos alunos
* Consulta de avaliações físicas
* Paginação de resultados

### Persistência

O projeto utiliza **MySQL + Spring Data JPA/Hibernate**, com relacionamentos entre as principais entidades do sistema.

Também foram utilizados:

* Queries JPQL
* Native Queries
* Projections
* `JOIN FETCH`
* Lazy Loading
* Paginação

---

## Segurança

A API utiliza **Spring Security + JWT** para proteger os endpoints.

Fluxo de autenticação:

```text
Cadastro
   ↓
Login
   ↓
JWT
   ↓
Bearer Token
   ↓
JwtAuthenticationFilter
   ↓
Spring Security
   ↓
Endpoint protegido
```

O acesso aos recursos é controlado de acordo com o usuário autenticado e sua role.

Exemplo:

```java
@PreAuthorize("#alunoId == authentication.principal.id or hasRole('ADMIN')")
```

Com isso, um aluno pode acessar apenas seus próprios dados, enquanto usuários com a role `ADMIN` possuem permissões adicionais.

---

## Arquitetura

O projeto segue uma arquitetura organizada em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

| Camada | Responsabilidade |
| --- | --- |
| `controller` | Recebe as requisições HTTP e devolve as respostas |
| `service` | Concentra as regras de negócio |
| `repository` | Acesso ao banco de dados com Spring Data JPA |
| `model` | Entidades JPA que representam as tabelas |
| `dto` | Objetos de entrada e saída da API |
| `config` | Configuração de segurança e JWT |
| `handler` / `exceptions` | Tratamento global de erros e exceções customizadas |

---

## Estrutura do projeto

```text
spring-boot-essentials
└── src
    └── main
        ├── java
        │   └── br/com/igor/spring_boot_essentials
        │       ├── config
        │       │   ├── JwtAuthenticationFilter
        │       │   ├── SecurityConfiguration
        │       │   └── TokenProvider
        │       ├── controller
        │       │   ├── AlunoController
        │       │   ├── AuthController
        │       │   ├── AvaliacoesFisicasController
        │       │   ├── ExercicioController
        │       │   └── TreinosController
        │       ├── dto
        │       │   ├── AlunoDto
        │       │   ├── AvaliacaoFisicaDTO
        │       │   ├── AvaliacoesFisicasProjection
        │       │   ├── ExercicioDto
        │       │   ├── LoginRequestDto
        │       │   ├── RegisterRequestDto
        │       │   ├── TokenResponseDto
        │       │   └── TreinoDto
        │       ├── enums
        │       │   └── RoleTypeEnum
        │       ├── exceptions
        │       │   ├── BadRequestException
        │       │   ├── ErrorResponse
        │       │   └── NotFoundException
        │       ├── handler
        │       │   └── GlobalExceptionHandler
        │       ├── model
        │       │   ├── AlunosEntity
        │       │   ├── AvaliacoesFisicasEntity
        │       │   ├── ExercicioEntity
        │       │   ├── RolesEntity
        │       │   └── TreinosEntity
        │       ├── repository
        │       │   ├── IAlunosRepository
        │       │   ├── IAvaliacoesFisicasRepository
        │       │   ├── IExercicioRepository
        │       │   ├── IRolesRepository
        │       │   └── ITreinosRepository
        │       ├── service
        │       │   ├── AlunoService
        │       │   ├── AuthenticationService
        │       │   ├── AvaliacaoService
        │       │   ├── ExercicioService
        │       │   ├── TreinoService
        │       │   └── UserDetailsServiceImpl
        │       └── SpringBootEssentialsApplication
        └── resources
            └── application.properties
```

---

## Relacionamentos

O projeto trabalha com diferentes relacionamentos entre entidades.

Exemplo simplificado:

```text
                 ┌──────────────┐
                 │    ALUNO     │
                 └──────┬───────┘
                        │
              ┌─────────┴─────────┐
              │                   │
              ▼                   ▼
      ┌──────────────┐     ┌──────────────┐
      │  AVALIAÇÃO   │     │    TREINO    │
      │    FÍSICA    │     └──────┬───────┘
      └──────────────┘            │
                                  ▼
                         ┌────────────────┐
                         │   EXERCÍCIOS   │
                         └────────────────┘
```

| Relacionamento | Tipo | Carregamento |
| --- | --- | --- |
| Aluno → Avaliação física | `@OneToOne` | `LAZY` |
| Aluno → Treinos | `@OneToMany` | `LAZY` |
| Aluno ↔ Roles | `@ManyToMany` | `EAGER` |

Além disso, foram utilizados DTOs, entidades, tratamento de exceções e validações para separar responsabilidades e manter o código organizado.

---

## Banco de dados

O projeto utiliza **MySQL**.

Banco utilizado durante o desenvolvimento:

```text
gym_security
```

A conexão é configurada através de variáveis de ambiente:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/${DB_NAME}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

> As credenciais não são armazenadas diretamente no código-fonte.

---

## Como executar

### Pré-requisitos

* Java 17
* Maven (ou o wrapper `mvnw` incluso no projeto)
* MySQL em execução

### Passo a passo

1. Clone o repositório:

```bash
git clone https://github.com/<seu-usuario>/spring-boot-essentials.git
cd spring-boot-essentials
```

2. Crie o banco de dados no MySQL:

```sql
CREATE DATABASE gym_security;
```

3. Defina as variáveis de ambiente:

| Variável | Descrição | Exemplo |
| --- | --- | --- |
| `DB_NAME` | Nome do banco de dados | `gym_security` |
| `DATABASE_USERNAME` | Usuário do MySQL | `root` |
| `DATABASE_PASSWORD` | Senha do MySQL | `sua_senha` |

Linux/macOS:

```bash
export DB_NAME=gym_security
export DATABASE_USERNAME=root
export DATABASE_PASSWORD=sua_senha
```

Windows (PowerShell):

```powershell
$env:DB_NAME="gym_security"
$env:DATABASE_USERNAME="root"
$env:DATABASE_PASSWORD="sua_senha"
```

> Pelo IntelliJ, também é possível configurar em **Run → Edit Configurations → Environment variables**.

4. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

---

## Exemplo de uso

Login para obter o token JWT:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "aluno@email.com", "senha": "123456"}'
```

Consulta da avaliação física de um aluno, usando o token:

```bash
curl http://localhost:8080/v1/alunos/1/avaliacao \
  -H "Authorization: Bearer <seu_token>"
```

Resposta (`200 OK`):

```json
{
  "id": 1,
  "peso": 70.00,
  "altura": 1.67,
  "porcentagemDeGorduraCorporal": 11.00
}
```

> Ajuste a rota e os campos do login conforme o seu `AuthController`.

---

## Objetivo

Este projeto foi desenvolvido para colocar em prática conhecimentos de **Java, Spring Boot e desenvolvimento backend**, simulando uma aplicação real de gerenciamento de academia.

Entre os principais conhecimentos aplicados estão:

* Desenvolvimento de APIs REST
* Banco de dados relacional
* JPA e Hibernate
* Autenticação e autorização
* JWT
* Spring Security
* Controle de acesso por roles
* Relacionamentos entre entidades
* Queries e otimização de consultas
* Validação e tratamento de exceções
* Organização de aplicações backend

---

## Autor

**Igor Moura**

Estudante de Engenharia de Software com foco em desenvolvimento backend.

