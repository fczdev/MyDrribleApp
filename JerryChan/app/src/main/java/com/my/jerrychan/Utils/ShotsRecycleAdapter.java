package com.my.jerrychan.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.jerrychan.R;
import com.my.jerrychan.data.Shots;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linchen on 16/2/21.
 * github:https://github.com/JerryChan123
 * e-mail:linchen0922@163.com
 */
public class ShotsRecycleAdapter extends RecyclerView.Adapter<ShotsRecycleAdapter.ShotsHolder> {

    private List<Shots > list;
    private Context context;
    public ShotsRecycleAdapter(Context context,List<Shots> list){
        this.list=list;
        this.context=context;
    }

    @Override
    public ShotsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotsHolder(LayoutInflater.from(context).inflate(R.layout.list_item_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ShotsHolder holder, int position) {
        holder.tv_author.setText(list.get(position).getUser().getUsername());
        holder.tv_shotname.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getImages().getNormal()).into(holder.iv_img);
        Picasso.with(context).load(list.get(position).getUser().getAvatarUrl()).into(holder.iv_author_avatar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class ShotsHolder extends RecyclerView.ViewHolder{
        ImageView iv_img,iv_author_avatar;
        TextView tv_shotname,tv_author;

        public ShotsHolder(View itemView) {
            super(itemView);
            iv_img= (ImageView) itemView.findViewById(R.id.iv_img);
            iv_author_avatar= (ImageView) itemView.findViewById(R.id.img_author_avatar);
            tv_shotname= (TextView) itemView.findViewById(R.id.tv_imgname);
            tv_author= (TextView) itemView.findViewById(R.id.tv_author);
        }
    }
}
