package com.shgmn.droidkaigi2017.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shgmn.droidkaigi2017.R;
import com.shgmn.droidkaigi2017.databinding.ActivityMainBinding;
import com.shgmn.droidkaigi2017.model.data.Sample;
import com.shgmn.droidkaigi2017.view.handler.MainActivityHandler;

public class MainActivity extends AppCompatActivity implements MainActivityHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Sample sample = new Sample("Hello DataBinding.", "Push");
        binding.setSample(sample);
        binding.setHandlers(this);
    }

    @Override
    public void onClickButton(View view) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }
}
