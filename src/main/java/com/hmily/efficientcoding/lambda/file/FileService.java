package com.hmily.efficientcoding.lambda.file;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName FileService
 * @Description 文件服务类
 * @Author Hmily
 * @Date 2019/10/8 12:34
 **/
public class FileService {


    public void fileHandle(String url, FileConsumer fileConsumer) throws IOException {
//        创建文件读取流
        FileInputStream fileInputStream = new FileInputStream(url);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//        定义行变量和内容sb
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line + "\n");
        }

        fileConsumer.fileHandler(stringBuilder.toString());
    }

}
