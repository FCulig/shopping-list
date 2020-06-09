package hr.tvz.andorid.shoppinglist.entities.login;

import com.google.gson.annotations.SerializedName;

public class LoginUserModel {
    private String username;
    private String password;
    @SerializedName("grant_type")
    private String grantType;

    public LoginUserModel(String username, String password) {
        this.username = username;
        this.password = password;
        this.grantType = "password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return "LoginUserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grantType='" + grantType + '\'' +
                '}';
    }
}
