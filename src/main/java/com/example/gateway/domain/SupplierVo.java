package com.example.gateway.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by hrhrh on 2020/8/5 15:46
 */
@Data
public class SupplierVo {

    private SupplierInfoV2 supplierInfoV2;

    private SupplierSettlement supplierSettlement;

    private List<SupplierContact> contactList;
}
