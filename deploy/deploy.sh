#!/bin/bash

# Exit on error
set -e

echo "Starting deployment process..."

# Build backend
echo "Building backend..."
cd ..
chmod +x mvnw
./mvnw clean package -DskipTests

# Create necessary directories
mkdir -p deploy/backend
mkdir -p deploy/frontend

# Copy backend files
echo "Copying backend files..."
cp target/detective-code-1.0-SNAPSHOT.jar deploy/backend/
cp src/main/resources/application.properties deploy/backend/

# Build frontend
echo "Building frontend..."
cd frontend
npm install
npm run build

# Copy frontend build
echo "Copying frontend build..."
cp -r build/* ../deploy/frontend/

# Create Docker Compose file
echo "Creating Docker Compose configuration..."
cd ../deploy

cat > docker-compose.yml << EOL
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: detective_code
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/detective_code
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    ports:
      - "80:80"
    depends_on:
      - backend

volumes:
  mysql_data:
EOL

# Create backend Dockerfile
echo "Creating backend Dockerfile..."
cat > backend/Dockerfile << EOL
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY detective-code-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
EOL

# Create frontend Dockerfile
echo "Creating frontend Dockerfile..."
cat > frontend/Dockerfile << EOL
FROM nginx:alpine
COPY . /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
EOL

# Create nginx configuration
echo "Creating nginx configuration..."
cat > frontend/nginx.conf << EOL
server {
    listen 80;
    server_name localhost;

    location / {
        root /usr/share/nginx/html;
        index index.html;
        try_files \$uri \$uri/ /index.html;
    }

    location /api {
        proxy_pass http://backend:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade \$http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host \$host;
        proxy_cache_bypass \$http_upgrade;
    }
}
EOL

echo "Deployment files created successfully!"
echo "To deploy the application:"
echo "1. Make sure Docker and Docker Compose are installed"
echo "2. Run 'docker-compose up -d' in the deploy directory"
echo "3. Access the application at http://localhost" 