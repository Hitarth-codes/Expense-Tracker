# Expense Tracker

## Overview

**Expense Tracker** is a Java Spring Boot application for tracking expenses, supporting user authentication via JWT, and providing analytics such as category-wise totals and monthly summaries. It uses SQLite as the database and Spring Security for authentication.

---

## Features

- **User Authentication**: Signup and login with JWT token.
- **Expense Management**: Add, update, delete expenses.
- **Analytics**: Get expenses by date range, category-wise totals, and monthly summaries.
- **Security**: Password encryption and JWT-based authentication.

---

## Main Components

### Entities

- `User`: Represents a user.
- `Expense`: Represents an expense entry.

### Repositories

- `UserRepository`: User data access.
- `ExpenseRepository`: Expense data access and analytics queries.

### DTOs

- `SignupRequest`, `LoginRequest`: Authentication payloads.
- `ExpenseRequest`, `ExpenseResponse`: Expense payloads.
- `CategoryTotalRequest`, `CategoryTotalResponse`: Category analytics.
- `MonthlySummaryRequest`, `MonthlySummaryResponse`: Monthly analytics.
- `CategorySummary`: Category summary for monthly analytics.
- `AuthResponse`: JWT token response.

### Controllers

- `AuthController`: Signup and login endpoints.
- `ExpenseController`: Expense CRUD endpoints.
- `AnalyticsController`: Analytics endpoints.

### Services

- `AuthService`: Handles authentication logic.
- `ExpenseService`: Handles expense logic.
- `AnalyticsService`: Handles analytics logic.

### Utilities

- `JwtUtil`: JWT token generation and validation.
- `SecurityConfig`: Spring Security configuration.

---

## Configuration

- **Database**: SQLite, configured in `application.properties`.
- **JWT**: Secret and expiration configured in `application.properties`.
- **Logging**: Log levels set in `application.properties`.

---

## API Endpoints

### Authentication

- `POST /api/myexpense/signup`: Register a new user.
- `POST /api/myexpense/login`: Login and receive JWT token.

### Expenses

- `POST /api/Expenses/addExpense`: Add a new expense.
- `PUT /api/Expenses/updateExpense/{id}`: Update an expense.
- `DELETE /api/Expenses/deleteExpense/{id}`: Delete an expense.

### Analytics

- `GET /api/analytics/getExpensesByDateRange?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`: Get expenses in a date range.
- `POST /api/analytics/pie-chart`: Get category-wise totals for a month.
- `POST /api/analytics/summary`: Get monthly summary.

---

## How to Build

Run the following command in the project root:

```sh
mvn clean package
```

This will generate the JAR file at `target/my-expense-tracker-0.0.1-SNAPSHOT.jar`.

---

## How to Run

```sh
java -jar target/my-expense-tracker-0.0.1-SNAPSHOT.jar
```

---

## Source Files

- Main entry: `EtUserAuthServiceApplication`
- All source code is under `src/main/java/com/hitarth/my_expense_tracker`.

---

## License

See this file for# Expense-Tracker
Expense built using JAVA springboot using JAVA spring security with JWT token
