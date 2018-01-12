package com.example.sportlogs.presenter.targethistory;

import android.content.Context;

import com.example.sportlogs.activity.inter.IViewTargetHistory;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.model.targethistory.TargetHistoryModelImpl;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetHistoryPresenterImpl implements TargetHistoryPresenter,TargetHistoryPresenterResult {

    private IViewTargetHistory iViewTargetHistory;
    private Context context;
    private TargetHistoryModelImpl targetHistoryModel;



    public TargetHistoryPresenterImpl(IViewTargetHistory iViewTargetHistory,Context context){
        this.iViewTargetHistory = iViewTargetHistory;
        this.context = context;
        targetHistoryModel = new TargetHistoryModelImpl();
    }

    @Override
    public void showHistoryTarget(Context context) {
        targetHistoryModel.showTargetHistory(context,this,0);
    }

    @Override
    public void updateHistoryTarget(Context context) {
        targetHistoryModel.showTargetHistory(context,this,1);
    }

    /**
     * 返回结果
     * @param targetInfoList
     */

    @Override
    public void currentTargetResult(List<TargetInfo> targetInfoList) {
        iViewTargetHistory.showTarget(targetInfoList);

    }

    @Override
    public void updateTargetResult(List<TargetInfo> targetInfoList) {
        iViewTargetHistory.showTarget(targetInfoList);

    }
}
