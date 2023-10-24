# Task Management API

This is a simple RESTful API for managing tasks. You can perform basic Create, Read, Update operations on tasks using this API.

## Base Endpoint

All endpoints for this API are relative to the base URL: `/`

## Task Model

A task object has the following properties:

- `id` (Integer): A unique identifier automatically generated for each task.
- `title` (String): The title or name of the task.
- `description` (String): detailed text field providing additional information, context, or instructions regarding the task.
- `completed` (Boolean): flag indicating the completion status of the task.
- `createDate` (String): string representing the date when the task was created.
- `completeDate` (String): A string representing the date when the task was completed, this field is stored as a string in the "dd-MM-yyyy" format.
## Endpoints

### Get All Tasks

- **URL:** `/tasks`
- **Method:** GET
- **Description:** Retrieve a list of all tasks.
- **Response:** JSON array of task objects.
- **Example Request:**
  ```http
  GET /tasks
  ```
- **Example Response:**
  ```json
  [
    {
        "id": 1,
        "title": "Sample Task1",
        "description": "This is a sample task",
        "completed": false,
        "createDate": "2023-10-24T00:00:00.000+00:00",
        "completionDate": null
    }
  ]
  ```

### Get Task by ID

- **URL:** `/tasks/:id`
- **Method:** GET
- **Description:** Retrieve a specific task by its ID.
- **Parameters:** `:id` - The ID of the task to retrieve.
- **Response:** JSON object representing the task.
- **Example Request:**
  ```http
  GET /tasks/1
  ```
- **Example Response:**
  ```json
  {
        "id": 1,
        "title": "Sample Task1",
        "description": "This is a sample task",
        "completed": false,
        "createDate": "2023-10-24T00:00:00.000+00:00",
        "completionDate": null
  }
  ```

### Create Task

- **URL:** `/tasks`
- **Method:** POST
- **Description:** Create a new task.
- **Request Body:** JSON object with task details (title, description, completed, createDate, completeDate).
- **Response:** JSON object representing the newly created task.
- **Example Request:**
  ```http
  POST /tasks
  ```
  Request Body:
  ```json
  {
        "id": 1,
        "title": "Sample Task1",
        "description": "This is a sample task",
        "completed": false,
        "createDate": "2023-10-24T00:00:00.000+00:00",
        "completionDate": null
  }
  ```


### Update Task by ID

- **URL:** `/tasks/:id`
- **Method:** PUT
- **Description:** Update an existing task by its ID.
- **Parameters:** `:id` - The ID of the task to update.
- **Request Body:** JSON object with task details (title, description, completed, createDate, completeDate).
- **Response:** JSON object representing the updated task.
- **Example Request:**
  ```http
  PUT /api/tasks/1
  ```
  Request Body:
  ```json
  {
    "id": 1,
    "title": "Sample Task1 updated!!!!",
    "description": "This is a sample task",
    "completed": false,
    "createDate": "2023-10-24T00:00:00.000+00:00",
    "completionDate": null
  }
  ```


## Error Handling

- If a task with a specified ID does not exist, a TaskNotFoundException response will be logged and displayed on the console.

- When creating a new task, if a task with a specified ID already exists, a TaskAlreadyExistsException response will be logged and displayed on the console.

- Invalid requests or missing parameters will result in appropriate error responses with status codes and error messages.