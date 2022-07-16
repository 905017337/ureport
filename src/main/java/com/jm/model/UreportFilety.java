package com.jm.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "ureport_filety")
public class UreportFilety implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private byte[] content;
    private Date createTime;
    private Date updateTime;



}
