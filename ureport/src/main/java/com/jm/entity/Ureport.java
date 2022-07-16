package com.jm.entity;

import java.util.Date;
import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author caozhenhao
 * @since 2022-07-15
 */
@Data
public class Ureport implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private byte[] content;

    private Date createTime;

    private Date updateTime;


}
