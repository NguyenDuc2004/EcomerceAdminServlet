package org.example.adminecomerce.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String roleName;
    private int roleId;
    private int status;// 1: Active, 0: Blocked
    private String createdAt;

    public User() {
    }

    public User(int id, String username, String fullname, String email, String roleName, int status) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.roleName = roleName;
        this.status = status;
    }


    // 2. Constructor đầy đủ (Dùng khi lấy dữ liệu TỪ Database lên - SELECT)
    public User(int id, String username, String fullname, String email, String phone, String address, String roleName,String createdAt, int status) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.status = status;
    }


    public User(int id, String fullname, String email, String phone, String address, int roleId, int status) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 3. Constructor dùng khi Thêm mới hoặc Cập nhật (Sử dụng roleId thay vì roleName)
    public User(String username, String password, String fullname, String email, int roleId) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.roleId = roleId;
        this.status = 1; // Mặc định mới tạo là Active
    }

    public User(int id, String username, String email, String roleName, int status, String create_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roleName = roleName;
        this.status = status;
        this.createdAt = create_at;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    //kiem tra xem co phai admin
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.roleName) || "STAFF".equalsIgnoreCase(this.roleName);
    }

    //kiem tra trang thai tai khoan
    public boolean isActive() {
        return this.status == 1;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    // Ghi đè toString để dễ dàng Debug khi cần in đối tượng ra console
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", role='" + roleName + '\'' + '}';
    }
}