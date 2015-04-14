package demo.vo;

/**
 * TODO: 这里需要写注释
 */
public class AuthParam {
    private String tenantId;
    private String userId;
    private String password;
    private long expired;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("AuthParam{");
        sb.append("tenantId='").append(tenantId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", expired=").append(expired);
        sb.append('}');
        return sb.toString();
    }
}
