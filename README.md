# 🛣️ Na Vereda API

> **"Não é só sobre viajar, é sobre descobrir o nosso caminho."**

## 👋 Sobre o Projeto

Seja bem-vindo ao repositório da API **Na Vereda**.
A ideia desse projeto nasceu de uma necessidade real: criar um planejador de viagens que entenda a minha realidade regional (Norte/Belém) e ajude o usuário a descobrir destinos baseados no seu perfil — seja um banho de **Igarapé** gelado, um almoço nas **Ilhas** ou um passeio cultural pela **Cidade**.
Este é um projeto de **Portfólio**, desenvolvido durante minha jornada de transição de carreira de QA para **Desenvolvedor Java Backend**. O objetivo aqui é aplicar na prática conceitos de arquitetura, integração de APIs e boas práticas de código.

## 🚀 O que já está funcionando? (MVP 1.0)
Nesta primeira versão, o sistema conta com um módulo de **Cadastro Inteligente**, que integra com serviços de geolocalização para enriquecer os dados do usuário automaticamente.

## 🗺️ Roadmap & Próximos Passos
Este projeto é vivo e está sendo desenvolvido em etapas. O objetivo é simular um ambiente real de evolução de software. Minhas metas para as próximas sprints incluem:

- [ ] **Core de Viagens:** Implementação do algoritmo que cruza as preferências do usuário (Ex: "Gosta de Igarapé") com dados reais de APIs de turismo para sugerir roteiros.
- [ ] **Segurança (Spring Security):** Blindagem da API com autenticação e autorização via **Tokens JWT**, garantindo que apenas usuários logados acessem seus dados.
- [ ] **Mensageria & Notificações:** Integração com serviço de E-mail para confirmar cadastro e enviar as sugestões de roteiro e mais.
- [ ] **Frontend & Deploy:** Desenvolvimento de uma interface web interativa e deploy da aplicação completa na nuvem (Render/Railway), permitindo que qualquer pessoa teste o sistema em tempo real sem configurar ambiente local.

## 🛠️ Stack Tecnológica
Escolhi tecnologias modernas e consolidadas no mercado para garantir robustez:

* **Java 21** (Versão LTS mais recente, aproveitando Records e novos recursos).
* **Spring Boot 3** (Web, Data JPA, Validation).
* **PostgreSQL** (Banco relacional parrudo).
* **Docker & Docker Compose** (Para subir o ambiente com um comando).
* **Maven** (Gerenciamento de dependências).
* **Git & GitHub** (Versionamento).

## 🏛️ Decisões de Arquitetura
O projeto segue a **Arquitetura de Camadas (Layered Architecture)**, priorizando a organização e separação de responsabilidades:

* `controller`: Camada de entrada (REST).
* `service`: Regras de negócio e integrações.
* `repository`: Acesso a dados.
* `model`: Entidades e objetos de domínio.

## 📦 Como rodar na sua máquina

### Pré-requisitos
* Docker e Docker Compose.
* JDK 21.

### Passo a Passo
1.  **Clone este repositório:**
    ```bash
    git clone [https://github.com/jsantosdev1/na-vereda-api.git](https://github.com/jsantosdev1/na-vereda-api.git)
    ```
2.  **Suba o Banco de Dados (Docker):**
    Entre na pasta do projeto e rode:
    ```bash
    docker-compose up -d
    ```
3.  **Execute a Aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
    *(A API subirá na porta 8080)*

## 🔌 Testando a API
Você pode usar o Postman, Bruno ou Insomnia.

**Criar Usuário:** `POST /users`

```json
{
  "nome": "Seu Nome",
  "email": "seu.email@exemplo.com",
  "senha": "suasenha",
  "cep": "11223344",
  "numero": "111",
  "complemento": "Próx ao Banco Itaú"
}