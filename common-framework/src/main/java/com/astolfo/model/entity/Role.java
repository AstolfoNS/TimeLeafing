package com.astolfo.model.entity;


import lombok.Data;

import java.util.Date;

public class Role {

    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

}
