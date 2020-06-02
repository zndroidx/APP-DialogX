package com.zndroid.dialogx.interfaces;

import com.zndroid.dialogx.core.BaseDialog;

public interface DialogLifeCycleListener {

    void onCreate(BaseDialog dialog);

    void onShow(BaseDialog dialog);

    void onDismiss(BaseDialog dialog);

}
