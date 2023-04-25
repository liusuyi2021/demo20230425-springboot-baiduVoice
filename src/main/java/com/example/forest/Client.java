package com.example.forest;

import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.example.domain.Param;

public interface Client {

    //String voiceUrl = "https://vop.baidu.com/pro_api";//极速版
    String voiceUrl = "https://vop.baidu.com/server_api";//标准版
    String ak = "dcCSIuFAkRydVpiL7KvYiQoN";
    String sk = "aaa4d04fe56acd4259dd98c8043e1002";
    String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
    String getAccessTokenUrl = authHost
            // 1. grant_type为固定参数
            + "grant_type=client_credentials"
            // 2. 官网获取的 API Key
            + "&client_id=" + ak
            // 3. 官网获取的 Secret Key
            + "&client_secret=" + sk;

    @Post(url = getAccessTokenUrl, sslProtocol = "TLS")
    String getToken(@JSONBody Param param);

    @Post(url = voiceUrl, sslProtocol = "TLS")
    String voiceRun(@JSONBody Param param);
}
