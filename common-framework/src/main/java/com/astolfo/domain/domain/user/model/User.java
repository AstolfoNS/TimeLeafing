package com.astolfo.domain.domain.user.model;

import com.astolfo.domain.domain.user.model.valueobject.Email;
import com.astolfo.domain.domain.user.model.valueobject.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    private Long id;

    private String username;

    private String password;

    private Email email;

    private String avatar;

    private String introduction;

    private Gender gender;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    protected User(
            String username,
            String password,
            Email email
    ) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty");
        }

        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }

        this.id = null;

        this.username = username;

        this.password = password;

        this.email = email;

        this.avatar = null;

        this.introduction = null;

        this.gender = Gender.UNKNOWN;

        this.lastLoginTime = null;

        this.enabled = true;

        this.createTime = LocalDateTime.now();

        this.updateTime = LocalDateTime.now();

        this.isDeleted = false;
    }

    public void changePassword(String newPassword) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty");
        }

        // TODO: 更新密码为新密码加密后的密码

        this.updateTime = LocalDateTime.now();
    }

    public void updateProfile(
            String avatar,
            Gender gender,
            String introduction
    ) {
        changeAvatar(avatar);

        changeGender(gender);

        changeIntroduction(introduction);
    }

    public void changeAvatar(String newAvatar) {
        this.avatar = newAvatar;
    }

    public void changeIntroduction(String newIntroduction) {
        this.introduction = newIntroduction;
    }

    public void changeGender(Gender newGender) {
        this.gender = newGender;
    }

    public void markAsLoggedIn() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void softDelete() {
        this.isDeleted = true;
        disable();
    }

    public void restore() {
        this.isDeleted = false;
        enable();
    }
}
