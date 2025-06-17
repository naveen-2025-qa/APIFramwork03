# 🔍 API Automation Framework using RestAssured, TestNG & SQL

A robust automation framework that validates CRUD operations for REST APIs using **TestNG** and **RestAssured**, with fully **database-driven test data**, elegant **ExtentReports integration**, and seamless **CI/CD execution via GitHub Actions**.

---

## 🚀 Overview

This project streamlines REST API testing through an object-oriented and reusable design, leveraging Java, Maven, TestNG, and RestAssured. It supports data-driven validation by ingesting test input directly from a SQL database and outputs detailed test execution results via ExtentReports. CI/CD hooks using GitHub Actions ensure real-time feedback and scalable deployment-ready testing.

---

## 📚 Tech Stack

| Tool/Library        | Purpose                                  |
|---------------------|------------------------------------------|
| Java                | Base language                            |
| TestNG              | Test orchestration framework             |
| RestAssured         | Fluent API test library                  |
| SQL (via JDBC)      | Externalized test data input             |
| Maven (POM)         | Dependency & build management            |
| ExtentReports       | Rich HTML reporting                      |
| GitHub Actions      | CI/CD automation                         |

---

## ✨ Core Features

- **📌 CRUD Test Coverage** – Full suite for Create, Read, Update & Delete API methods
- **🧪 Positive & Negative Testing** – Verifies expected behaviors and boundary failures
- **📊 DB-Driven Execution** – Loads test cases from SQL database via JDBC queries
- **🔄 Payload Generation** – Converts SQL result sets into Java POJOs dynamically
- **🧱 POM Architecture** – Modular, clean, and maintainable codebase
- **📈 ExtentReports Integration** – HTML reports with test metadata and error traces
- **🚦 GitHub Actions CI/CD** – Runs test suite automatically on push/pull

---

## 🔧 Installation & Setup

### 1. Clone the Project
```bash
git clone https://github.com/naveen-2025-qa/APIFramwork03.git
cd APIFramwork03
