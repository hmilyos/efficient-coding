package com.hmily.efficientcoding.lambda.cart;

import com.alibaba.fastjson.JSON;
import com.hmily.efficientcoding.EfficientCodingApplicationTests;
import com.hmily.efficientcoding.lambda.cart.predicate.SkuPredicate;
import com.hmily.efficientcoding.lambda.cart.predicate.SkuTotalPricePredicate;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartServiceTest extends EfficientCodingApplicationTests {

    @Test
    public void filterSkus1() {

        List<Sku> cartSkuList = CartService.getCartSkuList();

        // 过滤商品总价大于2000的商品
        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuTotalPricePredicate());

        System.out.println(JSON.toJSONString(
                result, true));

    }

    @Test
    public void filterSkus2() {

        List<Sku> cartSkuList = CartService.getCartSkuList();

        // 过滤商品总价大于 1000 的商品
        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuPredicate() {
                    @Override
                    public boolean test(Sku sku) {
                        return sku.getTotalPrice() > 1000;
                    }
                });

        System.out.println(JSON.toJSONString(
                result, true));

    }

    @Test
    public void filterSkus3() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> skus = CartService.filterSkus(
                cartSkuList, sku -> sku.getTotalPrice() > 1000);
        System.out.println(JSON.toJSONString(skus, true));
    }
}