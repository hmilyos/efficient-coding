package com.hmily.efficientcoding.lambda.file;

/**
 * @ClassName FileConsumer
 * @Description 文件处理函数式接口
 * @Author ouShiMing
 * @Date 2019/10/8 12:35
 **/
@FunctionalInterface
public interface FileConsumer {

    /**
     * 函数式接口抽象方法
     * @param fileContent - 文件内容字符串
     */
    void fileHandler(String fileContent);

}
