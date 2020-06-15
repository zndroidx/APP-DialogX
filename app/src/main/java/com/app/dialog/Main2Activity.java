package com.app.dialog;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl);
        View bottomSheet = coordinatorLayout.findViewById(R.id.bs);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

}
