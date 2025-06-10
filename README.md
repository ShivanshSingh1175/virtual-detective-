# Virtual Detective: Codebreaker Edition 🕵️‍♂️

An immersive, gamified educational platform where users solve programming challenges through detective-themed cases.

## 🎯 Features

- Interactive detective-themed UI with dark mode
- 50+ unique case files with progressive difficulty
- Real-time code execution using Judge0 API
- Monaco code editor integration
- Case progression and clue tracking system
- User authentication and progress tracking
- Responsive and animated UI elements

## 🛠️ Tech Stack

### Backend
- Spring Boot (Java)
- MySQL Database
- Judge0 API Integration
- Spring Security

### Frontend
- React.js
- Monaco Editor
- Tailwind CSS
- Framer Motion (for animations)

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- MySQL 8.0
- Maven

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Configure MySQL database in `application.properties`
3. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```

## 📁 Project Structure

```
virtual-detective/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── utils/
│   └── package.json
└── README.md
```

## 🔐 Environment Variables

Create a `.env` file in the backend directory with:
```
DB_URL=jdbc:mysql://localhost:3306/virtual_detective
DB_USERNAME=your_username
DB_PASSWORD=your_password
JUDGE0_API_URL=your_judge0_url
JWT_SECRET=your_jwt_secret
```

## 📝 License

MIT License - feel free to use this project for educational purposes.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. 