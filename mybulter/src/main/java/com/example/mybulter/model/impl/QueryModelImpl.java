package com.example.mybulter.model.impl;

import com.example.mybulter.constant.Constant;
import com.example.mybulter.info.PhoneInfo;
import com.example.mybulter.model.inter.QueryModel;
import com.example.mybulter.presenter.inter.QueryResultPresenter;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public class QueryModelImpl implements QueryModel {

    /**
     * 请求示例：http://apis.juhe.cn/mobile/get?phone=13429667914&key=您申请的KEY
     *
     * @param phone
     * @param presenter {
     *                  "resultcode":"200",
     *                  "reason":"Return Successd!",
     *                  "result":{
     *                  "province":"浙江",
     *                  "city":"杭州",
     *                  "areacode":"0571",
     *                  "zip":"310000",
     *                  "company":"中国移动",
     *                  "card":"移动动感地带卡"
     *                  }
     *                  }
     */

    private String url;

    @Override
    public void queryPhone(String phone, final QueryResultPresenter presenter) {
        url = "http://apis.juhe.cn/mobile/get?phone="+phone+"&key="+ Constant.PHONE_KEY;

        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                try {
                    JSONObject jsonObject = new JSONObject(t);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    String company = jsonObject1.getString("company");
                    String province = jsonObject1.getString("province");
                    String city = jsonObject1.getString("city");

                    PhoneInfo info = new PhoneInfo(company,province,city);

                    presenter.success(info);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                presenter.fail();
            }
        });


    }
}
