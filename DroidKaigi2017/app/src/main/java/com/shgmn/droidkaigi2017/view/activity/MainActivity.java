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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                GitHubClient gitHubClient = new GitHubClient();
                Call<List<Repo>> repo = gitHubClient.fetchRepos("shgmn");
                repo.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        List<Repo> repos = response.body();
                        if (repos == null) {
                            return;
                        }
                        for (Repo repo : repos) {
                            Toast.makeText(getApplicationContext(), repo.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
