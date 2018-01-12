package com.example.mybulter.presenter.impl;

import com.example.mybulter.model.impl.ProveModelImpl;
import com.example.mybulter.model.inter.ProveModel;
import com.example.mybulter.presenter.inter.OnPorveFinishedListener;
import com.example.mybulter.presenter.inter.ProvePresenter;
import com.example.mybulter.view.inter.ProveMessageView;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 25/7/17$
 * Input Parameter &
 */

public class ProvePresenterImpl implements ProvePresenter,OnPorveFinishedListener {

    private ProveMessageView messageView;
    private ProveModel proveModel;


    public ProvePresenterImpl(ProveMessageView messageView){
        this.messageView = messageView;
        proveModel = new ProveModelImpl();
    }


    @Override
    public void getProveFailed() {

    }

    @Override
    public void getProveSuccess() {

    }

    @Override
    public void prove(String phone, String new_password, String new_password_again, String identify_code) {
        if (phone.equals("")){
            messageView.phoneNumberNull();
            return;
        }

        if (phone.length()!=11){
            messageView.phoneNumberNotStandard();
            return;
        }

        if (new_password.equals("")||new_password_again.equals("")){
            messageView.passwordNull();
            return;
        }

        if (!new_password.equals(new_password_again)){
            messageView.passwordDifferent();
            return;
        }

        if (identify_code.equals("")){
            messageView.identifyCodeError();
            return;
        }

        proveModel.getProveResult(phone,new_password,identify_code,this);
    }
}
