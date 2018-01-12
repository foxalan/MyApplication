package com.example.sportlogs.presenter.inter;

import com.example.sportlogs.info.JournalInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public interface JournalHistoryPresenterResult {
    void currentJournalResult(List<JournalInfo> journalInfoList);
    void updateJournalResult(List<JournalInfo> journalInfoList);
}
