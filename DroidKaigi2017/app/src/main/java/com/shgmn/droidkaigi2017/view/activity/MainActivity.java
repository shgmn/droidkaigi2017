package com.shgmn.droidkaigi2017.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shgmn.droidkaigi2017.R;
import com.shgmn.droidkaigi2017.databinding.ActivityMainBinding;
import com.shgmn.droidkaigi2017.model.data.Repo;
import com.shgmn.droidkaigi2017.model.data.Sample;
import com.shgmn.droidkaigi2017.model.repository.GitHubClient;
import com.shgmn.droidkaigi2017.view.handler.MainActivityHandler;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityHandler {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Sample sample = new Sample("Hello DataBinding.", "Push");
        binding.setSample(sample);
        binding.setHandlers(this);
    }

    @Override
    public void onClickButton(View view) {
        GitHubClient gitHubClient = new GitHubClient();
        gitHubClient.fetchRepos("shgmn")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        if (repos == null) {
                            return;
                        }
                        for (Repo repo : repos) {
                            Toast.makeText(getApplicationContext(), repo.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
