package com.qzhp.entity.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * description
 *
 * @author qcb
 * @date 2022/05/28 14:08.
 */
public class UserLogin implements Serializable {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

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
}
