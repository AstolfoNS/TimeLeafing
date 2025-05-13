package com.astolfo.domain.rbac.model;

import com.astolfo.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.rbac.model.valueobject.enumtype.Gender;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PRIVATE)
@Getter
public class User {

    private Long id;

    private Email email;

    private String username;

    private String password;

    private String avatar;

    private Gender gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public User(
            Email email,
            String username,
            String password
    ) {
        setEmail(email);

        setUsername(username);

        setPassword(password);

        setGender(Gender.UNKNOWN);

        setEnabled(true);

        setCreateTime(LocalDateTime.now());

        setUpdateTime(LocalDateTime.now());

        setIsDeleted(false);
    }

    public User(
            Email email,
            String password
    ) {
        setEmail(email);

        setPassword(password);
    }
    
    public User(
             String username,
             String password
    ) {
        setUsername(username);

        setPassword(password);
    }

    public void updateEmail(Email email) {
        // TODO 修改邮箱
    }

    public void updateUsername(String username) {
        // TODO 修改用户名
    }

    public void updatePassword(String password) {
        // TODO 修改密码
    }

    public void updateProfile(
            String avatar,
            Gender gender,
            String introduction
    ) {
        setAvatar(avatar);

        setGender(gender);

        setIntroduction(introduction);
    }

    public void markAsLoggedIn() {
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