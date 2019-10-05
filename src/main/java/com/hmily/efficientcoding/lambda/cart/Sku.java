package com.hmily.efficientcoding.lambda.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Sku
 * @Description 下单商品信息对象
 * @Author Hmily
 * @Date 2019/10/5 15:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    // 编号
    private Integer skuId;
    // 商品名称
    private String skuName;
    // 单价
    private Double skuPrice;
    // 购买个数
    private Integer totalNum;
    // 总价
    private Double totalPrice;
    // 商品类型
    private SkuCategoryEnum skuCategory;

}
