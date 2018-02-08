package com.huangtianci.lucene.bean.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "common_name_dic")
public class CommonNameDic {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 通用名编码
     */
    @Column(name = "common_code")
    private String commonCode;

    /**
     * 通用名
     */
    @Column(name = "common_name")
    private String commonName;

    /**
     * 成分编码
     */
    @Column(name = "component_code")
    private String componentCode;

    /**
     * 成分
     */
    @Column(name = "component_name")
    private String componentName;

    /**
     * 实际剂型编码
     */
    @Column(name = "actual_dosage_type_code")
    private String actualDosageTypeCode;

    /**
     * 实际剂型
     */
    @Column(name = "actual_dosage_type_name")
    private String actualDosageTypeName;

    /**
     * 标注剂型编码
     */
    @Column(name = "label_dosage_type_code")
    private String labelDosageTypeCode;

    /**
     * 标注剂型
     */
    @Column(name = "label_dosage_type_name")
    private String labelDosageTypeName;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private String isDeleted;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取通用名编码
     *
     * @return common_code - 通用名编码
     */
    public String getCommonCode() {
        return commonCode;
    }

    /**
     * 设置通用名编码
     *
     * @param commonCode 通用名编码
     */
    public void setCommonCode(String commonCode) {
        this.commonCode = commonCode;
    }

    /**
     * 获取通用名
     *
     * @return common_name - 通用名
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * 设置通用名
     *
     * @param commonName 通用名
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * 获取成分编码
     *
     * @return component_code - 成分编码
     */
    public String getComponentCode() {
        return componentCode;
    }

    /**
     * 设置成分编码
     *
     * @param componentCode 成分编码
     */
    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    /**
     * 获取成分
     *
     * @return component_name - 成分
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * 设置成分
     *
     * @param componentName 成分
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * 获取实际剂型编码
     *
     * @return actual_dosage_type_code - 实际剂型编码
     */
    public String getActualDosageTypeCode() {
        return actualDosageTypeCode;
    }

    /**
     * 设置实际剂型编码
     *
     * @param actualDosageTypeCode 实际剂型编码
     */
    public void setActualDosageTypeCode(String actualDosageTypeCode) {
        this.actualDosageTypeCode = actualDosageTypeCode;
    }

    /**
     * 获取实际剂型
     *
     * @return actual_dosage_type_name - 实际剂型
     */
    public String getActualDosageTypeName() {
        return actualDosageTypeName;
    }

    /**
     * 设置实际剂型
     *
     * @param actualDosageTypeName 实际剂型
     */
    public void setActualDosageTypeName(String actualDosageTypeName) {
        this.actualDosageTypeName = actualDosageTypeName;
    }

    /**
     * 获取标注剂型编码
     *
     * @return label_dosage_type_code - 标注剂型编码
     */
    public String getLabelDosageTypeCode() {
        return labelDosageTypeCode;
    }

    /**
     * 设置标注剂型编码
     *
     * @param labelDosageTypeCode 标注剂型编码
     */
    public void setLabelDosageTypeCode(String labelDosageTypeCode) {
        this.labelDosageTypeCode = labelDosageTypeCode;
    }

    /**
     * 获取标注剂型
     *
     * @return label_dosage_type_name - 标注剂型
     */
    public String getLabelDosageTypeName() {
        return labelDosageTypeName;
    }

    /**
     * 设置标注剂型
     *
     * @param labelDosageTypeName 标注剂型
     */
    public void setLabelDosageTypeName(String labelDosageTypeName) {
        this.labelDosageTypeName = labelDosageTypeName;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_created - 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取修改者
     *
     * @return modifier - 修改者
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改者
     *
     * @param modifier 修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}