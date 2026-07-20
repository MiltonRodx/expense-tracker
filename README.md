# Expense Tracker

<p align="center">
  <img src="https://img.shields.io/badge/Java-21+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/CLI-Application-2E8B57?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Storage-CSV-00599C?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" />
</p>

<p align="center">
  A lightweight <strong>Command-Line Interface (CLI)</strong> application written in Java for tracking personal expenses.
  <br>
  Built as a solution for the <a href="https://roadmap.sh/projects/expense-tracker">Expense Tracker</a> project on roadmap.sh.
</p>

---

## Features

- Add new expenses
- Update existing expenses
- Delete expenses
- List all recorded expenses
- Display total expense summary
- Display monthly expense summaries
- Persistent CSV storage
- Formatted terminal tables
- Robust input validation
- Comprehensive error handling

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Core application |
| Maven | Dependency management & build automation |
| Apache Commons CSV | CSV reading/writing |
| Apache Commons Lang3 | Numeric validation (`NumberUtils`) |
| ASCII Table | Terminal table rendering |

---

## Project Structure

```text
expense-tracker
│
├── src
│   └── main
│       └── java
│           ├── App.java
│           ├── Expense.java
│           └── Utils.java
│
├── expenses.csv
├── pom.xml
└── README.md
```

### Components

| File | Responsibility |
|------|----------------|
| `App.java` | CLI entry point and command dispatcher |
| `Expense.java` | Expense model |
| `Utils.java` | CSV operations, validation, formatting, helper methods |
| `expenses.csv` | Persistent expense database |

---

# Installation

Clone the repository

```bash
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker
```

Build the project

```bash
mvn clean package
```

Run

```bash
java -jar target/expense-tracker.jar
```

or

```bash
mvn exec:java
```

---

# Usage

## Add an expense

```bash
expense-tracker add --description "Lunch" --amount 20
```

```
Expense added successfully (ID: 1)
```

---

## Update an expense

```bash
expense-tracker update --id 1 --description "Business Lunch" --amount 25
```

---

## Delete an expense

```bash
expense-tracker delete --id 1
```

---

## List expenses

```bash
expense-tracker list
```

```
+----+------------+----------------+--------+
| ID | Date       | Description    | Amount |
+----+------------+----------------+--------+
| 1  | 2024-08-06 | Lunch          | $20    |
| 2  | 2024-08-06 | Coffee         | $4.50  |
+----+------------+----------------+--------+
```

---

## Total summary

```bash
expense-tracker summary
```

```
Total expenses: $24.50
```

---

## Monthly summary

```bash
expense-tracker summary --month 8
```

```
Total expenses for August: $24.50
```

---

# Data Storage

The application stores every expense inside **expenses.csv**.

Each record contains:

| Field | Description |
|-------|-------------|
| ID | Unique expense identifier |
| Date | Creation date |
| Description | Expense description |
| Amount | Expense value |

The file is automatically updated whenever an expense is added, modified, or removed.

---

# Error Handling

The application validates user input and gracefully handles situations such as:

- Missing command arguments
- Invalid commands
- Invalid numeric values
- Negative amounts
- Invalid months
- Empty descriptions
- Non-existent IDs
- Malformed CSV data

---

# Dependencies

```xml
Apache Commons CSV
Apache Commons Lang3
ASCII Table
```

Managed entirely with **Maven**.

---

# Assignment

This project is an implementation of the **Expense Tracker** project from roadmap.sh.

The objective was to create a CLI application capable of:

- Adding expenses
- Updating expenses
- Deleting expenses
- Listing expenses
- Showing summaries
- Persisting data using a local file

Project page:

https://roadmap.sh/projects/expense-tracker

---

# What I Practiced

- Object-Oriented Programming
- File I/O
- CSV Parsing
- Maven
- CLI Design
- Command Parsing
- Exception Handling
- Data Validation
- Clean Code Principles
- Utility Class Design

---

# Future Improvements

- Expense categories
- Budget management
- CSV export/import
- Colored terminal output
- Search by description
- Sorting options
- Statistics
- JSON storage support
- Unit testing (JUnit)
- Native executable using GraalVM

---

# License

This project is licensed under the MIT License.
