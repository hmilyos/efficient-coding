package com.hmily.efficientcoding.lambda.cart;

import com.hmily.efficientcoding.EfficientCodingApplicationTests;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StreamVsOldServiceTest extends EfficientCodingApplicationTests {

    private List<Sku> cartSkuList = CartService.getCartSkuList();
    private StreamVsOldService streamVsOldService = new StreamVsOldService();

    @Test
    public void oldHandle() {
        streamVsOldService.oldHandle(cartSkuList);
    }

    @Test
    public void newCartHandle() {
        streamVsOldService.newCartHandle(cartSkuList);
    }
}