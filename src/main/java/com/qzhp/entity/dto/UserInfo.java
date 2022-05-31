package com.qzhp.entity.dto;

import com.qzhp.common.OperationGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注册用户
 *
 * @author qcb
 * @date 2022/05/28 10:46.
 */
public class UserInfo implements Serializable {

    @NotNull(message = "用户ID不能为空", groups = { OperationGroup.Update.class })
    private Long id;

    @NotEmpty(message = "用户名不能为空", groups = { OperationGroup.Add.class, OperationGroup.Update.class })
    @Length(min = 5, max = 20, message = "用户名长度必须大于等于5且小于等于20",groups = { OperationGroup.Add.class, OperationGroup.Update.class })
    private String username;

    @NotEmpty(message = "密码不能为空", groups = { OperationGroup.Add.class, OperationGroup.Update.class })
    @Length(min = 6, max = 100, message = "用户名长度必须大于等于6且小于等于100",groups = { OperationGroup.Add.class, OperationGroup.Update.class })
    private String password;

    private String icon;

    private String nickname;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
