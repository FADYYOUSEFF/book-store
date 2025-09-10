# 📚 Book Store Backend API

This project is a **Spring Boot application** that provides a backend API for managing a book store.  
It supports **REST endpoints** for authentication and **GraphQL endpoints** for managing books, authors, and book copies.

---

## 🚀 Features

- **Authentication**
  - Public REST endpoints for user registration and login
  - JWT-based authentication and authorization
  - Role-based access control (`STAFF`, `MEMBER`)

- **GraphQL Endpoints**
  - `getBookById`
  - `filterBooks`
  - `getAuthorById`
  - `filterAuthors`
  - `borrowBook`
  - `returnBook`
  - `getBookCopy` (available copies for borrowing)
  - `filterBookCopy` (by status, name, borrowId, etc.)
  - `getBookCopyById`

- **Exception Handling**
  - Centralized exception handling for **REST endpoints**
  - Centralized exception handling for **GraphQL endpoints**

- **Security**
  - JWT filter with Spring Security
  - **Role-based access**
    - `STAFF`: Can access almost all endpoints (e.g., add book, add book copy, borrow, return)
    - `MEMBER`: Limited access (cannot add books or manage copies, restricted borrowing/returning)

- **Architecture**
  - Follows the standard **Controller → Service → Repository → Entity** pattern
