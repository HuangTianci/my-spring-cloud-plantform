package com.huangtianci.commonfunction.uploadanddownload.bean.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "excel_mapping")
public class ExcelMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    private String name;

    @Column(name = "prop_name")
    private String propName;

    @Column(name = "prop_index")
    private String index;

    @Column(name = "prop_type")
    private String propType;

}