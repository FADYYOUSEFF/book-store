# 📚 Bookstore API

A monolithic **Spring Boot** application that provides a secure and flexible **Bookstore API**.  
The app combines **REST** (for authentication) and **GraphQL** (for bookstore operations) endpoints, secured with **Spring Security**.

---

## ✨ Features

- **Authentication**
  - `POST /register` – Register a new user.
  - `POST /login` – Authenticate and obtain a JWT token.
- **Role-based Access**
  - `MEMBER`: Can browse and filter books/authors.
  - `STAFF`: Can add new books, manage copies, and handle borrowing/returning.
- **GraphQL Endpoints** (protected with JWT)
  - Add a book
  - Add a book copy (staff only)
  - Borrow / Return a book
  - Filter books by user or author
  - Filter book copies
- **Postman Collection**
  - Provided for easy testing.
  - Includes duplicated GraphQL queries for faster adjustments and usage.

---

## 🛠️ Tech Stack

- **Spring Boot**
- **Spring Security** with JWT
- **GraphQL** (for data queries and mutations)
- **Hibernate / JPA** (persistence layer)
- **H2 / PostgreSQL** (configurable database)

