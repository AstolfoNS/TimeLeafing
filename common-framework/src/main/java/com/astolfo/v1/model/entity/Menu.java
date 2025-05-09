package com.astolfo.v1.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("enum")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu {

    private Long id;

    private String permission;

    private String description;

    private String url;

    private String method;

    private String type;

    private String order_num;

    private Boolean enabled;

    private Date create_time;

    private Date update_time;

    private Boolean isDeleted;

}
