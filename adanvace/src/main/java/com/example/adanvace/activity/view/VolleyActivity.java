package com.example.adanvace.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adanvace.R;

import org.json.JSONObject;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 7/8/17
 * Input Parameter :
 */

public class VolleyActivity extends BaseActivity {

    private EditText et_string;
    private EditText et_json;
    private ImageView imageView;

    private RequestQueue quene;
    private StringRequest stringRequest;

    private JsonObjectRequest jsonObjectRequest;

    private String url_string = "http://www.baidu.com";

    private String url_weather = "http://m.weather.com.cn/data/101010100.html";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_volley;
    }

    @Override
    public void initView() {

        et_string = (EditText) findViewById(R.id.et_show_string);
        et_json = (EditText) findViewById(R.id.et_show_json);
        imageView = (ImageView) findViewById(R.id.iv_volley);
    }

    @Override
    public void initData() {
        quene = Volley.newRequestQueue(this);

    }

    @Override
    public void initEvent() {

        stringRequest = new StringRequest(url_string, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                et_string.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        jsonObjectRequest = new JsonObjectRequest(url_weather, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                et_json.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(VolleyActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void onVolleyClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_string:
                quene.add(stringRequest);
                break;
            case R.id.bt_get_json:
                quene.add(jsonObjectRequest);
                break;
            case R.id.bt_get_image:

                break;
        }
    }
}
