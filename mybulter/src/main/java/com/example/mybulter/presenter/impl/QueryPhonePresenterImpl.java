package com.example.mybulter.presenter.impl;

import com.example.mybulter.info.PhoneInfo;
import com.example.mybulter.model.impl.QueryModelImpl;
import com.example.mybulter.model.inter.QueryModel;
import com.example.mybulter.presenter.inter.QueryPresenter;
import com.example.mybulter.presenter.inter.QueryResultPresenter;
import com.example.mybulter.view.inter.IQueryPhoneView;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public class QueryPhonePresenterImpl implements QueryPresenter ,QueryResultPresenter{

    private IQueryPhoneView queryPhoneView;
    private QueryModel queryModel;


    public QueryPhonePresenterImpl(IQueryPhoneView queryPhoneView){

        this.queryPhoneView = queryPhoneView;
        queryModel = new QueryModelImpl();

    }



    @Override
    public void query(String phoneNumber) {
        if (phoneNumber.equals("")){
            queryPhoneView.phoneNull();
            return;
        }

        if (phoneNumber.length() != 11){
            queryPhoneView.phoneUnillgel();
            return;
        }

        queryModel.queryPhone(phoneNumber,this);
    }

    @Override
    public void success(PhoneInfo info) {
        queryPhoneView.querySueecss(info);

    }

    @Override
    public void fail() {
        queryPhoneView.queryFail();
    }
}
