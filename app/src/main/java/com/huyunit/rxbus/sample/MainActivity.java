package com.huyunit.rxbus.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, RxbusActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, Rxbus2Activity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, RxbusktActivity.class));
                break;
        }
    }

}
