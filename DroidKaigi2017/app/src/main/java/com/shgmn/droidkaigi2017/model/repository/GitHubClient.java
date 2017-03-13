package com.shgmn.droidkaigi2017.model.repository;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shgmn.droidkaigi2017.model.data.Repo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shigemototakuya on 2017/03/13.
 */

public class GitHubClient {

    public GitHubClient() {
    }

    public Observable<List<Repo>> fetchRepos(String user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        return service.listRepos(user);
    }
}
