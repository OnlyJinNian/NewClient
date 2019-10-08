package com.wh.jia.newsclient;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

/**
 * Created by JIA on 2019/9/17.
 */

public class NewsAdapter extends BaseAdapter {
    private List<NewsInfo> newsInfoList;
    private Context context;

    public NewsAdapter() {
    }

    public NewsAdapter(List<NewsInfo> newsInfoList, Context context) {
        this.newsInfoList = newsInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*View view = View.inflate(context,R.layout.news_item,null);
        SmartImageView item_icon = view.findViewById(R.id.item_icon);
        TextView item_title = view.findViewById(R.id.item_title);
        TextView item_description = view.findViewById(R.id.item_description);
        TextView item_type = view.findViewById(R.id.item_type);
        NewsInfo newsInfo = newsInfoList.get(position);
        item_icon.setImageUrl(newsInfo.getIcon(),R.drawable.ic_launcher,R.drawable.ic_launcher);
        item_title.setText(newsInfo.getTitle());
        item_description.setText(newsInfo.getContent());
        int type = newsInfo.getType();
        switch (type){
            case 1:
                item_type.setText("评论"+newsInfo.getComment());
                break;
            case 2:
                item_type.setTextColor(Color.RED);
                item_type.setText("专题");
                break;
            case 3:
                item_type.setTextColor(Color.BLUE);
                item_type.setText("LIVE");
                break;
        }
        return view;*/
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.item_icon = convertView.findViewById(R.id.item_icon);
            viewHolder.item_title = convertView.findViewById(R.id.item_title);
            viewHolder.item_description = convertView.findViewById(R.id.item_description);
            viewHolder.item_type = convertView.findViewById(R.id.item_type);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsInfo newsInfo = newsInfoList.get(position);
        viewHolder.item_icon.setImageUrl(newsInfo.getIcon(),R.drawable.ic_launcher,R.drawable.ic_launcher);
        viewHolder.item_title.setText(newsInfo.getTitle());
        viewHolder.item_description.setText(newsInfo.getContent());
        int type = newsInfo.getType();
        switch (type){
            case 1:
                viewHolder.item_type.setText("评论"+newsInfo.getComment());
                break;
            case 2:
                viewHolder.item_type.setTextColor(Color.RED);
                viewHolder.item_type.setText("专题");
                break;
            case 3:
                viewHolder.item_type.setTextColor(Color.BLUE);
                viewHolder.item_type.setText("LIVE");
                break;
        }
        return convertView;
    }
    class ViewHolder{
        SmartImageView item_icon;
        TextView item_title;
        TextView item_description;
        TextView item_type;
    }
}
