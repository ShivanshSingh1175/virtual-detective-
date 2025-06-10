# Deployment Guide

## Overview
This guide covers the deployment of the Detective Code platform in various environments, from local development to production.

## Prerequisites

### System Requirements
- Java 17 or higher
- Node.js 14 or higher
- MySQL 8.0 or higher
- Docker and Docker Compose
- 4GB RAM minimum
- 20GB disk space

### Required Software
- Git
- Maven
- npm
- Docker
- Docker Compose
- MySQL Client

## Local Development Deployment

### 1. Clone Repository
```bash
git clone https://github.com/detectivecode/platform.git
cd platform
```

### 2. Backend Setup
```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### 3. Frontend Setup
```bash
cd frontend
npm install
npm start
```

### 4. Database Setup
```bash
# Create database
mysql -u root -p
CREATE DATABASE detective_code;
```

### 5. Configuration
Create `application.properties`:
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/detective_code
spring.datasource.username=root
spring.datasource.password=your_password

# JWT
jwt.secret=your_secret_key
jwt.expiration=86400000

# Server
server.port=8080
```

## Docker Deployment

### 1. Build Images
```bash
# Build backend
docker build -t detective-code-backend ./backend

# Build frontend
docker build -t detective-code-frontend ./frontend
```

### 2. Docker Compose
Create `docker-compose.yml`:
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: your_password
      MYSQL_DATABASE: detective_code
    volumes:
      - mysql_data:/var/lib/mysql

  backend:
    image: detective-code-backend
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/detective_code
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: your_password
    ports:
      - "8080:8080"

  frontend:
    image: detective-code-frontend
    ports:
      - "80:80"
    depends_on:
      - backend

volumes:
  mysql_data:
```

### 3. Run with Docker Compose
```bash
docker-compose up -d
```

## Production Deployment

### 1. Server Preparation
```bash
# Update system
sudo apt update
sudo apt upgrade

# Install required software
sudo apt install docker.io docker-compose mysql-server
```

### 2. Security Setup
```bash
# Configure firewall
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
sudo ufw allow 8080/tcp

# Enable firewall
sudo ufw enable
```

### 3. SSL Configuration
```bash
# Install Certbot
sudo apt install certbot python3-certbot-nginx

# Obtain SSL certificate
sudo certbot --nginx -d your-domain.com
```

### 4. Nginx Configuration
```nginx
server {
    listen 80;
    server_name your-domain.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl;
    server_name your-domain.com;

    ssl_certificate /etc/letsencrypt/live/your-domain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/your-domain.com/privkey.pem;

    location / {
        proxy_pass http://frontend:80;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }

    location /api {
        proxy_pass http://backend:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

### 5. Environment Configuration
Create `.env` file:
```env
# Database
DB_HOST=mysql
DB_PORT=3306
DB_NAME=detective_code
DB_USER=root
DB_PASSWORD=your_secure_password

# JWT
JWT_SECRET=your_secure_secret
JWT_EXPIRATION=86400000

# Server
SERVER_PORT=8080
NODE_ENV=production
```

## Monitoring and Maintenance

### 1. Logging
```bash
# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f backend
```

### 2. Backup
```bash
# Database backup
mysqldump -u root -p detective_code > backup.sql

# Automated backup script
#!/bin/bash
BACKUP_DIR="/backup"
DATE=$(date +%Y%m%d)
mysqldump -u root -p detective_code > $BACKUP_DIR/backup_$DATE.sql
```

### 3. Monitoring
- Set up Prometheus for metrics
- Configure Grafana dashboards
- Set up alerting

## Scaling

### 1. Horizontal Scaling
```yaml
# docker-compose.yml
services:
  backend:
    deploy:
      replicas: 3
      resources:
        limits:
          cpus: '1'
          memory: 1G
```

### 2. Load Balancing
```nginx
upstream backend {
    server backend1:8080;
    server backend2:8080;
    server backend3:8080;
}
```

## Troubleshooting

### Common Issues
1. Database Connection
   - Check MySQL service
   - Verify credentials
   - Check network connectivity

2. Application Startup
   - Check logs
   - Verify environment variables
   - Check port availability

3. Performance Issues
   - Monitor resource usage
   - Check database performance
   - Review application logs

## Security Considerations

### 1. Network Security
- Use HTTPS
- Configure firewalls
- Implement rate limiting

### 2. Data Security
- Encrypt sensitive data
- Regular backups
- Access control

### 3. Application Security
- Input validation
- SQL injection prevention
- XSS protection

## Support and Maintenance

### 1. Regular Tasks
- Update dependencies
- Security patches
- Performance optimization
- Log rotation

### 2. Emergency Procedures
- Backup restoration
- Service recovery
- Incident response

## Conclusion
This deployment guide provides comprehensive instructions for deploying the Detective Code platform in various environments. Follow these guidelines to ensure a secure and efficient deployment. 