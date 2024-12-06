# **Device Management API**

This project is a Spring Boot-based REST API for managing a database of devices. It supports operations like adding, retrieving, updating, deleting, and searching devices by their brand.

---

## **Features**

- Add a new device.
- Get a device by its ID.
- List all devices.
- Update a device (full and partial updates supported).
- Delete a device by its ID.
- Search devices by brand.

---

## **Tech Stack**

- **Java**: Programming language used for development.
- **Spring Boot**: Framework for building the REST API.
- **H2 Database**: In-memory database for testing and development.
- **JPA/Hibernate**: For object-relational mapping.
- **Gradle**: Dependency management.
- **JUnit & Mockito**: For unit testing.

---

## **Setup and Installation**

### **Prerequisites**
- Java 17 or higher installed.
- Gradle.
- Git.

### **Steps to Run**

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/device-management-api.git
   cd device-management-api

2. Build the project:
   ```bash
   ./gradlew build

3. Run the application:
   ```bash
   ./gradlew bootRun

4. Access the API at:

   Base URL: http://localhost:8080/api/devices

---

## **Endpoints**

### **1. Add Device**
**POST** `/api/devices`  
Adds a new device to the database.

**Request Body**:
```json
{
  "name": "Device Name",
  "brand": "Device Brand"
}
```
**Response**:
```json
{
   "id": 1,
   "name": "Device Name",
   "brand": "Device Brand",
   "createdAt": "2024-12-06T12:00:00Z"
}
```

### **2. Get Device by ID**
**GET** `/api/devices/{id}`  
Retrieves a device by its ID.

Example: `/api/devices/1`

**Response**:
```json
{
   "id": 1,
   "name": "Device Name",
   "brand": "Device Brand",
   "createdAt": "2024-12-06T12:00:00Z"
}
```

### **3. List All Devices**
**GET** `/api/devices`  
Retrieves all devices from the database.

### **4. Update Device**
**PUT** `/api/devices/{id}`  
Updates all fields of a device.

**PATCH** `/api/devices/{id}`  
Updates one or more fields of a device.

**Request Body**:
```json
{
   "name": "Updated Device Name",
   "brand": "Updated Device Brand"
}
```

### **5. Delete Device**
**DELETE** `/api/devices/{id}`  
Deletes a device by its ID.

### **6. Search Devices by Brand**
**GET** `/api/devices/search?brand={brand}`
Searches for devices by brand.

Example: `/api/devices/search?brand=Apple`

---

## **Testing**
This project includes comprehensive unit tests using JUnit 5 and Mockito. To run tests:
```bash
./gradlew test
```
---

## **Project Structure**

mirza.device.challenge:
- controller: REST controllers for handling API requests.
- service: Business logic and service layer.
- repository: JPA repositories for database interaction.
- model: Device entity class.


src/test/java:
        Unit tests for the service layer and other components.

---

## **Notes**

-The application uses an in-memory H2 database by default.
Access the H2 console at:
http://localhost:8080/h2-console
Use the following credentials:
```yaml
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (leave blank)
```

- If the port 8080 is already in use, you can change it in the `application.properties` file by setting `server.port=*new_port*`

---

## **Contributors**
- Mirza Delic
