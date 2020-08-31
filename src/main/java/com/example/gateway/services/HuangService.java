package com.example.gateway.services;

import com.alibaba.fastjson.JSON;
import com.baitao.common.response.PageResult;
import com.baitao.common.response.Result;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.example.gateway.client.HuangClient;
import com.example.gateway.domain.SupplierListDto;
import com.example.gateway.domain.SupplierSearchVo;
import com.example.gateway.domain.SupplierVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hrhrh on 2020/8/17 14:29
 */
@Service
@Slf4j
public class HuangService {

    @Resource
    private HuangClient huangClient;

    public Result<PageResult<SupplierListDto>> findSupplier (SupplierSearchVo s) {
       return huangClient.supplierList(s);
    }

    public SupplierVo findOne(String id) {
        Result<SupplierVo> one = huangClient.findOne(id, (Result<SupplierVo> r, ForestRequest request, ForestResponse response) -> {
            log.info("result:{}", JSON.toJSONString(r.getData()));
            log.info("codes:{}", response.getStatusCode());
        }, (ForestRuntimeException ex, ForestRequest request, ForestResponse response) -> {
            log.error("ex", ex);
        });

        return one.getData();
    }
}
