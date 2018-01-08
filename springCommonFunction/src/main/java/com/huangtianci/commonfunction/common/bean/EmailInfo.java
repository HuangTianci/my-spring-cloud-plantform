package com.huangtianci.commonfunction.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Huang Tianci
 * @date 2017/11/28
 * 邮件信息
 */
@Data
public class EmailInfo implements Serializable {

    /**
     * 发送者
     */
    private String from;

    /**
     * 接受者
     */
    private String to;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件在oss上的位置，多个附件使用英文逗号分隔
     */
    private String fileLocation;

    /**
     * 发送标识
     */
    private String sendFlag;

}
