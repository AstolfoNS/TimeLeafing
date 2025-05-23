package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.Gender;
import com.astolfo.domain.rbac.model.valueobject.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    // 唯一标识
    private UserId id;

    // 基础属性
    private Email email;

    private Username username;

    private Nickname nickname;

    private PasswordHash passwordHash;

    private Avatar avatar;

    private Gender gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    // 生命周期属性
    private List<RoleId> roleIdList;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public void recordLogin() {
        setLastLoginTime(LocalDateTime.now());
    }

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        setEnabled(true);
    }

    public void softDelete() {
        setIsDeleted(true);
        disable();
    }

    public void restore() {
        setIsDeleted(false);
        enable();
    }
}