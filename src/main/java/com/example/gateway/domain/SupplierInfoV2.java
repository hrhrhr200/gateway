package com.example.gateway.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * supplier_info_V2
 * @author 
 */
public class SupplierInfoV2 implements Serializable {
    /**
     * 供应商id
     */
    private String id;

    /**
     * 供应商no
     */
    private String supplierNo;

    /**
     * 供应商全称
     */
    private String supplierName;

    /**
     * 供应商简称
     */
    private String abbr;

    /**
     * 供货类型 代销agency,经销dist,联营 alliance
     */
    private String supplyType;

    /**
     * 供应商等级
     */
    private String supplyGrade;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 地址
     */
    private String address;

    /**
     * 税务登记名称
     */
    private String taxName;

    /**
     * 税号
     */
    private String taxNumber;

    /**
     * 税务电话
     */
    private String taxMobile;

    /**
     * 税务地址
     */
    private String taxAddress;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 创建操作员
     */
    private String createUser;

    /**
     * 操作员名称
     */
    private String createUserName;

    /**
     * 修改操作员
     */
    private String updateUser;

    /**
     * 修改操作人名称
     */
    private String updateUserName;

    private Date createDate;

    private Date updateDate;

    /**
     * 供应商状态 normal ban
     */
    private String status;

    /**
     * 是否使用供应商系统 yes no
     */
    private String useSupplySys;

    /**
     * 商户号
     */
    private String merchId;

       /**
     * 资质图片文件
     */
    private String resourcePic;

    /**
     * 采购合同文件
     */
    private String purchaseFile;

    /**
     * 是否意=一件代发
     */
    private Integer proxy;

    public Integer getProxy() {
        return proxy;
    }

    public void setProxy(Integer proxy) {
        this.proxy = proxy;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public String getSupplyGrade() {
        return supplyGrade;
    }

    public void setSupplyGrade(String supplyGrade) {
        this.supplyGrade = supplyGrade;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTaxMobile() {
        return taxMobile;
    }

    public void setTaxMobile(String taxMobile) {
        this.taxMobile = taxMobile;
    }

    public String getTaxAddress() {
        return taxAddress;
    }

    public void setTaxAddress(String taxAddress) {
        this.taxAddress = taxAddress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseSupplySys() {
        return useSupplySys;
    }

    public void setUseSupplySys(String useSupplySys) {
        this.useSupplySys = useSupplySys;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }
    
     public String getResourcePic() {
        return resourcePic;
    }

    public void setResourcePic(String resourcePic) {
        this.resourcePic = resourcePic;
    }

    public String getPurchaseFile() {
        return purchaseFile;
    }

    public void setPurchaseFile(String purchaseFile) {
        this.purchaseFile = purchaseFile;
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
        SupplierInfoV2 other = (SupplierInfoV2) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSupplierNo() == null ? other.getSupplierNo() == null : this.getSupplierNo().equals(other.getSupplierNo()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getAbbr() == null ? other.getAbbr() == null : this.getAbbr().equals(other.getAbbr()))
            && (this.getSupplyType() == null ? other.getSupplyType() == null : this.getSupplyType().equals(other.getSupplyType()))
            && (this.getSupplyGrade() == null ? other.getSupplyGrade() == null : this.getSupplyGrade().equals(other.getSupplyGrade()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTaxName() == null ? other.getTaxName() == null : this.getTaxName().equals(other.getTaxName()))
            && (this.getTaxNumber() == null ? other.getTaxNumber() == null : this.getTaxNumber().equals(other.getTaxNumber()))
            && (this.getTaxMobile() == null ? other.getTaxMobile() == null : this.getTaxMobile().equals(other.getTaxMobile()))
            && (this.getTaxAddress() == null ? other.getTaxAddress() == null : this.getTaxAddress().equals(other.getTaxAddress()))
            && (this.getBankAccount() == null ? other.getBankAccount() == null : this.getBankAccount().equals(other.getBankAccount()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateUserName() == null ? other.getUpdateUserName() == null : this.getUpdateUserName().equals(other.getUpdateUserName()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUseSupplySys() == null ? other.getUseSupplySys() == null : this.getUseSupplySys().equals(other.getUseSupplySys()))
            && (this.getMerchId() == null ? other.getMerchId() == null : this.getMerchId().equals(other.getMerchId()))
            && (this.getResourcePic() == null ? other.getResourcePic() == null : this.getResourcePic().equals(other.getResourcePic()))
            && (this.getPurchaseFile() == null ? other.getPurchaseFile() == null : this.getPurchaseFile().equals(other.getPurchaseFile()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSupplierNo() == null) ? 0 : getSupplierNo().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getAbbr() == null) ? 0 : getAbbr().hashCode());
        result = prime * result + ((getSupplyType() == null) ? 0 : getSupplyType().hashCode());
        result = prime * result + ((getSupplyGrade() == null) ? 0 : getSupplyGrade().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTaxName() == null) ? 0 : getTaxName().hashCode());
        result = prime * result + ((getTaxNumber() == null) ? 0 : getTaxNumber().hashCode());
        result = prime * result + ((getTaxMobile() == null) ? 0 : getTaxMobile().hashCode());
        result = prime * result + ((getTaxAddress() == null) ? 0 : getTaxAddress().hashCode());
        result = prime * result + ((getBankAccount() == null) ? 0 : getBankAccount().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateUserName() == null) ? 0 : getUpdateUserName().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUseSupplySys() == null) ? 0 : getUseSupplySys().hashCode());
        result = prime * result + ((getMerchId() == null) ? 0 : getMerchId().hashCode());
        result = prime * result + ((getResourcePic() == null) ? 0 : getResourcePic().hashCode());
        result = prime * result + ((getPurchaseFile() == null) ? 0 : getPurchaseFile().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierNo=").append(supplierNo);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", abbr=").append(abbr);
        sb.append(", supplyType=").append(supplyType);
        sb.append(", supplyGrade=").append(supplyGrade);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", taxName=").append(taxName);
        sb.append(", taxNumber=").append(taxNumber);
        sb.append(", taxMobile=").append(taxMobile);
        sb.append(", taxAddress=").append(taxAddress);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankName=").append(bankName);
        sb.append(", createUser=").append(createUser);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateUserName=").append(updateUserName);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", useSupplySys=").append(useSupplySys);
        sb.append(", merchId=").append(merchId);
        sb.append(", resourcePic=").append(resourcePic);
        sb.append(", purchaseFile=").append(purchaseFile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}