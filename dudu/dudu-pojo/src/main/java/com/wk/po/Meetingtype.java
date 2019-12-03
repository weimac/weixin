package com.wk.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Meetingtype implements Serializable {
    private Integer id;

    private String tname;

    private String remark;

    private Short status;

    private Integer sortnum;

    private static final long serialVersionUID = 1L;

}