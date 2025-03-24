# Chat Real-Time với WebSocket và OAuth2

## Giới thiệu
Đây là một ứng dụng chat real-time được xây dựng bằng WebSocket để cung cấp khả năng giao tiếp tức thời giữa các người dùng. Hệ thống sử dụng OAuth2 để xác thực và phân quyền, đảm bảo an toàn và bảo mật.

## Công nghệ sử dụng
- **Backend**: Spring Boot, Spring Security OAuth2, Spring WebSocket
- **Frontend**: React, Vite, Tailwind CSS
- **Cơ sở dữ liệu**: MongoDB
- **Xác thực**: OAuth2 (Google, Facebook, Keycloak,...)
- **IDE**: IntelliJ IDEA, WebStorm

## Cài đặt và chạy ứng dụng

### Yêu cầu hệ thống
- Java 17 hoặc mới hơn
- Node.js 18+ và npm/yarn
- MongoDB
- Maven

### Hướng dẫn cài đặt
#### 1. Clone repository:
```sh
   git clone https://github.com/nguyen-tuankiet/websocket-chat-oauth2.git
```

#### 2. Cấu hình backend
- Chỉnh sửa `application.properties` hoặc `application.yml`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/chat_db
spring.security.oauth2.client.registration.google.client-id=your_client_id
spring.security.oauth2.client.registration.google.client-secret=your_client_secret
```
- Cài đặt MongoDB và chạy server:
```sh
   mongod --dbpath /path/to/your/data/db
```
- Build và chạy backend:
```sh
   mvn clean install
   mvn spring-boot:run
```

#### 3. Cấu hình frontend
```sh
   cd frontend
   npm install
   npm run dev
```

5. Truy cập ứng dụng tại `http://localhost:3000`

## Tính năng chính
- Đăng nhập bằng OAuth2 (Google, Facebook,...)
- Gửi/nhận tin nhắn real-time qua WebSocket
- Danh sách người dùng đang trực tuyến
- Hỗ trợ chat nhóm
- Lưu lịch sử tin nhắn vào MongoDB

## API Endpoints
| HTTP Method | Endpoint | Mô tả |
|------------|---------|-------|
| `POST` | `/api/auth/login` | Đăng nhập bằng OAuth2 |
| `GET` | `/api/users/online` | Lấy danh sách người dùng trực tuyến |
| `POST` | `/api/messages/send` | Gửi tin nhắn |
| `GET` | `/api/messages/history` | Lấy lịch sử tin nhắn |

## Hướng dẫn làm việc với Git

### Pull code mới nhất từ repository:
```sh
   git pull origin main
```

### Tạo nhánh mới để phát triển tính năng:
```sh
   git checkout -b feature/tinh-nang-moi
```

### Commit và push code lên repository:
```sh
   git add .
   git commit -m "Mô tả thay đổi"
   git push origin feature/tinh-nang-moi
```

### Tạo Pull Request để merge vào main:
- Truy cập GitHub/GitLab
- Mở Pull Request từ nhánh `feature/tinh-nang-moi` vào `main`
- Chờ review và merge

## Đóng góp
Mọi đóng góp đều được hoan nghênh! Vui lòng fork repository và gửi pull request.

## Giấy phép
Dự án này được cấp phép theo MIT License.

