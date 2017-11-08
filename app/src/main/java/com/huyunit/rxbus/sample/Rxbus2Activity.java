package com.huyunit.rxbus.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huyunit.rxbus2.RxBus;
import com.huyunit.rxbus2.annotation.Subscribe;
import com.huyunit.rxbus2.annotation.Tag;
import com.huyunit.rxbus2.thread.EventThread;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rxbus2Activity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus2);
        ButterKnife.bind(this);
        mTextView = findViewById(R.id.text_view);

        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Subscribe(thread = EventThread.IMMEDIATE, tags = {@Tag, @Tag(Constants.EventTags.TAG_APP_NAME)})
    public void result(String result){
        mTextView.setText(result);
    }

    public void onClick(View view){
        RxBus.get().post(Constants.EventTags.TAG_APP_NAME, "implements RxBus2 Remote call");
    }
}
