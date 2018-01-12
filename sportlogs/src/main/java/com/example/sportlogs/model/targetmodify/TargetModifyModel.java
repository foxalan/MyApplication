package com.example.sportlogs.model.targetmodify;

import android.content.Context;

import com.example.sportlogs.presenter.targetmodify.TargetModifyResultPresenter;

/**
 * Function :
 * Author : Alan
 * Modify Date : 5/9/17
 * Issue : TODO
 * Whether solve :
 */

public interface TargetModifyModel {
    void modify(String new_name, String new_end_time, String new_value,String name,String end_time,String value, TargetModifyResultPresenter presenter);

    void delete(String name_item,TargetModifyResultPresenter presenter);
}
