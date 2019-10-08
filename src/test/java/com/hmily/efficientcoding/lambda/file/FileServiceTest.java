package com.hmily.efficientcoding.lambda.file;

import com.hmily.efficientcoding.EfficientCodingApplicationTests;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileServiceTest extends EfficientCodingApplicationTests {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();

        String filePath = "D:\\test\\code\\efficient-coding\\src\\main\\java\\com\\hmily\\efficientcoding\\lambda\\file\\FileService.java";
        fileService.fileHandle(filePath, System.out::println);
    }
}