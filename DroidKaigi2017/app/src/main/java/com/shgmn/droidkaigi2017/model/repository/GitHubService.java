package com.shgmn.droidkaigi2017.model.repository;

import com.shgmn.droidkaigi2017.model.data.Repo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shigemototakuya on 2017/03/13.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
