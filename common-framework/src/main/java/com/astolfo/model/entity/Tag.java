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
    private Long tagId;

    private String tagName;

    private Date tagDate;

    private Boolean isDeleted;

}
