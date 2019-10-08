package com.hmily.efficientcoding.lambda;

import com.hmily.efficientcoding.lambda.cart.CartService;
import com.hmily.efficientcoding.lambda.cart.Sku;
import com.hmily.efficientcoding.lambda.cart.SkuCategoryEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @ClassName StreamOperatorTest
 * @Description 演示流的各种操作
 * @Author Hmily
 * @Date 2019/10/8 16:39
 **/
@Slf4j
public class StreamOperatorTest {

    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * filter使用：过滤掉不符合断言判断的数据
     */
    @Test
    public void filterTest(){
        list.stream()
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .forEach(sku -> log.info("sku: {}", sku));
    }

    /**
     * map使用：将一个元素转换成另一个元素
     */
    @Test
    public void mapTest() {
        list.stream()
                .map(Sku::getSkuName)
                .forEach(s -> log.info("name: {}", s));
    }

    /**
     * flatMap使用：将一个对象转换成流
     */
    @Test
    public void flatMapTest() {
        list.stream()
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(s -> log.info("res: {}", s));
    }

    /**
     * peek使用：对流中元素进行遍历操作，与forEach类似，但不会销毁流元素
     */
    @Test
    public void peek() {
        list.stream()
                .peek(sku -> log.info("p sku: {}", sku))
                .forEach(sku -> log.info("f sku: {}", sku));
    }

    /**
     * sort使用：对流中元素进行排序，可选择自然排序或指定排序规则。有状态操作
     */
    @Test
    public void sortTest() {
        list.stream()
                .peek(sku -> log.info("p sku: {}", sku))
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(sku -> log.info("f sku: {}", sku));
    }

    /**
     * distinct使用：对流元素进行去重。有状态操作
     */
    @Test
    public void distinctTest() {
        list.stream()
                .map(sku -> sku.getSkuCategory())
                .distinct()
                .forEach(s -> log.info("res: {}", s));
    }

    /**
     * skip使用：跳过前N条记录。有状态操作
     */
    @Test
    public void skipTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .forEach(sku -> log.info("sku: {}", sku));
    }

    /**
     * limit使用：截断前N条记录。有状态操作
     */
    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .limit(2)
                .forEach(sku -> log.info("sku: {}", sku));
    }

    /**
     * allMatch使用：终端操作，短路操作。所有元素都匹配成功，返回true
     */
    @Test
    public void allMatchTest() {
        boolean res = list.stream()
                .peek(sku -> log.info("sku: {}", sku))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        log.info("res: {}", res);
    }

    /**
     * anyMatch使用：只要有一个匹配成功，就马上返回true
     */
    @Test
    public void anyMatchTest() {
        boolean res = list.stream()
                .peek(sku -> log.info("sku: {}", sku))
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        log.info("res: {}", res);
    }

    /**
     * noneMatch使用：任何元素都不匹配，返回true
     */
    @Test
    public void noneMatchTest() {
        boolean res = list.stream()
                .peek(sku -> {
//                    sku.setTotalPrice(sku.getTotalPrice() * 100);
                    log.info("p sku: {}", sku);
                })
                .noneMatch(sku -> sku.getTotalPrice() > 100);
        log.info("res: {}", res);
    }

    /**
     * 找到第一个
     */
    @Test
    public void findFirstTest() {
        Sku sku = list.stream()
                .peek(sku1 -> log.info("p sku: {}", sku1))
                .findFirst().get();
        log.info("sku: {}", sku);
    }

    /**
     * 找任意一个
     */
    @Test
    public void findAnyTest() {
        Sku sku = list.stream()
                .peek(sku1 -> log.info("p sku: {}", sku1))
                .findAny().get();
        log.info("sku: {}", sku);log.info("sku: {}", sku);
    }

    /**
     * max使用：
     */
    @Test
    public void maxTest() {
        Sku sku = list.stream()
                .peek(sku1 -> log.info("p sku: {}", sku1))
                .max(Comparator.comparing(Sku::getSkuId)).get();
        log.info("sku: {}", sku);

        double maxPrice = list.stream().mapToDouble(Sku::getTotalPrice).max().getAsDouble();
        log.info("maxPrice: {}", maxPrice);
    }

    /**
     * min使用
     */
    @Test
    public void minTest() {
        OptionalDouble optionalDouble = list.stream()
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * count使用
     */
    @Test
    public void countTest() {
        long count = list.stream()
                .count();

        System.out.println(count);
    }
}
