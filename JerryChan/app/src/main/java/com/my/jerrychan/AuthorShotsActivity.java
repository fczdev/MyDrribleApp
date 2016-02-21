package com.my.jerrychan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.my.jerrychan.HttpManager.UserApi;
import com.my.jerrychan.data.Author;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthorShotsActivity extends AppCompatActivity {
    private final static String TAG="AuthorShotsActivity";
    Toolbar toolbar;
    private long userId=0l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_shots);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       userId=getIntent().getLongExtra("shotsId",0l);
        Log.e("id",userId+"");

        UserApi.getAuthorInfo(userId+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Author>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage()!=null){
                            Log.e(TAG,"getdata wrong:"+e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Author author) {
                        Log.e(TAG,"author="+author.toString());
                    }
                });
    }

}
