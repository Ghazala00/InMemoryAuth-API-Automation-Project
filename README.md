# InMemoryAuth API Automation Project

##  Overview

This project is an **API automation testing framework** for the *InMemoryAuth* system. It focuses on validating authentication-related APIs using automated tests to ensure correctness, reliability, and stability of core authentication functionalities.

The framework is designed following **QA best practices** and can be easily extended to cover additional APIs or test scenarios.

---

##  Scope of Testing

The project covers automated testing for:

* User authentication
* Token generation and validation
* Positive and negative test scenarios
* API response validation (status codes, response body, headers)

---

##  Tech Stack

* **Programming Language:** Java
* **Automation Tool:** Rest Assured
* **Test Framework:** TestNG
* **Build Tool:** Maven
* **IDE:** IntelliJ IDEA / Eclipse

---

## Project Structure

```
InMemoryAuth-API-Automation-Project
│
├── src/test/java
│   ├── base        # Base test setup and configurations
│   ├── tests       # Test classes for API endpoints
│   └── utils       # Utility classes (helpers, constants, etc.)
│
├── src/test/resources
│   └── testng.xml  # TestNG configuration file
│
├── pom.xml         # Maven dependencies and configuration
└── README.md
```

---

## Setup & Installation

### Prerequisites

* Java JDK 8 or higher
* Maven installed
* Git

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Ghazala00/InMemoryAuth-API-Automation-Project.git
```

2. Navigate to the project directory:

```bash
cd InMemoryAuth-API-Automation-Project
```

3. Install dependencies:

```bash
mvn clean install
```

---

##  How to Run Tests

Run all tests using Maven:

```bash
mvn test
```

Or run tests via **testng.xml** from your IDE.

---

##  Test Scenarios Covered

* Valid login with correct credentials
* Invalid login with wrong credentials
* Missing or invalid authentication tokens
* Response status code validation
* Response body assertions

---

##  Reporting

* Test execution results are displayed in the console
* TestNG default reports are generated after execution

---

## Future Enhancements

* Add data-driven testing
* Integrate with CI/CD pipeline (GitHub Actions / Jenkins)
* Enhance reporting using Allure
* Add more negative and edge test cases

---

##  Author

**Seif Ghazala**
Software Tester | QA Engineer
[GitHub Profile](https://github.com/Ghazala00)

---

## 📄 License

This project is for learning and demonstration purposes.
