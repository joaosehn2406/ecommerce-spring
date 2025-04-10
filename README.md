<h1 align="center">🛒 Projeto Ecommerce Spring Boot</h1>

<div align="center">
  <strong>API RESTful robusta com cadastro de usuários, pedidos, produtos, categorias, pagamento e autenticação com segurança e performance!</strong>
</div>

<br />

<div align="center">
  <img src="https://img.shields.io/badge/SpringBoot-2.7.0-brightgreen?style=for-the-badge&logo=spring"/>
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql"/>
  <img src="https://img.shields.io/badge/JPA-Hibernate-orange?style=for-the-badge"/>
</div>

---

## 📖 Índice

- [🔍 Visão Geral](#-visão-geral)
- [📦 Funcionalidades](#-funcionalidades)
- [✨ Diferenciais](#-diferenciais)
- [💻 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Configuração do Ambiente](#-configuração-do-ambiente)
- [🧪 Testes e Popular Dados](#-testes-e-popular-dados)
- [🤝 Como Contribuir](#-como-contribuir)
- [📄 Licença](#-licença)

---

## 🔍 Visão Geral

Este projeto é uma API completa de ecommerce desenvolvida com Spring Boot, com foco em boas práticas, escalabilidade, modularização e clareza de código. 

A API realiza o gerenciamento completo de um sistema de vendas, incluindo:
- Cadastro de usuários, produtos, categorias e pedidos;
- Relacionamentos entre entidades (ManyToOne, OneToMany, ManyToMany);
- Registro e visualização de pedidos com itens, status e pagamento.

---

## 📦 Funcionalidades

✔️ Cadastro e gerenciamento de usuários  
✔️ Cadastro de produtos com múltiplas categorias  
✔️ Realização de pedidos com múltiplos itens  
✔️ Controle de status do pedido (`WAITING_PAYMENT`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELED`)  
✔️ Pagamento integrado ao pedido com relação `@OneToOne`  
✔️ Total do pedido calculado automaticamente  
✔️ Respostas completas em JSON com todos os relacionamentos (client, items, products, subTotal, total, payment)  
✔️ Requisições `POST`, `GET`, `DELETE`, `PUT` e `PATCH`  

---

## 📈 Diferenciais

⚙️ **Uso de JPA com Hibernate** para mapeamento automático das entidades  
🔁 **Relacionamentos complexos tratados com elegância** usando `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@EmbeddedId`, `@RestController`,   
🧠 **Cálculo de subtotal e total encapsulado nas entidades** com `getSubTotal()` e `getTotal()`  
📐 **Resource e Service separados e bem estruturados**  
🎯 **JSONs de entrada e saída claros e consistentes para integração com frontend ou testes via Postman**  

---

## 💻 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL ou H2
- Hibernate
- Jackson (para serialização JSON)
- Maven

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- JDK 17 instalado
- IDE (IntelliJ, Eclipse, STS ou VS Code com suporte Java)
- PostgreSQL (ou use o banco em memória H2 para testes)
- Maven

### Passos

1. Clone o projeto:
```bash
git clone https://github.com/joaosehn2406/ecommerce-spring.git
cd seu-projeto-ecommerce
```

2. Configure o `application.properties` com as credenciais do seu banco (ou use o `application-test.properties` com H2).

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

4. Acesse os endpoints via Postman ou front-end (porta padrão: `http://localhost:8080`).


---

## 🧪 Testes e Popular Dados

O projeto vem com uma classe `TestConfig` com o profile `test` que popula automaticamente:
- Usuários (`User`)
- Produtos (`Product`)
- Categorias (`Category`)
- Pedidos (`Order`)
- Itens do Pedido (`OrderItem`)
- Pagamentos (`Payment`)

Perfeito para testes e aprendizado.

---

## 🤝 Como Contribuir

1. Faça um fork do projeto
2. Crie uma branch com sua feature: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'feat: minha nova feature'`
4. Push para sua branch: `git push origin minha-feature`
5. Abra um Pull Request 🤓

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte [MIT License](https://mit-license.org/) para mais detalhes.

---

Desenvolvido com foco em boas práticas, didática e organização. Ideal para aprender e evoluir com Spring Boot e JPA!
