# **Realtime Chat App - Tính Năng Chính & API Endpoints**

## **📌 Các Tính Năng Chính**  

### **1. Xác Thực & Phân Quyền (Auth Service)**  
- **Đăng nhập/Đăng ký** bằng OAuth2 (Google, Facebook) hoặc email/mật khẩu  
- **JWT Token** để bảo mật API  
- **Quản lý người dùng** (user profile, đổi mật khẩu)  
- **Phân quyền** (Admin, User)  

### **2. Chat Realtime (Chat Service)**  
- **Nhắn tin realtime** giữa người dùng (WebSocket)  
- **Tạo phòng chat** (1-1 hoặc nhóm)  
- **Lịch sử tin nhắn** (lưu trữ trong MongoDB)  
- **Thông báo realtime** (khi có tin nhắn mới)  
- **Tìm kiếm người dùng & đoạn chat**  
- **Gửi file & hình ảnh**  

### **3. Quản Lý (Admin Dashboard - FE)**  
- **Thống kê người dùng & tin nhắn**  
- **Quản lý phòng chat** (xóa, khóa phòng)  
- **Quản lý người dùng** (block, xóa tài khoản)  

---

## **🔌 API Endpoints**  

### **1. Auth Service (PostgreSQL) - Port 8081**  
| **Endpoint** | **Method** | **Mô Tả** | **Yêu Cầu Auth** |
|-------------|-----------|-----------|----------------|
| `/api/auth/signup` | POST | Đăng ký tài khoản | ❌ |
| `/api/auth/login` | POST | Đăng nhập (JWT token) | ❌ |
| `/api/auth/oauth2/google` | GET | Đăng nhập bằng Google | ❌ |
| `/api/auth/oauth2/facebook` | GET | Đăng nhập bằng Facebook | ❌ |
| `/api/auth/me` | GET | Lấy thông tin user hiện tại | ✔ (Bearer Token) |
| `/api/auth/update-profile` | PUT | Cập nhật thông tin user | ✔ |
| `/api/auth/change-password` | PUT | Đổi mật khẩu | ✔ |
| `/api/admin/users` | GET | Danh sách user (Admin) | ✔ (Role: ADMIN) |

### **2. Chat Service (MongoDB) - Port 8082**  
| **Endpoint** | **Method** | **Mô Tả** | **Yêu Cầu Auth** |
|-------------|-----------|-----------|----------------|
| `/api/chat/rooms` | GET | Danh sách phòng chat của user | ✔ |
| `/api/chat/rooms/{roomId}` | GET | Chi tiết phòng chat | ✔ |
| `/api/chat/rooms/create` | POST | Tạo phòng chat (1-1 hoặc nhóm) | ✔ |
| `/api/chat/rooms/{roomId}/messages` | GET | Lấy tin nhắn trong phòng | ✔ |
| `/api/chat/rooms/{roomId}/send` | POST | Gửi tin nhắn (WebSocket: `/chat/{roomId}`) | ✔ |
| `/api/chat/search?query=...` | GET | Tìm kiếm người dùng/đoạn chat | ✔ |
| `/api/chat/upload` | POST | Upload file/hình ảnh | ✔ |
| `/api/admin/chat/rooms` | GET | Quản lý phòng chat (Admin) | ✔ (Role: ADMIN) |

### **3. WebSocket Endpoints**  
| **Endpoint** | **Mô Tả** |
|-------------|-----------|
| `/ws/chat/{roomId}` | Kết nối WebSocket để nhận tin nhắn realtime |
| `/topic/notifications/{userId}` | Nhận thông báo realtime (tin nhắn mới) |

---

## **🛠 Cách Kiểm Thử API**  
1. **Postman/Insomnia**:  
   - Import **Swagger JSON** từ `http://localhost:8081/v3/api-docs` (Auth) và `http://localhost:8082/v3/api-docs` (Chat)  
   - Thêm **Bearer Token** sau khi đăng nhập.  

2. **WebSocket Testing**:  
   - Dùng **WebSocket Client** (Postman hoặc trình duyệt) kết nối tới `ws://localhost:8082/ws/chat/{roomId}`  

3. **Frontend**:  
   - Chạy React app (`npm start`) và kiểm tra giao diện chat.  

---

## **📂 Cấu Trúc Database**  
### **PostgreSQL (Auth Service)**  
- **Users** (`id`, `username`, `email`, `password`, `role`, `avatar_url`)  
- **OAuth2 Clients** (`client_id`, `client_secret`)  

### **MongoDB (Chat Service)**  
- **ChatRooms** (`roomId`, `name`, `members[]`, `createdAt`)  
- **Messages** (`messageId`, `roomId`, `senderId`, `content`, `timestamp`, `attachments[]`)  

---

## **🚀 Triển Khai (Deployment)**  
1. **Docker**:  
   ```bash
   docker-compose up --build
   ```
2. **AWS/GCP**:  
   - Sử dụng **ECS/EKS** (Backend) + **S3/Cloud Storage** (Upload file)  
   - **React App** deploy trên **Vercel/Netlify**  

---

**📌 Lưu Ý**:  
- **CORS** đã được cấu hình sẵn giữa các services.  
- **WebSocket** yêu cầu **JWT Token** trong header khi kết nối.  
- File upload lưu trữ trên **Cloudinary/Local Storage**.  

