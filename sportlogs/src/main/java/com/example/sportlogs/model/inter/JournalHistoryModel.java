package com.example.sportlogs.model.inter;

import android.content.Context;

import com.example.sportlogs.presenter.impl.JournalHistoryPresenterImpl;
import com.example.sportlogs.presenter.inter.JournalHistoryPresenterResult;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public interface JournalHistoryModel {

    void getCurrentJournal(Context context, JournalHistoryPresenterImpl result,int state);
}
