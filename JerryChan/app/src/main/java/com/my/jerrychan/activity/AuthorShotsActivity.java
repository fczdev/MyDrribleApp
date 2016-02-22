package com.my.jerrychan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.my.jerrychan.HttpManager.UserApi;
import com.my.jerrychan.R;
import com.my.jerrychan.Utils.AuthorShotRecycleAdapter;
import com.my.jerrychan.data.Author;
import com.my.jerrychan.data.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthorShotsActivity extends BaseActivity implements View.OnClickListener{
    private final static String TAG="AuthorShotsActivity";
    private Toolbar toolbar;
    private long userId=0l;
    private String authorTitleStr="";
    private RecyclerView recyclerView;
    private List<Comment> list;
    private ImageView title_img;
    private AuthorShotRecycleAdapter adapter;


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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(this);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();



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
                        recyclerView.setAdapter(new AuthorShotRecycleAdapter(AuthorShotsActivity.this,list));
                        Picasso.with(AuthorShotsActivity.this)
                                .load(author.getImages().getNormal())
                                .into(title_img);
                        initRecycleHeader(author);
                        getComments(author.getId()+"");
                    }
                });
    }

    private void initRecycleHeader(Author author) {
        adapter=new AuthorShotRecycleAdapter(this,list);
        View view= LayoutInflater.from(this).inflate(R.layout.recycle_author_info_header,recyclerView,false);
        recyclerView.setAdapter(adapter);
        adapter.setHeaderView(view,author);

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
                        adapter.addDatas(comments);
                    }
                });

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.toolbar==id){
            AuthorShotsActivity.this.finish();
        }
    }
}
