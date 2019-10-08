package com.hmily.efficientcoding.lambda.cart;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @ClassName StreamVsOld
 * @Description 对比：原始集合操作与Stream集合操作
 * @Author Hmily
 * @Date 2019/10/8 14:29
 **/
@Slf4j
public class StreamVsOldService {

    /**
     * 1 想看看购物车中都有什么商品
     * 2 图书类商品都给买
     * 3 其余的商品中买两件最贵的
     * 4 只需要两件商品的名称和总价
     */

    public void oldHandle(List<Sku> cartSkuList){

        List<Sku> notBooksSkuList = new ArrayList<Sku>();

        for (Sku sku : cartSkuList) {
            log.info("sku: {}", JSON.toJSONString(sku, true));
            if (!SkuCategoryEnum.BOOKS.getCode().equals(sku.getSkuCategory())){
                notBooksSkuList.add(sku);
            }
        }

        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku sku1, Sku sku2) {
                if (sku1.getTotalPrice() > sku2.getTotalPrice()){
                    return -1;
                } else if (sku1.getTotalPrice() < sku2.getTotalPrice()){
                    return 1;
                }
                return 0;
            }
        });

        List<String> resultSkuNameList = new ArrayList<String>();
        Double money = 0.0;
        for (int i = 0; i < 2; i++) {
            money += notBooksSkuList.get(i).getTotalPrice();
            resultSkuNameList.add(notBooksSkuList.get(i).getSkuName());
        }

        log.info("top 2 : {}",  JSON.toJSONString(resultSkuNameList, true));
        log.info("总价: [{}]", money);
    }


    public void newCartHandle(List<Sku> cartSkuList){
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));
        List<String> resultSkuNameList = cartSkuList.stream()
                .peek(sku -> log.info("sku: {}", JSON.toJSONString(sku)))
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .limit(2)
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                .map(Sku::getSkuName).collect(Collectors.toList());
        log.info("top 2 : {}",  JSON.toJSONString(resultSkuNameList, true));
        log.info("总价: [{}]", money.get());
    }
}
