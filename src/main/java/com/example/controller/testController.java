package com.example.controller;

import com.example.service.VoiceService;
import com.example.util.IKAnalyzerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: testController
 * @Description:
 * @Author: 刘苏义
 * @Date: 2023年04月25日13:38
 * @Version: 1.0
 **/
@RestController
@Slf4j
public class testController {
    @Autowired
    private VoiceService voiceService;

    @GetMapping("/token")
    String token()
    {
       return voiceService.getToken();
    }

    @PostMapping("/getWord")
    public  List<String> getWord(MultipartFile file) {
        String path = "G:\\voice-ai\\"+new Date().getTime()+ ".wav";
        File localFile = new File(path);
        try {
            int length = file.getBytes().length;
            System.out.println("文件长度"+length);
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename() + " 上传成功");
            // 上传成功，开始解析
            // 开始时间
            long stime = System.currentTimeMillis();
            System.out.println("文件路径:"+path);
            String text = voiceService.VoiceRun(path);
            boolean delete = localFile.delete();
            if(!delete)
            {
                System.out.println("删除文件"+path+"失败！");
            }
            // 结束时间
            long etime = System.currentTimeMillis();
            // 计算执行时间
            log.info("执行时长："+(etime-stime)+"毫秒");
            List<String> list= IKAnalyzerUtil.cut(text);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            localFile.delete();
            return new ArrayList<>();
        }
    }
}
