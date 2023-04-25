package com.example.domain;

import lombok.Data;

/**
 * @ClassName: TokenParam
 * @Description:
 * @Author: 刘苏义
 * @Date: 2023年04月25日13:49
 * @Version: 1.0
 **/
@Data
public class Param {
    String format;
    long rate;
    Integer channel;
    String token;
    String cuid;
    long dev_pid;
    String speech;
    long len;
}
