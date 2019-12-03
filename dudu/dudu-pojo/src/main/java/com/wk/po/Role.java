package com.wk.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Integer id;

    private String rname;

    private String remark;

    private Short status;

    private static final long serialVersionUID = 1L;

}