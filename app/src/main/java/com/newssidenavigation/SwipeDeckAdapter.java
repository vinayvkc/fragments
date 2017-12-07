package com.newssidenavigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SwipeDeckAdapter extends BaseAdapter {

        private List<String> data,des,link;
        private Activity context;

        public SwipeDeckAdapter(List<String> data, ArrayList des,ArrayList link, Activity context) {
            this.data = data;
            this.context = context;
            this.des=des;
            this.link=link;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.main_page, parent, false);

            }
            ImageView imageView = (ImageView) v.findViewById(R.id.image1);
            Picasso.with(context).load(link.get(position).toString()).fit().centerCrop().into(imageView);
            TextView textView = (TextView) v.findViewById(R.id.text1);


            TextView  text2 = (TextView)v.findViewById(R.id.text2);
            text2.setText(des.get(position).toString());
            String item = (String)getItem(position);
            textView.setText(item);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                    Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));*/


                            Intent i = new Intent(context,ScrollingActivity.class);
                    Bundle b = new Bundle();
                    b.putString("title",data.get(position));
                    b.putString("des",des.get(position));
                    b.putString("img",link.get(position));
                    i.putExtras(b);
                            context.startActivity(i);






                }
            });
            return v;
        }
    }