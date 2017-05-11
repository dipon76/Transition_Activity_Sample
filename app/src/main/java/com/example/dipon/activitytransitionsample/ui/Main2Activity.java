package com.example.dipon.activitytransitionsample.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.TextView;

import com.example.dipon.activitytransitionsample.R;
import com.example.dipon.activitytransitionsample.data.SampleData;

public class Main2Activity extends AppCompatActivity {

    private TextView prompt;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initialize();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initialize() {
        prompt = (TextView) findViewById(R.id.txt_prompt);
        SampleData sampleData = (SampleData) getIntent().getExtras().getSerializable("sample");
        prompt.setText(sampleData.getItemName());
        setupToolbar();
        setUpTransition();
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpTransition() {
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);
    }
}
