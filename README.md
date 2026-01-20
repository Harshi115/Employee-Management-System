
# 🏢 Employee Management System

### Full-Stack CRUD Application - Internship Training Project

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?style=for-the-badge&logo=spring)
![React](https://img.shields.io/badge/React-18-blue?style=for-the-badge&logo=react)



## 📋 About The Project

A simple Employee Management System built as part of a **5-week Full-Stack Development Internship** training program. This project demonstrates basic CRUD operations using Spring Boot backend and React frontend.

---

## 🛠️ Technologies Used

### Backend
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- postgresql 
- Maven

### Frontend
- React 18
- JavaScript (ES6+)
- CSS3
- HTML5

### Static Version
- HTML5
- CSS3
- Vanilla JavaScript

---

## ✨ Features

### Employee Management
- ✅ Create new employee
- ✅ View all employees in a grid
- ✅ View employee details
- ✅ Edit employee information
- ✅ Delete employee

---

## 🚀 Getting Started

### Prerequisites
```bash
Java 17
Node.js 18+
Maven 3.6+
```

### Backend Setup
```bash
cd "employeemanagement (1)"
mvn spring-boot:run
```

**Runs on:** `http://localhost:8080`

### Frontend Setup
```bash
cd employee-react-app
npm install
npm start
```

**Runs on:** `http://localhost:3000`

### Static Website
```bash
cd static-website
# Open index.html in browser
```

---

## 📡 API Endpoints

**Base URL:** `http://localhost:8080/api`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/employees` | Get all employees |
| GET | `/employees/{id}` | Get one employee |
| POST | `/employees` | Create employee |
| PUT | `/employees/{id}` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |

### Sample Request
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "department": "IT",
  "position": "Developer"
}
```

---

## 📁 Project Structure
```
employee-management-system/
├── employeemanagement (1)/     # Spring Boot Backend
├── employee-react-app/         # React Frontend
└── static-website/             # Static HTML version
```
## 🧪 Testing
```bash
# Backend
cd "employeemanagement (1)"
mvn test

# Frontend
cd employee-react-app
npm test
```

---

## 📚 Learning Outcomes

This project was completed in 5 weeks:

**Week 1:** Java basics, Spring Boot, REST APIs  
**Week 2:** HTML, CSS, JavaScript  
**Week 3:** React development  
**Week 4:** Git, GitHub, Azure DevOps  
**Week 5:** SOLID principles, Design patterns  

---

## 👨‍💻 Author

Harshita




