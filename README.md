# School Database Management System

A Spring Boot application for managing school data including schools, students, teachers, classes, and subjects with RESTful API endpoints.

## Features

- **CRUD Operations**: Complete Create, Read, Update, Delete operations for all entities
- **RESTful API**: RESTful endpoints following best practices
- **MongoDB Integration**: NoSQL database for flexible data storage
- **OpenAPI Documentation**: Swagger UI for API documentation
- **Docker Support**: Containerized application with docker-compose
- **Validation**: Input validation using Bean Validation

## Entities

1. **School**: Basic school information (name, address, phone)
2. **Student**: Student details (name, age, school, class)
3. **Teacher**: Teacher information (name, subject, school)
4. **Class**: Class details (name, grade, school, teacher)
5. **Subject**: Subject information (name, description, school)

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data MongoDB**
- **Maven**
- **MongoDB**
- **OpenAPI 3.0**
- **Docker & Docker Compose**

## API Endpoints

### Schools
- `GET /api/schools` - Get all schools
- `GET /api/schools/{id}` - Get school by ID
- `POST /api/schools` - Create new school
- `PUT /api/schools/{id}` - Update school
- `DELETE /api/schools/{id}` - Delete school

### Students
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create new student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student
- `GET /api/students/school/{schoolId}` - Get students by school
- `GET /api/students/class/{classId}` - Get students by class

### Teachers
- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `POST /api/teachers` - Create new teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher
- `GET /api/teachers/subject/{subject}` - Get teachers by subject
- `GET /api/teachers/school/{schoolId}` - Get teachers by school

### Classes
- `GET /api/classes` - Get all classes
- `GET /api/classes/{id}` - Get class by ID
- `POST /api/classes` - Create new class
- `PUT /api/classes/{id}` - Update class
- `DELETE /api/classes/{id}` - Delete class
- `GET /api/classes/school/{schoolId}` - Get classes by school
- `GET /api/classes/grade/{grade}` - Get classes by grade
- `GET /api/classes/teacher/{teacherId}` - Get classes by teacher

### Subjects
- `GET /api/subjects` - Get all subjects
- `GET /api/subjects/{id}` - Get subject by ID
- `POST /api/subjects` - Create new subject
- `PUT /api/subjects/{id}` - Update subject
- `DELETE /api/subjects/{id}` - Delete subject
- `GET /api/subjects/school/{schoolId}` - Get subjects by school
- `GET /api/subjects/name/{name}` - Get subjects by name

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MongoDB (for local development)
- Docker & Docker Compose (for containerized deployment)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/HammadKashmiri1/school-management-system.git
   cd school-management-system
   ```

2. **Start MongoDB** (if running locally)
   ```bash
   # Start MongoDB service or use MongoDB Atlas
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**
   - Application: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - API Docs: http://localhost:8080/api-docs

### Docker Deployment

1. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

2. **Access the application**
   - Application: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

### API Testing

You can test the API using:

1. **Swagger UI**: http://localhost:8080/swagger-ui.html
2. **cURL commands**:
   ```bash
   # Create a school
   curl -X POST http://localhost:8080/api/schools \
     -H "Content-Type: application/json" \
     -d '{"name":"ABC School","address":"123 Main St","phone":"555-0123"}'
   
   # Get all schools
   curl http://localhost:8080/api/schools
   ```

## Project Structure

```
src/main/java/com/example/school_database/
├── SchoolDatabaseApplication.java
├── controller/
│   ├── SchoolController.java
│   ├── StudentController.java
│   ├── TeacherController.java
│   ├── ClassController.java
│   └── SubjectController.java
├── service/
│   ├── SchoolService.java
│   ├── StudentService.java
│   ├── TeacherService.java
│   ├── ClassService.java
│   └── SubjectService.java
├── repository/
│   ├── SchoolRepository.java
│   ├── StudentRepository.java
│   ├── TeacherRepository.java
│   ├── ClassRepository.java
│   └── SubjectRepository.java
└── model/
    ├── School.java
    ├── Student.java
    ├── Teacher.java
    ├── Class.java
    └── Subject.java
```

## Database Schema

The application uses MongoDB with the following collections:
- `schools` - School information
- `students` - Student records
- `teachers` - Teacher records
- `classes` - Class information
- `subjects` - Subject information

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License. 