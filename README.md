# 🛒 Selenium Cart & Product Automation Suite

This is a TestNG-based Java Selenium automation framework for testing an e-commerce website. The suite covers product search, add-to-cart, checkout flow, and user authentication (signup and login).

---

## ✅ Features

- 🔐 **User Authentication**
  - Dynamic email-based signup flow
  - Login and logout validation

- 🔍 **Product Search**
  - Search functionality with result text assertions

- 🛒 **Cart Functionality**
  - Add first two products to cart
  - Clear cart before tests to ensure a clean state
  - Price format validation (`Rs.`)
  - Quantity and total price validation

- 💳 **Checkout Flow**
  - Proceed to checkout from cart
  - Validate total price on summary

---

## 🧰 Tech Stack

- Java 17+
- Selenium 4.31.0
- TestNG 7.11.0
- Maven
- Page Object Model (POM)
- IntelliJ IDEA

---

## 🚀 How to Run

1. Clone the repo:

```bash
git clone https://github.com/YOUR_USERNAME/selenium-cart-automation.git
cd selenium-cart-automation
