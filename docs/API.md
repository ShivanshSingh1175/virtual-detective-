# API Documentation

## Overview
The Detective Code API provides a RESTful interface for interacting with the platform. All endpoints are prefixed with `/api/v1`.

## Authentication
All API requests require authentication using JWT tokens.

### Headers
```
Authorization: Bearer <jwt_token>
Content-Type: application/json
```

## Endpoints

### Authentication

#### Register User
```http
POST /api/v1/auth/register
```
Request Body:
```json
{
    "username": "string",
    "email": "string",
    "password": "string"
}
```
Response:
```json
{
    "id": "long",
    "username": "string",
    "email": "string",
    "token": "string"
}
```

#### Login
```http
POST /api/v1/auth/login
```
Request Body:
```json
{
    "username": "string",
    "password": "string"
}
```
Response:
```json
{
    "token": "string",
    "user": {
        "id": "long",
        "username": "string",
        "email": "string"
    }
}
```

### Cases

#### Get All Cases
```http
GET /api/v1/cases
```
Query Parameters:
- `page`: int (default: 0)
- `size`: int (default: 10)
- `difficulty`: string (optional)
- `status`: string (optional)

Response:
```json
{
    "content": [
        {
            "id": "long",
            "title": "string",
            "description": "string",
            "difficulty": "string",
            "points": "int",
            "status": "string"
        }
    ],
    "totalElements": "long",
    "totalPages": "int",
    "currentPage": "int"
}
```

#### Get Case by ID
```http
GET /api/v1/cases/{id}
```
Response:
```json
{
    "id": "long",
    "title": "string",
    "description": "string",
    "difficulty": "string",
    "points": "int",
    "challenges": [
        {
            "id": "long",
            "title": "string",
            "description": "string",
            "difficulty": "string",
            "points": "int"
        }
    ]
}
```

#### Create Case
```http
POST /api/v1/cases
```
Request Body:
```json
{
    "title": "string",
    "description": "string",
    "difficulty": "string",
    "points": "int",
    "challenges": [
        {
            "title": "string",
            "description": "string",
            "difficulty": "string",
            "points": "int"
        }
    ]
}
```

### Challenges

#### Get Challenge by ID
```http
GET /api/v1/challenges/{id}
```
Response:
```json
{
    "id": "long",
    "title": "string",
    "description": "string",
    "difficulty": "string",
    "points": "int",
    "testCases": [
        {
            "input": "string",
            "expectedOutput": "string"
        }
    ]
}
```

#### Submit Solution
```http
POST /api/v1/challenges/{id}/submit
```
Request Body:
```json
{
    "code": "string",
    "language": "string"
}
```
Response:
```json
{
    "success": "boolean",
    "testResults": [
        {
            "passed": "boolean",
            "input": "string",
            "expectedOutput": "string",
            "actualOutput": "string"
        }
    ],
    "points": "int"
}
```

### User Progress

#### Get User Progress
```http
GET /api/v1/users/{id}/progress
```
Response:
```json
{
    "userId": "long",
    "completedChallenges": "int",
    "totalPoints": "int",
    "skillLevel": "string",
    "recentActivity": [
        {
            "challengeId": "long",
            "challengeTitle": "string",
            "completedAt": "datetime",
            "points": "int"
        }
    ]
}
```

### Code Execution

#### Execute Code
```http
POST /api/v1/execute
```
Request Body:
```json
{
    "code": "string",
    "language": "string",
    "input": "string"
}
```
Response:
```json
{
    "output": "string",
    "executionTime": "long",
    "memoryUsage": "long",
    "status": "string"
}
```

## Error Responses

### Error Format
```json
{
    "error": {
        "code": "string",
        "message": "string",
        "details": "object"
    }
}
```

### Common Error Codes
- `400`: Bad Request
- `401`: Unauthorized
- `403`: Forbidden
- `404`: Not Found
- `429`: Too Many Requests
- `500`: Internal Server Error

## Rate Limiting
- 100 requests per minute for authenticated users
- 20 requests per minute for unauthenticated users

## Best Practices
1. Always handle errors appropriately
2. Implement retry logic for failed requests
3. Cache responses when possible
4. Use pagination for large data sets
5. Validate input before sending requests

## SDKs
- [JavaScript SDK](sdk/javascript.md)
- [Python SDK](sdk/python.md)
- [Java SDK](sdk/java.md)

## Webhooks
The API supports webhooks for real-time notifications.

### Available Events
- `challenge.completed`
- `case.completed`
- `user.level_up`
- `code.execution.completed`

### Webhook Configuration
```http
POST /api/v1/webhooks
```
Request Body:
```json
{
    "url": "string",
    "events": ["string"],
    "secret": "string"
}
```

## Versioning
The API is versioned using the URL path. The current version is v1.

## Support
For API support, please contact:
- Email: api-support@detectivecode.com
- Documentation: https://docs.detectivecode.com
- Status Page: https://status.detectivecode.com 