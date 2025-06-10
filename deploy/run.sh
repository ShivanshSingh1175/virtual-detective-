#!/bin/bash

# Start MySQL if not running
if ! pgrep -x "mysqld" > /dev/null; then
    echo "Starting MySQL..."
    brew services start mysql
fi

# Create database if not exists
mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS detective_code;"

# Build and run backend
echo "Building and running backend..."
cd ..
./mvnw clean package
java -jar target/detective-code-1.0-SNAPSHOT.jar &

# Wait for backend to start
echo "Waiting for backend to start..."
sleep 10

# Install frontend dependencies and start
echo "Setting up frontend..."
cd frontend
npm install
npm start 