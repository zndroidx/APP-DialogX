package com.app.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zndroid.dialogx.BottomMenuDialog;
import com.zndroid.dialogx.core.DialogSettings;
import com.zndroid.dialogx.interfaces.OnMenuItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogSettings.init();
        DialogSettings.DEBUGMODE = true;
        DialogSettings.checkRenderscriptSupport(this);
        DialogSettings.isUseBlur = true;
        DialogSettings.autoShowInputKeyboard = true;
        //DialogSettings.backgroundColor = Color.BLUE;
        //DialogSettings.titleTextInfo = new TextInfo().setFontSize(50);
        //DialogSettings.buttonPositiveTextInfo = new TextInfo().setFontColor(Color.GREEN);
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;
    }

    public void test(View view) {
        showBottomMenuDialog();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showBottomMenuDialog(){
        BottomMenuDialog.show(this, "test", new String[]{"213", "345", "5675"}, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                toast(text);
            }
        });

    }
}
