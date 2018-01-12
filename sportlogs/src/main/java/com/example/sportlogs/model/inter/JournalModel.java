package com.example.sportlogs.model.inter;

import android.content.Context;

import com.example.sportlogs.presenter.inter.JournalPresenterResult;

/**
 * Function :
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public interface JournalModel {

    void addInJournal(Context context, String journal_name, int journal_values, JournalPresenterResult journalPresenterResult);

}
