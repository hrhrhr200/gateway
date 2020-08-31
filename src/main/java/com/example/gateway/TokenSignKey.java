package com.example.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by hrhrh on 2020/4/26 14:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenSignKey {

    @Id
    private String signKey;

    private Date createDate;
}
