package com.example.bms.myapplication_226;

import android.app.Activity;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MusicListAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Integer> musicList = new ArrayList<>();
    MediaPlayer mp;
    MediaMetadataRetriever mmr;

    public MusicListAdapter(Context ctx, ArrayList<Integer> musicList) {
        this.ctx = ctx;
        this.musicList = musicList;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView!=null){
            v = convertView;
        }else{
            v = LayoutInflater.from(ctx).inflate(R.layout.music_listitem,null);
        }

        Uri uri=Uri.parse("android.resource://"+ctx.getPackageName()+"/"+musicList.get(position));

        mmr = new MediaMetadataRetriever();
        mmr.setDataSource(ctx,uri);

        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if(title==null)
            title = musicList.get(position).toString();

        TextView tv = v.findViewById(R.id.tvMusicTitle);
        tv.setText(title);

        mp = new MediaPlayer();
        v.findViewById(R.id.ivPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.reset();
                mp = MediaPlayer.create(ctx,musicList.get(position));
                mp.start();
            }
        });
        v.findViewById(R.id.ivPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        return v;
    }
}
