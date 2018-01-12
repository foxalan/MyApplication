package com.example.sportlogs.model.target;

import android.content.Context;

import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.presenter.target.TargetResultPresenter;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public interface TargetModel {
    void addTargetModel(Context context,TargetInfo info, TargetResultPresenter presenter);
}
