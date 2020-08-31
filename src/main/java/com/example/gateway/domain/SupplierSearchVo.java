package com.example.gateway.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Created by hrhrh on 2020/8/5 17:55
 */
@Data
public class SupplierSearchVo {

    private String supplierName;

    private String supplierNo;

    private String supplierType;

    private String supplyGrade;

    private String status;

    private String key;

    private Page<SupplierListDto> page;
}


