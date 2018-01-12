package com.example.sportlogs.activity.inter;

import com.example.sportlogs.info.JournalInfo;

import java.util.List;

/**
 * Function :用于显示历史日志　
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public interface IViewJournalHistory {

    void updateJournal(List<JournalInfo> journalInfoList);
    void showJournal(List<JournalInfo> journalInfoList);
}
