package com.example.modeldemo.model;

import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class StatusModel {
    /**
     * 售卖机的四个状态,无硬币时,投币时,出售,售完
     */
    private final static int STATUS_NO_MONEY = 1;
    private final static int STATUS_MONEY = 2;
    private final static int STATUS_SOLD = 3;
    private final static int STATUS_SOLD_OUT = 4;

    private int currentStatus = STATUS_NO_MONEY;

    private final static int ware_price = 10;

    private int count = 0;

    /**
     * 客户操作的三个方法  ,投币,买东西,退款
     */

    public StatusModel(int count){
        this.count = count;
        currentStatus = STATUS_NO_MONEY;
    }


    public void insertCoins() {
        switch (currentStatus) {
            case STATUS_NO_MONEY:
                L.d("投币成功");
                currentStatus = STATUS_MONEY;
                break;
            case STATUS_MONEY:
                L.d("已有货币,无须重复投币");
                break;
            case STATUS_SOLD:
                L.d("正在购中,请不要投币");
                break;
            case STATUS_SOLD_OUT:
                L.d("已售完,请不要投币");

        }
    }

    public void shopping() {
        switch (currentStatus){
            case STATUS_NO_MONEY:
                L.d("无货币,请先投币");
                break;
            case STATUS_MONEY:
                currentStatus = STATUS_SOLD;
                L.d("正在取货");
                getWare();
                break;
            case STATUS_SOLD:
                L.d("正在购买中,请不要重复点击");
                break;
            case STATUS_SOLD_OUT:
                L.d("商品已售完");
                break;
        }

    }

    private void getWare() {
        if (count <= 0 ){
            currentStatus = STATUS_SOLD_OUT;
            L.d("商品已售完");
        }else {
            count -- ;
            L.d("购买商品成功");
            currentStatus = STATUS_SOLD;
        }

    }

    public void refund() {
        switch (currentStatus){
            case STATUS_NO_MONEY:
                L.d("无款可退");
                break;
            case STATUS_MONEY:
                L.d("正在退款");
                currentStatus = STATUS_NO_MONEY;
                break;
            case STATUS_SOLD:
                L.d("商品购买的成功,请取剩余的零钱,欢迎下次再来");
                currentStatus = STATUS_NO_MONEY;
                break;
            case STATUS_SOLD_OUT:
                L.d("你没买任何商品,退款成功");
                currentStatus = STATUS_NO_MONEY;
                break;

        }

    }
}
