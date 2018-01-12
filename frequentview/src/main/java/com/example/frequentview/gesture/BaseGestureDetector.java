package com.example.frequentview.gesture;

import android.content.Context;
import android.view.MotionEvent;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 24/7/17$
 * Input Parameter &
 */

public abstract class BaseGestureDetector {


    protected boolean mGestureInProgress;

    protected MotionEvent mPreMotionEvent;
    protected MotionEvent mCurrentMotionEvent;

    protected Context mContext;

    public BaseGestureDetector(Context context)
    {
        mContext = context;
    }


    public boolean onToucEvent(MotionEvent event)
    {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                break;
        }

        if (!mGestureInProgress)
        {
            handleStartProgressEvent(event);
        } else
        {
            handleInProgressEvent(event);
        }

        return true;

    }

    protected abstract void handleInProgressEvent(MotionEvent event);

    protected abstract void handleStartProgressEvent(MotionEvent event);

    protected abstract void updateStateByEvent(MotionEvent event);

    protected void resetState()
    {
        if (mPreMotionEvent != null)
        {
            mPreMotionEvent.recycle();
            mPreMotionEvent = null;
        }
        if (mCurrentMotionEvent != null)
        {
            mCurrentMotionEvent.recycle();
            mCurrentMotionEvent = null;
        }
        mGestureInProgress = false;
    }
}
