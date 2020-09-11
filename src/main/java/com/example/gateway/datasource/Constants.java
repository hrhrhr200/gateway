package com.example.gateway.datasource;

import com.baitao.common.enums.FormHeadSubTypeEnum;

/**
 * @author LiuJiaPeng
 * @version 1.0
 * @description TODO
 * @date 2019-09-02 23:42
 */
public interface Constants {


    /**
     * 数据源
     */
    String DATASOURCE_USER = "User";
    String DATASOURCE_ORDER = "order";
    String DATASOURCE_GOODS = "goods";
    String DATASOURCE_BASICS = "basics";

    /**
     * 默认数据源
     */
    String DEFAULT_DATASOURCE = DATASOURCE_ORDER;

    /**
     * 有赞消息类型
     */
    String TRADE_TRADEBUYERPAY = "trade_TradeBuyerPay"; // 买家付款(即商家待发货)
    String TRADE_TRADESUCCESS = "trade_TradeSuccess"; //交易成功
    String TRADE_TRADECLOSE = "trade_TradeClose"; // 交易关闭


    String CRAD_ALIAS = "Y35z9707dracn4";

    String YOUZAN_ACTIVITY_NAME = "有赞活动";

    String YOUZAN_SHOPNO = "BTS1001";

    String PUECHASER = "purchase";

    //有赞零售渠道
    String OFFLINE = "OFFLINE";

    String ONLINE = "ONLINE";

    //其他出入库单待审核状态
    String WAIT_AUDIT = "waitaudit";

    //需要操作的单据类型
    String[] NEED_HANDLE_FORM_TYPE = {FormHeadSubTypeEnum.ENTRY_OTHER_GIFTS.getName(),
            FormHeadSubTypeEnum.ENTRY_OTHER_SAMPLES.getName(),
            FormHeadSubTypeEnum.ENTRY_OTHER_SHOPPINGBAGS.getName(),
            FormHeadSubTypeEnum.ENTRY_PURCHASE.getName(),
            FormHeadSubTypeEnum.ENTRY_TRANS.getName(),
            FormHeadSubTypeEnum.ENTRY_INVENTORY_ADJUSTMENT.getName(),
            FormHeadSubTypeEnum.DELIVERY_INVENTORY_ADJUSTMENT.getName(),
            FormHeadSubTypeEnum.DELIVERY_TRANS.getName(),
            FormHeadSubTypeEnum.DELIVERY_PURCHASE.getName(),
            FormHeadSubTypeEnum.ENTRY_SALE_RETURN_LOGISTICS.getName()
    };

    //基础方法名称
    String MERCHID = "getMerchId";
    String YZTYPE = "getYzType";

    //平台类型
    String MINI = "mini";

    String RETAIL = "retail";

    String PROXY_NAME = "【品牌直供】";

    String ALLOT_CONFIG_CODE = "Need_Handle_YzAllotMsg";

    String DELIVERY = "delivery";

    String BUILDDELIVERY = "builddelivery";

    String FINISH = "finish";

    String REFUND = "refund";

    Integer expressType_package = 0;
}
