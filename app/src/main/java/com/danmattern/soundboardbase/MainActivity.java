package com.danmattern.soundboardbase;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String LIST_VIEW = "listView";

    private MediaPlayer mediaPlayer = null;
    private ListView listView = null;
    private ArrayList<ClipEntry> clips;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.clips = new ArrayList<>();
        ArrayList<String> files = getRawFileList();
        for (String file : files) {
            String display_name = "";
            try {
                display_name = getResources()
                        .getString(getResources()
                                .getIdentifier(file, "string", getPackageName()));
            } catch (RuntimeException e) {
                // Don't do anything
            }
            this.clips.add(new ClipEntry(file, display_name));
        }
        listView = (ListView) findViewById(R.id.listView);
        final ClipAdapter clipAdapter = new ClipAdapter(MainActivity.this, R.layout.list_record, this.clips);
        listView.setAdapter(clipAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSound(clipAdapter.getClipEntry(position).getTag());
            }
        });
    }

    private void playSound(String tag) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Resources res = this.getResources();
        int soundId = res.getIdentifier(tag, "raw", this.getPackageName());
        mediaPlayer = MediaPlayer.create(this, soundId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
        mediaPlayer.start();
    }

    private ArrayList<String> getRawFileList() {
        ArrayList<String> _return = new ArrayList<>();
        Field[] files = R.raw.class.getFields();
        for (Field file : files) {
            Log.d(TAG, "getRawFileList: filename: " + file.getName().toUpperCase());
            if (!(file.getName().charAt(0) == '$' || file.getName().toUpperCase().equalsIgnoreCase("SERIALVERSIONUID"))) {
                Log.d(TAG, "getRawFileList: Adding file" + file.getName().toUpperCase());
                _return.add(file.getName());
            }
        }
        return _return;
    }

}
