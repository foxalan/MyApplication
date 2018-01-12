package com.example.sportlogs.presenter.target;

import android.content.Context;

import com.example.sportlogs.activity.inter.IViewTarget;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.model.target.TargetModelImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetPresenterImpl implements TargetPresenter, TargetResultPresenter {

    private IViewTarget iViewTarget;
    private TargetModelImpl targetModel;
    private Context context;

    public TargetPresenterImpl(IViewTarget iViewTarget, Context context) {
        this.iViewTarget = iViewTarget;
        targetModel = new TargetModelImpl();
        this.context = context;
    }

    @Override
    public void addTarget(TargetInfo targetInfo) {
        if (targetInfo.getEndTime().equals("") || targetInfo.getStartTime().equals("") ||
                targetInfo.getTargetName().equals("") || targetInfo.getTargetValue() == 0) {
            iViewTarget.addTargetFail();
            return;
        }

        String startTime = targetInfo.getStartTime();
        String endTime = targetInfo.getEndTime();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sf.parse(startTime);
            d2 = sf.parse(endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (d1.after(d2)) {
            iViewTarget.addTargetTimeFail();
            return;
        }


        targetModel.addTargetModel(context, targetInfo, this);


    }

    @Override
    public void targetResultSuccess() {
        iViewTarget.addTargetSuccess();

    }
}
