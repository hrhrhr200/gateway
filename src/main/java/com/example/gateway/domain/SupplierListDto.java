package com.example.gateway.domain;

import lombok.Data;

/**
 * Created by hrhrh on 2020/8/6 9:59
 */
@Data
public class SupplierListDto extends SupplierInfoV2 {


    private Integer goodsCount = 0;

    private String showSupplierType;

    private String showStatus;


    public String getShowSupplierType() {
        return SupplierTypeEnum.getValue(this.getSupplyType());
    }

    public String getShowStatus() {
        if("normal".equals(this.getStatus())) {
            return "已启用";
        }

        return "已禁用";
    }
}
