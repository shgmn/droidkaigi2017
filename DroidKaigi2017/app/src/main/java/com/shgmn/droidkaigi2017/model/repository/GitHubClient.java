package com.shgmn.droidkaigi2017.model.repository;

import com.shgmn.droidkaigi2017.model.data.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shigemototakuya on 2017/03/13.
 */

public class GitHubClient {

    public GitHubClient() {
    }

    public Call<List<Repo>> fetchRepos(String user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        return service.listRepos(user);
    }
}
