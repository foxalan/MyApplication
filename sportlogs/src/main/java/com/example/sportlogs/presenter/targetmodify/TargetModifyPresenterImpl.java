package com.example.sportlogs.presenter.targetmodify;

import android.content.Context;

import com.example.sportlogs.activity.inter.IViewTarget;
import com.example.sportlogs.activity.inter.IViewTargetModify;
import com.example.sportlogs.model.target.TargetModelImpl;
import com.example.sportlogs.model.targetmodify.TargetModifyModeImpl;
import com.example.sportlogs.model.targetmodify.TargetModifyModel;

/**
 * Function :
 * Author : Alan
 * Modify Date : 5/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetModifyPresenterImpl implements TargetModifyPresenter,TargetModifyResultPresenter {

    private IViewTargetModify iViewTargetModify;
    private TargetModifyModel targetModel;


    public TargetModifyPresenterImpl(IViewTargetModify iViewTargetModify,Context context){
        this.iViewTargetModify = iViewTargetModify;
        targetModel = new TargetModifyModeImpl(context);
    }



    @Override
    public void modifySuccess() {
        iViewTargetModify.targetModifySuccess();

    }


    @Override
    public void modifyTarget(String new_name, String new_end_time, String new_value, String name, String end_time, String value) {
        if (new_name.equals("")||new_end_time.equals("")||new_value.equals("")){
            iViewTargetModify.targetModifyFail();
            return;
        }
        targetModel.modify(new_name,new_end_time,new_value,name,end_time,value,this);
    }

    @Override
    public void deleteTarget(String name_item) {
        targetModel.delete(name_item,this);

    }
}
