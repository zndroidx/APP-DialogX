package com.zndroid.dialogx.interfaces;

import android.view.View;

import com.zndroid.dialogx.core.BaseDialog;

public interface OnInputDialogButtonClickListener {
    
    boolean onClick(BaseDialog baseDialog, View v, String inputStr);
}
