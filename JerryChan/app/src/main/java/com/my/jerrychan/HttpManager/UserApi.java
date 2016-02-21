package com.my.jerrychan.HttpManager;

import com.my.jerrychan.data.Shots;
import com.my.jerrychan.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by linchen on 16/2/21.
 * github:https://github.com/JerryChan123
 * e-mail:linchen0922@163.com
 */
public class UserApi extends BaseApi{
    private interface UserImpl {
        @Headers({
                "Content-Type:application/json;charset=utf-8" ,
                "Server:nginx",
                "Cache-Control:max-age=0,private,must-revalidate",
                "Authorization:Bearer 030410453e69f1981606ddfa1be4caeb892a1ddd35457639d51a5e2d26110968"
        })
        @GET("{user}")
        Observable<User> getUsers(@Path("user") String user );


    }

    private static com.my.jerrychan.HttpManager.UserApi.UserImpl userImpl=getRetrofit().create(UserImpl.class);

    public static Observable<User> getUser(String user){
        return userImpl.getUsers(user);
    }







    private interface ShotsImpl {
        @Headers({
                "Content-Type:application/json;charset=utf-8" ,
                "Server:nginx",
                "Cache-Control:max-age=0,private,must-revalidate",
                "Authorization:Bearer 030410453e69f1981606ddfa1be4caeb892a1ddd35457639d51a5e2d26110968"
        })
        @GET("shots")

        Observable<List<Shots>> getShots(@Query("timeframe") String timeframe);
    }

    private static ShotsImpl shots= getRetrofit().create(ShotsImpl.class);

    public static  Observable<List<Shots>> getShots(String timeframe){
        return shots.getShots(timeframe);
    }


}
