package com.wk.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Meetinggrab implements Serializable {
    private String id;

    private String pid;

    private String remark;

    private String uid;

    private Date createdate;

    private Integer grabstatus;

    private Date grabdate;

    private Short status;

    private static final long serialVersionUID = 1L;

    //补充meetingpub
    private String ptitle;
    private String pcode;
    private String pzone;
    private String tname;
    private String telephone;

    //补充user
    private String name;
    private String province;
    private String city;


}