package com.my.jerrychan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.my.jerrychan.HttpManager.BaseApi;
import com.my.jerrychan.constant.ApiConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linchen on 16/2/20.
 * github:https://github.com/JerryChan123
 * e-mail:linchen0922@163.com
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setApiConnection();
        onChildCreate(savedInstanceState);


    }
    protected  void onChildCreate(@Nullable Bundle savedInstanceState){

    }

//    protected void setApiConnection(){
//        retrofit= BaseApi.
//    }
}
