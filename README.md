# Demo Spring Boot CRUD Application

My first Spring Boot CRUD application which I have created using spring boot, JPA Repository, h2 database, swagger documentation, exception handling.


## Features

- GET Api to fetch all courses deatils.
- GET Api to fetch course details with ID.
- POST Api to add course details.
- PUT Api to update course details.
- DELETE Api to remove a course.


## API Reference

#### Get all courses ("/courses")

```http
  GetMapping("/")
```

#### Get course ("/courses")

```http
  GetMapping("/{id}")
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of course to fetch |

#### Add course ("/courses")

```http
  PostMapping("/")
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `course`      | `course` | **Required**. Course to be added |

#### Update course ("/courses")

```http
  PutMapping("/{id}")
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of Course to be updated |
| `course`  | `course` | **Required**. Course details to be updated|

#### Delete course ("/courses")

```http
  DeleteMapping("/{id}")
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of course to be deleted |


## 🔗 Swagger Documentation Link

http://localhost:8080/swagger-ui/index.html

## 🔗 Repository Link

https://github.com/1999vansh/demo-crud-application/pull/1

## Developers

- Vansh Garg


