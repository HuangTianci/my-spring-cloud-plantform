package com.tianci.iframework.usermanager.admin.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huang Tianci
 * 管理员
 */
@Data
public class Admin implements Serializable{

    private long id;

    private String userName;

    private String password;

    private String createUser;

    private String updateUser;

    private Date createDate;

    private Date updateDate;

}
