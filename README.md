# Whiteboard Server - Spring Boot

This is a Spring Boot server for the Whiteboard web app. It provides RESTful web services for the Whiteboard clients to perform CRUD operations.

Whiteboard is a simple learning management platform where faculty can create courses and learning materials for students with various types of widgets, and students can take, submit and get scores for quizzes.

## RESTful API

Live API endpoint: https://cs5610-sp20-springboot-siyanhe.herokuapp.com/api

Please note that the services are hosted on Heroku so it may take few minutes to start when you access it.

### Widget Service

| HTTP Method | URL Pattern                 | Description                                                  |
| ----------- | --------------------------- | ------------------------------------------------------------ |
| POST        | `/api/topics/{tid}/widgets`  | Creates a new widget for a topic whose ID is `tid`. Returns the new widget with a unique identifier. |
| GET         | `/api/topics/{tid}/widgets` | Retrieves a collection of all widgets for a topic whose ID is `tid` as a JSON array. |
| PUT         | `/api/widgets/{wid}`        | Updates a widget whose id is `wid` with the new fields specified in the body of the request. Returns 1 if successful, 0 otherwise. |
| DELETE      | ``/api/widgets/{wid}``      | Removes a widget whose id is `wid`. Returns 1 if successful, 0 otherwise. |
| GET         | `/api/widgets`              | Retrieves a collection of all widgets as a JSON array.       |
| GET         | `/api/widgets/{wid}`        | Retrieves a single widget whose id is `wid` as a JSON object. |

### Topic Service

| HTTP Method | URL Pattern                 | Description                                                  |
| ----------- | --------------------------- | ------------------------------------------------------------ |
| POST        | `/api/lessons/{lid}/topics` | Creates a new topic for a lesson whose ID is `lid`. Returns the new topic with a unique identifier. |
| GET         | `/api/lessons/{lid}/topics` | Retrieves a collection of all topics for a lesson whose ID is `lid` as a JSON array. |
| PUT         | `/api/topics/{tid}`         | Updates a topic whose id is `tid` with the new fields specified in the body of the request. Returns 1 if successful, 0 otherwise. |
| DELETE      | ``/api/topics/{tid}``       | Removes a topic whose id is `tid`. Returns 1 if successful, 0 otherwise. |
| GET         | `/api/topics`               | Retrieves a collection of all topics as a JSON array.        |
| GET         | `/api/topics/{tid}`         | Retrieves a single topic whose id is `tid` as a JSON object. |


## Disclaimer

This is a personal project for CS5610 Web Development and the repository was migrated from GitHub Enterprise. For maintaining academic intergity, please do NOT reuse any code in this repository if you are working on your project for a related course.
