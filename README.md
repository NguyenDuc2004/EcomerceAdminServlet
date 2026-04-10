# 🛒 E-Commerce Admin System (Java Servlet)

Dự án quản lý người dùng và hệ thống bán hàng đơn giản sử dụng công nghệ Java Servlet, JSP và MySQL.

## 🚀 Tính năng chính
* Quản lý tài khoản (CRUD): Thêm, Sửa, Xóa, Xem chi tiết.
* Phân quyền người dùng (ADMIN, STAFF, USER).
* Tìm kiếm và lọc người dùng theo vai trò, trạng thái.
* Giao diện hiện đại với Bootstrap 5 và hiệu ứng Glassmorphism.
* Hệ thống thông báo (Toast notification) thân thiện.

## 🛠 Công nghệ sử dụng
* **Backend:** Java Servlet, JDBC, MySQL Connector.
* **Frontend:** JSP, JSTL, Bootstrap 5, Bootstrap Icons.
* **Database:** MySQL 8.0.
* **Server:** Apache Tomcat 9.0.

## 📋 Hướng dẫn cài đặt

### 1. Cấu trúc Database
* File script nằm trong thư mục: `/database/ecommerce.sql`.
* Mở **MySQL Workbench**, chọn `File -> Open SQL Script...` và chọn file trên.
* Bấm **Execute** (hình tia sét) để khởi tạo Schema và dữ liệu mẫu.

### 2. Cấu hình kết nối (Java)
* Mở file `src/main/java/.../dbConnection/DbConnection.java`.
* Kiểm tra và sửa lại `USER` và `PASSWORD` cho đúng với cấu hình MySQL trên máy của bạn.
* Đảm bảo `JDBC_URL` đã có tham số `characterEncoding=UTF-8` để tránh lỗi font.

```java
public static final String USER = "root";
public static final String PASSWORD = "your_password";