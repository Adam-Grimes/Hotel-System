# Hotel Employee Management System

This project is a desktop application for managing a hotel's employee records, built entirely in **Java** with the **Swing** GUI toolkit.

It's a comprehensive demonstration of core Object-Oriented Programming (OOP) principles and data persistence, where all employee data is saved locally to a binary file.

![Employee Icon](https://github.com/Adam-Grimes/hotel-system/blob/main/Hotel%20System/images/employees.jpg?raw=true)

---

## 🚀 Key Features

* **Full CRUD Functionality:** Provides a complete "Employee Maintenance" interface allowing administrators to **Add**, **List**, **View**, **Edit**, and **Delete** employee records.
* **Object-Oriented by Design:** Built using key OOP concepts:
    * **Inheritance:** The `Employee` and `Guest` classes inherit from an abstract `Person` class, reusing common properties.
    * **Interfaces:** Implements a `Payable` interface to define a contract for methods like `calculatePay` and `incrementSalary`.
    * **Composition:** Classes like `Guest` "HAVE-A" `CreditCard` and `Name`, demonstrating composition over inheritance where appropriate.
* **Data Persistence:** Uses **Java Serialization** to save the entire `ArrayList` of employee objects to a binary file (`employees.bin`) on exit and load them back on startup.
* **Robust Exception Handling:** Implements `try-catch` blocks to manage invalid user input (like text in a number field or invalid dates) without crashing the application.
* **GUI Interface:** All functionality is accessed through a clean **Java Swing** menu, using `JOptionPane` dialogs for user input.

---

## 🛠 Tech Stack

* **Language:** Java
* **UI Toolkit:** Java Swing
* **Data Persistence:** Java Serialization

---

## 👨‍💻 Author

**Adam Grimes** - [LinkedIn](https://www.linkedin.com/in/your-profile) | [GitHub](https://github.com/Adam-Grimes)
