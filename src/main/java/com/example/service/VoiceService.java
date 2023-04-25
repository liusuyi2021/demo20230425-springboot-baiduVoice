package com.example.service;

import java.io.IOException;

/**
 * @ClassName: voiceService
 * @Description:
 * @Author: 刘苏义
 * @Date: 2023年04月25日13:59
 * @Version: 1.0
 **/

public interface VoiceService {
    String getToken();
    String VoiceRun(String path) throws IOException;
}
