package com.huyunit.rxbus.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.huyunit.rxbus.RxBus;
import com.huyunit.rxbus.Subscribe;
import com.huyunit.rxbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxbusActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);

        ButterKnife.bind(this);
        mTextView = findViewById(R.id.text_view);

        RxBus.get().register(this);
    }

    @Subscribe
    public void onEvent(String s) {
        Log.e("RxBus", "------>" + s);
        mTextView.setText(s);
    }

    @Subscribe
    public void onEvent(EventA eventA) {
        Log.e("RxBus", "---onEvent EventA-->" + eventA.text);
        mTextView.setText(eventA.text);
    }

    @Subscribe(code = 102)
    public void onEventWithCode(EventA eventA) {
        Log.e("RxBus", "---onEvent EventA 102-->" + eventA.text);
        mTextView.setText(eventA.text);
    }

    @Subscribe(code = 103, threadMode = ThreadMode.MAIN)
    public void onEventWithCodeAndThreadMode(EventA eventA) {
        Log.e("RxBus", "---onEvent EventB 103--->" + eventA.text);
        mTextView.setText(eventA.text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                RxBus.get().post("single text 测试。");
                break;
            case R.id.btn2:
                RxBus.get().post(new EventA());
                break;
            case R.id.btn3:
                RxBus.get().post(102, new EventA("event code 102"));
                break;
            case R.id.btn4:
                RxBus.get().post(103, new EventA("event code 103"));
                break;
        }
    }


    class EventA {
        public String text = "EventA class";

        public EventA() {

        }

        public EventA(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "EventA{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

}
