package com.example.sportlogs.presenter.targethistory;

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

public interface TargetHistoryPresenterResult {
    void currentTargetResult(List<TargetInfo> targetInfoList);
    void updateTargetResult(List<TargetInfo> targetInfoList);
}
