package com.example.gateway.domain;

import java.io.Serializable;

/**
 * supplier_contact
 * @author 
 */
public class SupplierContact implements Serializable {
    private String id;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人姓名
     */
    private String contactDepartment;

    /**
     * 联系人电话
     */
    private String contactMobile;

    /**
     * 联系人邮箱
     */
    private String contactMail;

    /**
     * 联系人固定电话
     */
    private String contactPhone;

    /**
     * 备注
     */
    private String remarks;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactDepartment() {
        return contactDepartment;
    }

    public void setContactDepartment(String contactDepartment) {
        this.contactDepartment = contactDepartment;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        SupplierContact other = (SupplierContact) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactDepartment() == null ? other.getContactDepartment() == null : this.getContactDepartment().equals(other.getContactDepartment()))
            && (this.getContactMobile() == null ? other.getContactMobile() == null : this.getContactMobile().equals(other.getContactMobile()))
            && (this.getContactMail() == null ? other.getContactMail() == null : this.getContactMail().equals(other.getContactMail()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactDepartment() == null) ? 0 : getContactDepartment().hashCode());
        result = prime * result + ((getContactMobile() == null) ? 0 : getContactMobile().hashCode());
        result = prime * result + ((getContactMail() == null) ? 0 : getContactMail().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
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
        sb.append(", contactName=").append(contactName);
        sb.append(", contactDepartment=").append(contactDepartment);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", contactMail=").append(contactMail);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}