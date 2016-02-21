package com.my.jerrychan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.my.jerrychan.HttpManager.UserApi;
import com.my.jerrychan.R;
import com.my.jerrychan.Utils.AuthorShotRecycleAdapter;
import com.my.jerrychan.activity.BaseActivity;
import com.my.jerrychan.data.Author;
import com.my.jerrychan.data.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthorShotsActivity extends BaseActivity {
    private final static String TAG="AuthorShotsActivity";
    private Toolbar toolbar;
    private long userId=0l;
    private String authorTitleStr="";
    private RecyclerView recyclerView;
    private List<String> list;
    private ImageView title_img;


    @Override
    protected void onChildCreate(@Nullable Bundle savedInstanceState) {
        super.onChildCreate(savedInstanceState);
        setContentView(R.layout.activity_author_shots);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        userId=getIntent().getLongExtra("shotsId",0l);
        authorTitleStr=getIntent().getStringExtra("authorTitle");
        if (!authorTitleStr.equals(""))
        {
            toolbar.setTitle(authorTitleStr);
        }
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            list.add("" + (char) i);
        }
        recyclerView.setAdapter(new AuthorShotRecycleAdapter(this,list));

        title_img= (ImageView) findViewById(R.id.toolbar_image);
        getData();

    }

    private void getData() {
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
                        Picasso.with(AuthorShotsActivity.this)
                                .load(author.getImages().getNormal())
                                .into(title_img);
                        getComments(author.getId()+"");
                    }
                });
    }

    private void getComments(String id){
        UserApi.getAuthorComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage()!=null){
                            Log.e(TAG,e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        Log.e(TAG,comments.toString());
                    }
                });

    }



}
