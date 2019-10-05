package com.hmily.efficientcoding.lambda.cart.predicate;

import com.hmily.efficientcoding.lambda.cart.Sku;

/**
 * @ClassName SkuPredicate
 * @Description Sku选择谓词接口
 * @Author Hmily
 * @Date 2019/10/5 15:47
 **/

public interface SkuPredicate {

    /**
     * 选择判断标准
     * @param sku
     * @return
     */
    boolean test(Sku sku);

}
