package org.example.constants;

public enum UserStatus {
    ACTIVE(0,"Tài khoản bình thường"),
    SUSPENDED(1,"Tài khoản bị khóa"),
    BANNED(2,"Tài khoản bị cấm");

    private final Integer status;
    private final String description;

    UserStatus(Integer status, String description){
        this.status=status;
        this.description=description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
