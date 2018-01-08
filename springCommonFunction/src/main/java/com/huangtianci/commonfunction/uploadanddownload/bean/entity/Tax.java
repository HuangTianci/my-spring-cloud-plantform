package com.huangtianci.commonfunction.uploadanddownload.bean.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "id_number")
    private String idNumber;

    private BigDecimal amount;

    @Column(name = "order_date")
    private Date orderDate;

    /**
     * 本月累计发放金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 本月累计扣税金额
     */
    @Column(name = "total_deduct_amount")
    private BigDecimal totalDeductAmount;

    /**
     * 本次扣税金额
     */
    @Column(name = "this_time_deduct_amount")
    private BigDecimal thisTimeDeductAmount;

    /**
     * 本次实发金额
     */
    @Column(name = "this_time_pay_amount")
    private BigDecimal thisTimePayAmount;

    private int version;

}