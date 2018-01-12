package com.example.sportlogs.presenter.impl;

import android.content.Context;

import com.example.sportlogs.activity.inter.IViewJournalHistory;
import com.example.sportlogs.config.Config;
import com.example.sportlogs.info.JournalInfo;
import com.example.sportlogs.model.impl.JournalHistoryModelImpl;
import com.example.sportlogs.presenter.inter.JournalHistoryPresenterResult;
import com.example.sportlogs.presenter.inter.JournalHistoryPresenter;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalHistoryPresenterImpl implements JournalHistoryPresenter ,JournalHistoryPresenterResult {

    private IViewJournalHistory iViewJournalHistory;
    private JournalHistoryModelImpl journalHistoryModel;

    public JournalHistoryPresenterImpl(IViewJournalHistory iViewJournalHistory){
        this.iViewJournalHistory = iViewJournalHistory;
        journalHistoryModel = new JournalHistoryModelImpl();
    }


    @Override
    public void showHistoryJournal(Context context) {
        journalHistoryModel.getCurrentJournal(context,this, Config.STATE_SHOW_HISTORY_JOURNAL);

    }

    @Override
    public void updateHistoryJournal(Context context) {
        journalHistoryModel.getCurrentJournal(context,this,Config.STATE_UPDATE_JOURNAL);

    }

    @Override
    public void currentJournalResult(List<JournalInfo> journalInfoList) {
        iViewJournalHistory.showJournal(journalInfoList);
    }

    @Override
    public void updateJournalResult(List<JournalInfo> journalInfoList) {
        iViewJournalHistory.updateJournal(journalInfoList);
    }
}
