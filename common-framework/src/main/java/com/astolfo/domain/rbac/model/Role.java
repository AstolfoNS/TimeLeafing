package com.astolfo.domain.rbac.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role {

    @Setter
    private Long id;

    private String name;

    @Setter
    private String description;

    private boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDeleted;


    public Role(String name, String description) {
        this.id = null;

        this.name = name;

        this.description = description;
    }
    
    public void updateDescription(String description) {
        setDescription(description);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : 0;
    }

}
