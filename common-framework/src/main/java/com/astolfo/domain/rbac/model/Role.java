package com.astolfo.domain.rbac.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PRIVATE)
@Getter
public class Role {

    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public Role(String name) {
        setName(name);

        setEnabled(true);

        setCreateTime(LocalDateTime.now());

        setUpdateTime(LocalDateTime.now());

        setIsDeleted(false);
    }

    public void updateName(String name) {
        // TODO 修改角色名称
    }

    public void updateDescription(String description) {
        setDescription(description);
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
