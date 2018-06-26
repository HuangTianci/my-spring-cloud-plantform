package com.huangtianci.commonfunction.uploadanddownload.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huangtianci.commonfunction.common.exception.ServiceException;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.TaxQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.Tax;
import com.huangtianci.commonfunction.uploadanddownload.mapper.TaxMapper;
import com.huangtianci.commonfunction.utils.DateFormatUtil;
import com.huangtianci.commonfunction.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 */
@Service
public class TaxService {

    @Autowired
    TaxMapper taxMapper;

    public Page selectByPage(TaxQueryDTO taxQueryDTO) {
        PageHelper.startPage(taxQueryDTO.getPageNum(), taxQueryDTO.getPageSize(), true);
        List<Tax> taxList = taxMapper.selectByQueryDTO(taxQueryDTO);
        return (Page) taxList;
    }

    public int batchInsert(List<Tax> list) {
        int count = 0;
        resoles(list);
        for (Tax tax: list) {
            count = count + taxMapper.insert(tax);
        }
        return count;
    }

    public List<Tax> selectAll() {
        return taxMapper.selectAll();
    }

    public List<Tax> selectByDate(Date startDate, Date endDate) {
        return taxMapper.selectByDate(startDate, endDate);
    }

    public int getLastVersionInThisMouth() {
        Date current = new Date();
        Date startDate = DateUtil.getMonthStart(current);
        Date endDate = DateUtil.getMonthEnd(current);
        return getLastVersionInThisMouthByDate(startDate, endDate);
    }

    public int getLastVersionInThisMouthByDate(Date startDate, Date endDate) {
        Integer lastVersion = taxMapper.getLastVersion(startDate, endDate);
        if(lastVersion == null) {
            lastVersion = 0;
        }
        return lastVersion;
    }

    public List<Tax> selectThisMouthLastVersionData() {
        Date current = new Date();
        Date startDate = DateUtil.getMonthStart(current);
        Date endDate = DateUtil.getMonthEnd(current);
        int lastVersion = getLastVersionInThisMouthByDate(startDate, endDate);
        Tax tax = new Tax();
        tax.setVersion(lastVersion);
        return taxMapper.select(tax);
    }

    /**
     * 算出本次应该缴纳的税费
     * @param taxList
     */
    public void resoles(List<Tax> taxList) {
        for (Tax tax : taxList) {
            validator(tax);
            resole(tax);
        }
    }

    /**
     * 验证该条记录是否是当月最后一次导入的记录
     * @param tax
     */
    private void validator(Tax tax) {
        Date endDate = DateUtil.getMonthEnd(tax.getOrderDate());
        List<Tax> taxList = taxMapper.selectByDate(tax.getOrderDate(), endDate);
        if(taxList.size() > 0) {
            StringBuilder sb = new StringBuilder("姓名[");
            sb.append(tax.getName()).append("],身份证号[").append(tax.getIdNumber()).append("],")
                    .append("当月已存在订单时间在")
                    .append(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, tax.getOrderDate()))
                    .append("之后的记录");
            throw new ServiceException(sb.toString());
        }
    }

    public void resole(Tax tax) {

        Date startDate = DateUtil.getMonthStart(tax.getOrderDate());
        Date endDate = DateUtil.getMonthEnd(tax.getOrderDate());
        int lastVersion = getLastVersionInThisMouthByDate(startDate, endDate);
        lastVersion++;

        List<Tax> hisTaxs = taxMapper.selectByIdNumberAndDate(tax.getIdNumber(), startDate, endDate);
        //本月累计发放金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        //本月历史累计扣税金额
        BigDecimal totalHisDeductAmount = BigDecimal.ZERO;
        //本次扣税金额
        BigDecimal thisTimeDeductAmount = BigDecimal.ZERO;
        //本次实发金额
        BigDecimal thisTimePayAmount = BigDecimal.ZERO;
        for(Tax hisTax: hisTaxs) {
            totalAmount = totalAmount.add(hisTax.getAmount());
            totalHisDeductAmount = totalHisDeductAmount.add(hisTax.getThisTimeDeductAmount());
        }
        totalAmount = totalAmount.add(tax.getAmount());

        //本月累计扣税金额
        BigDecimal deductAmount = BigDecimal.ZERO;
        // totalAmount <= 800
        if(totalAmount.compareTo(new BigDecimal("800")) <= 0) {
            thisTimeDeductAmount = BigDecimal.ZERO;
            thisTimePayAmount = totalAmount;
        } else if(totalAmount.compareTo(new BigDecimal("800")) == 1 &&
                totalAmount.compareTo(new BigDecimal("4000")) <= 0 ){
            deductAmount = totalAmount.multiply(new BigDecimal("0.2")).subtract(new BigDecimal("160"));
            thisTimeDeductAmount = deductAmount.subtract(totalHisDeductAmount);
            thisTimePayAmount = tax.getAmount().subtract(thisTimeDeductAmount);
        } else if(totalAmount.compareTo(new BigDecimal("4000")) == 1 &&
                totalAmount.compareTo(new BigDecimal("25000")) <= 0) {
            deductAmount = totalAmount.multiply(new BigDecimal("0.16"));
            thisTimeDeductAmount = deductAmount.subtract(totalHisDeductAmount);
            thisTimePayAmount = tax.getAmount().subtract(thisTimeDeductAmount);
        } else if(totalAmount.compareTo(new BigDecimal("25000")) == 1 &&
                totalAmount.compareTo(new BigDecimal("65000")) <= 0) {
            deductAmount = totalAmount.multiply(new BigDecimal("0.24")).subtract(new BigDecimal("2000"));
            thisTimeDeductAmount = deductAmount.subtract(totalHisDeductAmount);
            thisTimePayAmount = tax.getAmount().subtract(thisTimeDeductAmount);
        } else if(totalAmount.compareTo(new BigDecimal("65000")) == 1) {
            deductAmount = totalAmount.multiply(new BigDecimal("0.32")).subtract(new BigDecimal("7000"));
            thisTimeDeductAmount = deductAmount.subtract(totalHisDeductAmount);
            thisTimePayAmount = tax.getAmount().subtract(thisTimeDeductAmount);
        }

        tax.setTotalAmount(totalAmount);
        tax.setThisTimeDeductAmount(thisTimeDeductAmount);
        tax.setThisTimePayAmount(thisTimePayAmount);
        tax.setTotalDeductAmount(deductAmount);
        tax.setVersion(lastVersion);
    }
}
