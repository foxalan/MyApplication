package com.example.sportlogs.presenter.targetmodify;

import android.content.Context;

/**
 * Function :
 * Author : Alan
 * Modify Date : 5/9/17
 * Issue : TODO
 * Whether solve :
 */

public interface TargetModifyPresenter {

    void modifyTarget(String new_name,String new_end_time,String new_value,String name,String end_time,String value);

    void deleteTarget(String name_item);
}
