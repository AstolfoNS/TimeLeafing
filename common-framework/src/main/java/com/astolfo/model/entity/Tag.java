package com.astolfo.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag {

    @TableId
    private Long id;

    private String name;

    private Boolean enabled;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

}
