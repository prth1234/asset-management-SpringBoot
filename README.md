# Assetify - Backend Asset Management System

Welcome to Assetify - Your go-to solution for sleek and efficient backend Asset Management using Spring Boot. Seamlessly organize and control your valuable resources with ease.

## 🚀 Features

- **RESTful API Backend:** Expose the power of CRUD operations via API endpoints (`/api/assets` and `/api/categories`).
- **Secure Authentication:** Fortified user authentication ensuring data integrity and access control.
- **Category Management:** Effortlessly categorize assets for intuitive organization.
- **Swagger Documentation:** Explore and test API endpoints with Swagger documentation at `/swagger-ui.html`.
- **Data Security:** Leverage Spring Security to protect your backend from unauthorized access.

## 🛠️ Technologies Used

- **Spring Boot:** Fast-track your backend development.
- **Spring Data JPA:** Streamline database operations for seamless data management.
- **Spring Security:** Robust security measures to safeguard your assets.
- **MySQL:** The relational database for persistent storage.
- **Swagger:** Interactive API documentation for easy exploration.

## 🚦 Getting Started

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/prth1234/asset-management-SpringBoot.git
    ```

2. **Configure Database:**
    - Create a MySQL database and update `application.properties` with your database configurations.

3. **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the Swagger Documentation:**
    Open your browser and navigate to `http://localhost:8080/swagger-ui.html`.

## 🛠️ Usage

1. **API Endpoints:**
    - Utilize `/api/assets` for asset CRUD operations.
    - Manage asset categories with `/api/categories`.

2. **Swagger Playground:**
    - Explore and test the API using Swagger documentation.

## 📖 API Documentation

Visit [Swagger Documentation](http://localhost:8080/swagger-ui.html) for detailed API information and testing.

## 🤝 Contributing

We welcome contributions! Feel free to submit issues or pull requests. Check out our [contribution guidelines](CONTRIBUTING.md) for more details.

## 📄 License

This project is licensed under the [MIT License](LICENSE).
