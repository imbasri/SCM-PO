# README SCM-PO

## Running a Java Spring Boot Application

This guide will help you clone, build, and run a Java Spring Boot application.

---

### Prerequisites
1. **Java Development Kit (JDK)**: Ensure JDK 11 or higher is installed.
2. **Maven**: Install Apache Maven for dependency management.
3. **Git**: Ensure Git is installed to clone the repository.
4. **MySQL**: Install MySQL and create a database named `scm_db`.

---

### Steps to Clone and Run the Application

1. **Clone the Repository**:
    ```bash
    git clone <repository-url>
    cd <repository-folder>
    ```

2. **Configure the Database**:
    Update the `application.properties` file with the following settings:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/scm_db
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the Application**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Access the Application**:
    Open your browser and navigate to `http://localhost:8080`.

---

### Example Video

Below is an example video demonstrating the steps:

[![Watch the video](https://img.youtube.com/vi/dQw4w9WgXcQ/0.jpg)](https://www.youtube.com/watch?v=dQw4w9WgXcQ)

---

### Testing with Postman

If you want to test the application using Postman, you can download the Postman collection file: [SCM-PO.postman_collection](./SCM-PO.postman_collection).

---

### Troubleshooting
- Ensure all dependencies are installed.
- Check if the port `8080` is available.
- Verify the database connection settings in `application.properties`.
- Review logs for errors during startup.

---

### Contributing
Feel free to submit issues or pull requests to improve this project.

---

### License
This project is licensed under the MIT License.
