package com.example.bms.myapplication_226;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewMusicActivity extends AppCompatActivity {

    ListView musicListView;
    ListAdapter musicAdapter;
    ArrayList<Integer> musicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_music);

        musicList.add(R.raw.lo_air_day_and_night);
        musicList.add(R.raw.mini_world);
        musicList.add(R.raw.pig_and_fox);
        musicList.add(R.raw.senden_baska);
        musicList.add(R.raw.this_town);

        musicListView = findViewById(R.id.MusicListView);
        musicAdapter = new MusicListAdapter(this,musicList);
        musicListView.setAdapter(musicAdapter);
    }
}
