package com.example.gateway.web;

import com.baitao.common.response.PageResult;
import com.baitao.common.response.Result;
import com.example.gateway.domain.SupplierListDto;
import com.example.gateway.domain.SupplierSearchVo;
import com.example.gateway.domain.SupplierVo;
import com.example.gateway.services.HuangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by hrhrh on 2020/8/17 14:30
 */
@RestController
@RequestMapping("/huang/v1")
public class HuangController {

    @Resource
    private HuangService huangService;

    @PostMapping("/findSupplier")
    public Result<PageResult<SupplierListDto>> findSupplier (@RequestBody SupplierSearchVo s) {
        return huangService.findSupplier(s);
    }

    @GetMapping("/findOne")
    public ResponseEntity<SupplierVo> findOne (@RequestParam String id) {
        return ResponseEntity.ok(huangService.findOne(id));
    }
}
