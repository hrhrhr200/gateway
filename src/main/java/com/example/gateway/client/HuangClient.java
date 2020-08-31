package com.example.gateway.client;

import com.baitao.common.response.PageResult;
import com.baitao.common.response.Result;
import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.callback.OnError;
import com.dtflys.forest.callback.OnSuccess;
import com.example.gateway.domain.SupplierListDto;
import com.example.gateway.domain.SupplierSearchVo;
import com.example.gateway.domain.SupplierVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hrhrh on 2020/8/17 11:26
 */
@BaseRequest(baseURL = "172.16.4.15:8000/api/v2/basics",contentType = "application/json", contentEncoding = "UTF-8", headers = {"Authorization:whosyourdaddy"})
public interface HuangClient {


    @Request(url = "/V2/supplier/supplierList",type = "POST",dataType = "json")
    Result<PageResult<SupplierListDto>> supplierList(@DataObject SupplierSearchVo s);

    @Request(url = "/V2/supplier/findOne", dataType = "json")
    Result<SupplierVo> findOne(@DataParam("id") String id, OnSuccess<Result<SupplierVo>> success, OnError error);

}
