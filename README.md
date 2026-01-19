Employee Management System
📌 Project Overview
This project is an Employee Management System developed as part of an internship assignment.
It demonstrates backend CRUD operations using Spring Boot, a static HTML website, a React frontend, and CI/CD automation.
🛠️ Tech Stack
Backend: Java, Spring Boot, Maven
Frontend: React, HTML, CSS
Database: H2 / MySQL (as configured)
CI/CD: GitHub Actions
Build Tool: Maven, npm
✨ Features
Backend (Spring Boot REST API)
Create employee record
Read employee records
Update employee record
Delete employee record
Static Website (No API / No DB)
Employee list page
View employee details
Add employee
Edit employee
Delete employee
React Application (API Based)
Employee grid view
View employee details
Add / edit employee
Delete employee
🚀 How to Run the Project
Backend
Copy code
Bash
cd employeemanagement
mvn spring-boot:run
Frontend (React)
Copy code
Bash
cd employee-react-app
npm install
npm start
Static Website
Open files from static-website folder directly in browser
🔄 CI/CD
CI/CD pipelines are implemented using GitHub Actions for both backend and frontend to automate build and test processes.
📂 Repository Structure
Copy code

Employee-Management-System
│── employeemanagement
│── employee-react-app
│── static-website
│── .github/workflows
📌 Notes
GitHub Actions is used instead of Azure DevOps for CI/CD.
The project can be extended with additional unit tests and API mocking.
👩‍💻 Author
Harshita
