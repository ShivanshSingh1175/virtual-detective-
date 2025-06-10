# Detective Code - Developer Guide

## Project Overview
Detective Code is a full-stack application built with Spring Boot (backend) and React (frontend). This guide will help developers understand the architecture, setup development environment, and contribute to the project.

## Architecture

### Tech Stack
- **Backend**: Spring Boot, JPA, MySQL, JWT
- **Frontend**: React, Material UI, CodeMirror
- **Database**: MySQL 8.0
- **Deployment**: Docker, Docker Compose

### System Design
```
[Client] <-> [Nginx] <-> [React Frontend] <-> [Spring Boot Backend] <-> [MySQL]
```

## Development Setup

### Prerequisites
- Java 17+
- Node.js 14+
- MySQL 8.0
- Docker & Docker Compose
- Git

### Backend Setup
1. Clone the repository
2. Configure MySQL
3. Set up environment variables
4. Run the application

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### Frontend Setup
1. Navigate to frontend directory
2. Install dependencies
3. Configure environment
4. Start development server

```bash
cd frontend
npm install
npm start
```

## Project Structure

### Backend Structure
```
src/main/java/com/detective/
├── controllers/    # REST endpoints
├── models/        # Entity classes
├── repositories/  # Data access
├── services/      # Business logic
├── security/      # Authentication
└── utils/         # Helper classes
```

### Frontend Structure
```
frontend/
├── src/
│   ├── components/    # React components
│   ├── services/      # API services
│   ├── utils/         # Helper functions
│   └── pages/         # Page components
└── public/           # Static assets
```

## API Documentation

### Authentication
- POST `/api/auth/login`
- POST `/api/auth/register`

### Cases
- GET `/api/cases`
- GET `/api/cases/{id}`
- POST `/api/cases`
- PUT `/api/cases/{id}`
- DELETE `/api/cases/{id}`

### Challenges
- GET `/api/challenges`
- GET `/api/challenges/{id}`
- POST `/api/challenges`
- PUT `/api/challenges/{id}`
- DELETE `/api/challenges/{id}`

## Database Schema

### Users
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    roles VARCHAR(255)
);
```

### Cases
```sql
CREATE TABLE cases (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    difficulty VARCHAR(50),
    points INTEGER
);
```

### Challenges
```sql
CREATE TABLE challenges (
    id BIGINT PRIMARY KEY,
    case_id BIGINT,
    title VARCHAR(255),
    description TEXT,
    difficulty VARCHAR(50),
    points INTEGER
);
```

## Development Guidelines

### Code Style
- Follow Java Code Conventions
- Use meaningful variable names
- Write comprehensive comments
- Follow React best practices

### Testing
- Write unit tests
- Integration tests
- End-to-end tests
- Performance tests

### Git Workflow
1. Create feature branch
2. Make changes
3. Write tests
4. Submit PR
5. Code review
6. Merge

## Deployment

### Local Deployment
```bash
./deploy/deploy.sh
cd deploy
docker-compose up -d
```

### Production Deployment
1. Set environment variables
2. Build Docker images
3. Deploy to server
4. Configure Nginx
5. Set up SSL

## Security

### Authentication
- JWT-based authentication
- Role-based access control
- Password encryption

### Data Protection
- Input validation
- SQL injection prevention
- XSS protection

## Performance

### Optimization
- Database indexing
- Caching
- Code minification
- Asset compression

### Monitoring
- Logging
- Error tracking
- Performance metrics

## Contributing

### Pull Request Process
1. Fork repository
2. Create feature branch
3. Make changes
4. Write tests
5. Update documentation
6. Submit PR

### Code Review
- Check code style
- Verify tests
- Review documentation
- Test functionality

## Troubleshooting

### Common Issues
1. Database connection
2. Build errors
3. Runtime errors
4. Deployment issues

### Debugging
- Use logging
- Check error messages
- Review stack traces
- Test locally

## Maintenance

### Regular Tasks
- Update dependencies
- Security patches
- Performance optimization
- Bug fixes

### Backup
- Database backup
- Configuration backup
- Code backup

## Support

### Resources
- Documentation
- Issue tracker
- Community forums
- Developer chat

### Contact
- Project maintainers
- Technical support
- Community leaders

## Future Development

### Planned Features
- Additional languages
- Advanced analytics
- Community features
- Mobile app

### Roadmap
- Short-term goals
- Medium-term goals
- Long-term goals

## Conclusion
This guide provides a comprehensive overview of the Detective Code project. Follow these guidelines to contribute effectively and maintain high code quality. 