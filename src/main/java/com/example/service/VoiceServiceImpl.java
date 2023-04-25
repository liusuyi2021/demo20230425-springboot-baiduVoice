package com.example.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.domain.Param;
import com.example.forest.Client;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Resource
    private Client client;

    @Override
    public String getToken() {
        Param param = new Param();
        param.setFormat("war");
        param.setChannel(1);
        param.setRate(16000);
        String tokenJson = client.getToken(param);
        JSONObject jsonObject = JSONObject.parseObject(tokenJson);
        return (String) jsonObject.get("access_token");
    }

    @Override
    public String VoiceRun(String path) {
        String result = "";
        Param param = new Param();
        param.setFormat("wav");
        param.setChannel(1);
        param.setRate(16000);
        // String token = getToken();
        param.setToken("24.7fc2976d1818ac5848c6223642097a60.2592000.1684998759.282335-10521231");
        param.setCuid("ard");
        param.setDev_pid(1537);//标准版普通话(纯中文识别)1537  极速版普通话80001
        String fileContentAsBase64 = getFileContentAsBase64(path);
        byte[] content = new byte[0];
        try {
            content = getFileContent(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        param.setLen(content.length);
        param.setSpeech(fileContentAsBase64);
        String resJson = client.voiceRun(param);
        System.out.println(resJson);
        JSONObject jsonObject = JSONObject.parseObject(resJson);
        result = JSON.toJSONString(jsonObject.get("result"));

        return result;
    }

    /**
     * 获取文件base64编码
     *
     * @param path 文件路径
     * @return base64编码信息，不带文件头
     * @throws IOException IO异常
     */
    static String getFileContentAsBase64(String path) {
        byte[] b = new byte[0];
        try {
            b = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 获取文件base64 UrlEncode编码
     *
     * @param path 文件路径
     * @return base64编码信息，不带文件头
     * @throws IOException IO异常
     */
    static String getFileContentAsBase64Urlencoded(String path) {
        try {
            return URLEncoder.encode(getFileContentAsBase64(path), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private byte[] getFileContent(String filename) throws IOException {
        File file = new File(filename);
        if (!file.canRead()) {
            System.err.println("文件不存在或者不可读: " + file.getAbsolutePath());
        }
        FileInputStream is = new FileInputStream(file);
        byte[] byt = new byte[is.available()];
        is.read(byt);
        is.close();
        return byt;
    }
}