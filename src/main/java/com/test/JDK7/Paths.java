package com.test.JDK7;

import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Paths {

    private static String TEMP_PATH = "/Users/jiahangchun/Desktop";


    //ffmpeg 几个基本命令
    //-i  输入的源文件地址
    //-r 设置帧率
    //-vf 创建过滤图。scale 就是在指缩放的比例；ass 用于字幕的输入
    //-y 指的是覆盖输出文件
    //-an 去掉音频
    //-vn 不做视频记录
    //-scodec 设置字幕code
    //-sn 禁止字幕录制
    //copy 拷贝所有流
    public static void main(String[] args) throws Exception {
        String videoPath = java.nio.file.Paths.get(TEMP_PATH).resolve("love.gif").toString();
        String gifPath = java.nio.file.Paths.get(TEMP_PATH).resolve(UUID.randomUUID() + ".gif").toString();
        String assPath = genAssFile();
        System.out.println("gifPath=" + gifPath + "\r\n" + "assFiles=" + assPath);
        String cmd = String.format("ffmpeg -i %s -r 5 -vf ass=%s,scale=180:-1 -y %s ", videoPath, assPath, gifPath);
        try {
            Process exec = Runtime.getRuntime().exec(cmd);
            exec.waitFor();
        } catch (Exception e) {
            System.err.println("ffmpeg使用出错=" + e.getMessage());
        }
    }

    /**
     * 生成字幕文件
     * ass 文件指的是 视频字幕文件： http://www.360doc.com/content/15/0808/11/19994993_490288265.shtml
     * [Script Info] PlayResX & PlayResY 描述字幕的范围 。 至于其他的都只是一些基本信息，可以删除的
     * [V4+ Styles] 主要用于描述 字体信息
     * [Events] 字幕的配置：格式如 Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
     * Layer    一般都是0
     * Start    字幕开始时间
     * End      字幕结束时间
     * Style    字体
     * Name     没什么作用的，相当于备注
     * MarginL  针对V4+ Styles]的MarginL再次定义 0000表示使用默认值
     * MarginR  同上
     * MarginV  同上
     * Effect   字幕出现的方式 如何滚动出现
     * Text     字幕
     *
     * @return
     * @throws IOException
     */
    public static String genAssFile() throws IOException {
        Path path = java.nio.file.Paths.get(TEMP_PATH).resolve(UUID.randomUUID().toString().replace("-", "") + ".ass");
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(java.nio.file.Paths.get("/Users/jiahangchun/IdeaProjects/bak/mybatisTest").resolve("template").toFile());
        Map<String, Object> root = new HashMap<>();
        Map<String, String> mx = new HashMap<>();
        List<String> words = Lists.newArrayList("啦啦啦啦啦，我是字幕", "1", "2", "3", "4", "5", "6", "7", "8");
        for (int i = 0; i < words.size(); i++) {
            mx.put("sentences" + i, words.get(i));
        }
        root.put("mx", mx);
        Template temp = cfg.getTemplate("template.ftl");
        try (FileWriter writer = new FileWriter(path.toFile())) {
            temp.process(root, writer);
        } catch (Exception e) {
            System.err.println("生成ass文件报错");
        }
        return path.toString();
    }


    //TODO
    //1.如何生成没有字幕的gif
    //2.ass文件怎么生成处理？
    //3.视频转gif https://segmentfault.com/a/1190000018940067
}