✅ **Mọi thắc mắc vui lòng mở issue trên GitHub!** 🚀
# **Hướng Dẫn Chi Tiết Clone, Pull, Push Code & Chạy Project**

Dưới đây là hướng dẫn **từng bước chi tiết** để làm việc với dự án Chat Realtime (WebSocket + OAuth2) bao gồm:

1. **Cách Clone code từ GitHub**
2. **Cách Pull/Push code (Git workflow)**
3. **Cách chạy project (Backend + Frontend)**
4. **Cách kiểm tra API & WebSocket**

---

## **1️⃣ Cách Clone Code Từ GitHub**

### **Bước 1: Clone từng repository**
Dự án gồm **3 repo riêng**, cần clone từng cái:

```bash
# Clone Auth Service (Spring Boot + PostgreSQL)
git clone https://github.com/nguyen-tuankiet/sorfware-auth-service.git

# Clone Chat Service (Spring Boot + MongoDB + WebSocket)
git clone https://github.com/nguyen-tuankiet/sorfware-message-service.git

# Clone Frontend (React)
git clone https://github.com/your-username/chat-frontend.git
```

### **Bước 2: Kiểm tra cấu trúc thư mục**
Sau khi clone xong, cấu trúc thư mục sẽ như sau:
```
📦 project-root/
├── 📂 chat-auth-service/   (Backend Auth)
├── 📂 chat-service/       (Backend Chat)
└── 📂 chat-frontend/      (React Frontend)
```

---

## **2️⃣ Cách Pull/Push Code (Git Workflow)**

### **📌 Quy trình làm việc với Git**
| **Lệnh** | **Mục đích** |
|----------|-------------|
| `git pull origin main` | Lấy code mới nhất từ `main` branch |
| `git checkout -b feature/new-feature` | Tạo branch mới cho tính năng |
| `git add .` | Thêm file thay đổi |
| `git commit -m "message"` | Commit thay đổi |
| `git push origin feature/new-feature` | Đẩy code lên GitHub |
| `git merge main` | Cập nhật `main` vào branch hiện tại |

### **📌 Ví dụ thực tế: Thêm tính năng mới**
```bash
# B1: Chuyển sang branch main và pull code mới
git checkout main
git pull origin main

# B2: Tạo branch mới
git checkout -b feature/add-dark-mode

# B3: Code tính năng mới, sau đó commit
git add .
git commit -m "Thêm chế độ dark mode cho frontend"

# B4: Push lên GitHub
git push origin feature/add-dark-mode

# B5: Vào GitHub tạo Pull Request (PR) để merge vào `main`
```

---

## **3️⃣ Cách Chạy Project (Local Development)**

### **📌 Yêu cầu trước khi chạy**
- **Java 17+** (Backend)
- **Node.js 16+** (Frontend)
- **MongoDB & PostgreSQL** (Hoặc dùng Docker)

### **📌 Cách 1: Chạy thủ công từng service**
#### **B1: Chạy Auth Service (PostgreSQL)**
```bash
cd chat-auth-service

# Cấu hình database trong `application.properties`
cp src/main/resources/application.example.properties src/main/resources/application.properties

# Build và chạy
./mvnw clean install
./mvnw spring-boot:run
```
→ Truy cập: `http://localhost:8081`

#### **B2: Chạy Chat Service (MongoDB + WebSocket)**
```bash
cd chat-service

# Cấu hình MongoDB & Auth Service URL
cp src/main/resources/application.example.properties src/main/resources/application.properties

# Build và chạy
./mvnw clean install
./mvnw spring-boot:run
```
→ Truy cập: `http://localhost:8082`

#### **B3: Chạy Frontend (React)**
```bash
cd chat-frontend

# Cài đặt dependencies
npm install

# Chạy development server
npm start
```
→ Truy cập: `http://localhost:3000`

---

### **📌 Cách 2: Chạy bằng Docker (Auto start cả 3 services)**
```bash
# Chạy lệnh này ở thư mục gốc (nơi có file docker-compose.yml)
docker-compose up --build
```
→ Sau khi chạy xong, truy cập:
- Frontend: `http://localhost:3000`
- Auth API: `http://localhost:8081`
- Chat API: `http://localhost:8082`

---

## **4️⃣ Kiểm Tra API & WebSocket**

### **📌 Kiểm tra API bằng Swagger**
- **Auth Service**: `http://localhost:8081/swagger-ui.html`
- **Chat Service**: `http://localhost:8082/swagger-ui.html`

### **📌 Test WebSocket bằng Postman**
1. Mở **WebSocket Client** trong Postman
2. Kết nối tới:
   ```
   ws://localhost:8082/ws/chat/{roomId}
   ```
3. Gửi message dạng JSON:
   ```json
   {
     "senderId": "user123",
     "content": "Xin chào!"
   }
   ```

---

## **📌 Lưu Ý Quan Trọng**
1. **Luôn pull code mới nhất trước khi làm việc**:
   ```bash
   git pull origin main
   ```
2. **Nếu gặp lỗi dependency**:
   - Backend: `./mvnw clean install`
   - Frontend: `npm install`
3. **Khi gặp lỗi database**:
   - Kiểm tra file `application.properties` đã đúng thông tin kết nối chưa.

---

✅ **Nếu có lỗi gì, hãy mở Issue trên GitHub!** 🚀
