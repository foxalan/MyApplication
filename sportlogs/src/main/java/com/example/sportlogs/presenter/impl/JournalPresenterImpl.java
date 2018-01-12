package com.example.sportlogs.presenter.impl;

import android.content.Context;

import com.example.sportlogs.activity.inter.IViewJournal;
import com.example.sportlogs.model.impl.JournalModelImpl;
import com.example.sportlogs.presenter.inter.JournalPresenter;
import com.example.sportlogs.presenter.inter.JournalPresenterResult;

/**
 * Function :
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalPresenterImpl implements JournalPresenter,JournalPresenterResult {

    private JournalModelImpl journalModel;
    private IViewJournal iViewJournal;
    private Context context;

    public JournalPresenterImpl(IViewJournal iViewJournal,Context context){
        this.iViewJournal = iViewJournal;
        journalModel =  new JournalModelImpl();
        this.context =context;
    }

    @Override
    public void addJournalInDatabase(String journal_name, String journal_value) {
        if (journal_value.equals("")){
            iViewJournal.addJournalFail();
            return;
        }

        int value = Integer.valueOf(journal_value);
        journalModel.addInJournal(context,journal_name,value,this);
    }

    @Override
    public void success() {
        iViewJournal.addJournalSuccess();
    }
}
