package com.example.sportlogs.activity.inter;

import com.example.sportlogs.info.JournalInfo;
import com.example.sportlogs.info.TargetInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public interface IViewTargetHistory {

    void updateTarget(List<TargetInfo> targetInfoList);

    void showTarget(List<TargetInfo> targetInfoList);
}
