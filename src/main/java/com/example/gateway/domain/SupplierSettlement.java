package com.example.gateway.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * supplier_settlement
 * @author 
 */
public class SupplierSettlement implements Serializable {
    private String id;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 供货类型 代销agency,经销dist,联营 alliance
     */
    private String supplierType;

    /**
     * 结算方式  666:先货后款  888:先款后货
     */
    private Integer settlementType;

    /**
     * 账期
     */
    private Short accountPeriod;

    /**
     * 结算周期  210:按月 220:按周
     */
    private Integer settlementPeriod;

    /**
     * 结算日
     */
    private String settlementDay;

    /**
     * 结算开始日期
     */
    private Date settlementStartDay;

    /**
     * 默认提成比率
     */
    private Integer defaultCommissionRate;

    /**
     * 预付比例
     */
    private String prePayPercent;

    public String getPrePayPercent() {
        return prePayPercent;
    }

    public void setPrePayPercent(String prePayPercent) {
        this.prePayPercent = prePayPercent;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public Short getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Short accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public Integer getSettlementPeriod() {
        return settlementPeriod;
    }

    public void setSettlementPeriod(Integer settlementPeriod) {
        this.settlementPeriod = settlementPeriod;
    }

    public String getSettlementDay() {
        return settlementDay;
    }

    public void setSettlementDay(String settlementDay) {
        this.settlementDay = settlementDay;
    }

    public Date getSettlementStartDay() {
        return settlementStartDay;
    }

    public void setSettlementStartDay(Date settlementStartDay) {
        this.settlementStartDay = settlementStartDay;
    }

    public Integer getDefaultCommissionRate() {
        return defaultCommissionRate;
    }

    public void setDefaultCommissionRate(Integer defaultCommissionRate) {
        this.defaultCommissionRate = defaultCommissionRate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SupplierSettlement other = (SupplierSettlement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSupplierType() == null ? other.getSupplierType() == null : this.getSupplierType().equals(other.getSupplierType()))
            && (this.getSettlementType() == null ? other.getSettlementType() == null : this.getSettlementType().equals(other.getSettlementType()))
            && (this.getAccountPeriod() == null ? other.getAccountPeriod() == null : this.getAccountPeriod().equals(other.getAccountPeriod()))
            && (this.getSettlementPeriod() == null ? other.getSettlementPeriod() == null : this.getSettlementPeriod().equals(other.getSettlementPeriod()))
            && (this.getSettlementDay() == null ? other.getSettlementDay() == null : this.getSettlementDay().equals(other.getSettlementDay()))
            && (this.getSettlementStartDay() == null ? other.getSettlementStartDay() == null : this.getSettlementStartDay().equals(other.getSettlementStartDay()))
            && (this.getDefaultCommissionRate() == null ? other.getDefaultCommissionRate() == null : this.getDefaultCommissionRate().equals(other.getDefaultCommissionRate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierType() == null) ? 0 : getSupplierType().hashCode());
        result = prime * result + ((getSettlementType() == null) ? 0 : getSettlementType().hashCode());
        result = prime * result + ((getAccountPeriod() == null) ? 0 : getAccountPeriod().hashCode());
        result = prime * result + ((getSettlementPeriod() == null) ? 0 : getSettlementPeriod().hashCode());
        result = prime * result + ((getSettlementDay() == null) ? 0 : getSettlementDay().hashCode());
        result = prime * result + ((getSettlementStartDay() == null) ? 0 : getSettlementStartDay().hashCode());
        result = prime * result + ((getDefaultCommissionRate() == null) ? 0 : getDefaultCommissionRate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierType=").append(supplierType);
        sb.append(", settlementType=").append(settlementType);
        sb.append(", accountPeriod=").append(accountPeriod);
        sb.append(", settlementPeriod=").append(settlementPeriod);
        sb.append(", settlementDay=").append(settlementDay);
        sb.append(", settlementStartDay=").append(settlementStartDay);
        sb.append(", defaultCommissionRate=").append(defaultCommissionRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}