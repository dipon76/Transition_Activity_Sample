package com.example.dipon.activitytransitionsample.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

import com.example.dipon.activitytransitionsample.R;
import com.example.dipon.activitytransitionsample.data.SampleData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ClickCallback {
    private ArrayList<SampleData> sampleDataList;
    Button transitionBtn;
    RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }


    private void initialize() {
        sampleDataList = new ArrayList<>();
        sampleDataList = getData();

        setupToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        listAdapter = new ListAdapter(sampleDataList,this);
        listAdapter.setClickCallback(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(listAdapter);


        setUpAnimation ();

    }

    private ArrayList<SampleData> getData() {
        ArrayList<SampleData> tempData = new ArrayList<>();
        tempData.add(new SampleData(getColorList(R.color.sample_red),"Transition"));
        tempData.add(new SampleData(getColorList(R.color.sample_yellow),"Transition Programmatically"));
        return tempData;
    }

    public int getColorList(int id) {
        int res = ContextCompat.getColor(this,id);
        return res;
    }

    private void setUpAnimation() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Transition slide;
            slide =  TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
            Transition explode;
            explode = TransitionInflater.from(this).inflateTransition(R.transition.activity_explode);

            getWindow().setReenterTransition(slide);
            getWindow().setExitTransition(explode);
        }

    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onClick(View v) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClickCallback(View v, int p) {
        SampleData sampleData = sampleDataList.get(p);
        switch (p) {
            case 0:
                transitionToActivity(Main2Activity.class, sampleData);
                break;
//            case 1:
//                transitionToActivity(SharedElementActivity.class, viewHolder, sample);
//                break;
//            case 2:
//                transitionToActivity(AnimationsActivity1.class, sample);
//                break;
//            case 3:
//                transitionToActivity(RevealActivity.class, viewHolder, sample, R.string.transition_reveal1);
//                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void transitionToActivity(Class target, SampleData sampleData) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        startActivity(target, pairs, sampleData);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, SampleData sample) {
        Intent i = new Intent(this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        i.putExtra("sample", sample);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
