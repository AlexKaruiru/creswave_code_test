# Posts  API Guide

---

## Description
- **Language:** Java
- **Framework:** Spring Boot
- **JDK Version:** JDK 17
- **Database:** MySQL
- **ORM:** MyBatis
- **Auth:** JWT
- **Build Tool:** Maven
- **API Documentation:** Swagger
- **Port:** 8082

The Posts Service API provides endpoints to manage users, blogs, and comments within a blogging platform. It offers functionalities such as user registration, login, CRUD operations on blogs and comments, as well as fetching all blogs with associated comments.

## Routes

### Users
1. **User Signup**
   - Method: POST
   - Path: `/api/signup`
   - Summary: Endpoint to register a new user.
   - Request Body: `UserModel`
   - Response: ResponseEntity with a success message.

2. **User Login**
   - Method: POST
   - Path: `/api/login`
   - Summary: Endpoint for user login.
   - Request Body: `LoginForm`
   - Response: String (likely a JWT token).

3. **Get All Users**
   - Method: GET
   - Path: `/api/users`
   - Summary: Endpoint to retrieve all users. Requires admin role.
   - Response: List of `UserModel`.

4. **Update User Profile**
   - Method: PUT
   - Path: `/api/users/{userId}`
   - Summary: Endpoint to update a user's profile.
   - Path Variable: `userId`
   - Request Body: `UserModel`
   - Response: ResponseEntity with a success message.

5. **Update User**
   - Method: PUT
   - Path: `/users/{userId}`
   - Summary: Endpoint to update a user. Requires admin role or the user themselves.
   - Path Variable: `userId`
   - Request Body: `UserModel`
   - Response: ResponseEntity with a success message.

6. **Delete User**
   - Method: DELETE
   - Path: `/api/users/{userId}`
   - Summary: Endpoint to delete a user. Requires admin role.
   - Path Variable: `userId`
   - Response: ResponseEntity with a success message.

### Blogs
1. **Create a new blog**
   - Method: POST
   - Path: `/api/blogs`
   - Summary: Endpoint to create a new blog.
   - Request Header: `X-AUTH-TOKEN`
   - Request Body: `BlogModel`
   - Response: ResponseEntity

2. **Update an existing blog**
   - Method: PUT
   - Path: `/api/blogs/{blogId}`
   - Summary: Endpoint to update an existing blog.
   - Path Variable: `blogId`
   - Request Header: `X-AUTH-TOKEN`
   - Request Body: `BlogModel`
   - Response: ResponseEntity

3. **Delete a blog**
   - Method: DELETE
   - Path: `/api/blogs/{blogId}`
   - Summary: Endpoint to delete a blog.
   - Path Variable: `blogId`
   - Request Header: `X-AUTH-TOKEN`
   - Response: ResponseEntity

4. **Get all blogs with comments**
   - Method: GET
   - Path: `/api/blogs`
   - Summary: Endpoint to retrieve all blogs with their associated comments.
   - Response: List of `BlogModel`

5. **Create a new comment for a blog**
   - Method: POST
   - Path: `/api/blogs/{blogId}/comments`
   - Summary: Endpoint to create a new comment for a specific blog.
   - Path Variable: `blogId`
   - Request Header: `X-AUTH-TOKEN`
   - Request Body: `CommentModel`
   - Response: ResponseEntity

6. **Update an existing comment**
   - Method: PUT
   - Path: `/api/blogs/comments/{commentId}`
   - Summary: Endpoint to update an existing comment.
   - Path Variable: `commentId`
   - Request Header: `X-AUTH-TOKEN`
   - Request Body: `CommentModel`
   - Response: ResponseEntity

7. **Delete a comment**
   - Method: DELETE
   - Path: `/api/blogs/comments/{commentId}`
   - Summary: Endpoint to delete a comment.
   - Path Variable: `commentId`
   - Request Header: `X-AUTH-TOKEN`
   - Response: ResponseEntity
