package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class User {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "username")
    private String username;
    @Column(name = "full_name")
    private String fullName;

    public User() {
        this.userId = 0;
        this.username = "";
        this.fullName = "";
    }

    public User(int userId, String username, String fullName) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
}
