package com.example.roman.animator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animator = (Animator) findViewById(R.id.animator);
        animator.setImages(Arrays.asList(arrows));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static String [] arrows = {
        "arrow-animation/arrow_00000.png",
        "arrow-animation/arrow_00001.png",
        "arrow-animation/arrow_00002.png",
        "arrow-animation/arrow_00003.png",
        "arrow-animation/arrow_00004.png",
        "arrow-animation/arrow_00005.png",
        "arrow-animation/arrow_00006.png",
        "arrow-animation/arrow_00007.png",
        "arrow-animation/arrow_00008.png",
        "arrow-animation/arrow_00009.png",
        "arrow-animation/arrow_00010.png",
        "arrow-animation/arrow_00011.png",
        "arrow-animation/arrow_00012.png",
        "arrow-animation/arrow_00013.png",
        "arrow-animation/arrow_00014.png",
        "arrow-animation/arrow_00015.png",
        "arrow-animation/arrow_00016.png",
        "arrow-animation/arrow_00017.png",
        "arrow-animation/arrow_00018.png",
        "arrow-animation/arrow_00019.png",
        "arrow-animation/arrow_00020.png",
        "arrow-animation/arrow_00021.png",
        "arrow-animation/arrow_00022.png",
        "arrow-animation/arrow_00023.png",
        "arrow-animation/arrow_00024.png",
        "arrow-animation/arrow_00025.png",
        "arrow-animation/arrow_00026.png",
        "arrow-animation/arrow_00027.png",
        "arrow-animation/arrow_00028.png",
        "arrow-animation/arrow_00029.png",
        "arrow-animation/arrow_00030.png",
        "arrow-animation/arrow_00031.png",
        "arrow-animation/arrow_00032.png",
        "arrow-animation/arrow_00033.png",
        "arrow-animation/arrow_00034.png",
        "arrow-animation/arrow_00035.png",
        "arrow-animation/arrow_00036.png",
        "arrow-animation/arrow_00037.png",
        "arrow-animation/arrow_00038.png",
        "arrow-animation/arrow_00039.png",
        "arrow-animation/arrow_00040.png",
        "arrow-animation/arrow_00041.png",
        "arrow-animation/arrow_00042.png",
        "arrow-animation/arrow_00043.png",
        "arrow-animation/arrow_00044.png",
        "arrow-animation/arrow_00045.png",
        "arrow-animation/arrow_00046.png",
        "arrow-animation/arrow_00047.png",
        "arrow-animation/arrow_00048.png",
        "arrow-animation/arrow_00049.png",
        "arrow-animation/arrow_00050.png",
        "arrow-animation/arrow_00051.png",
        "arrow-animation/arrow_00052.png",
        "arrow-animation/arrow_00053.png",
        "arrow-animation/arrow_00054.png",
        "arrow-animation/arrow_00055.png",
        "arrow-animation/arrow_00056.png",
        "arrow-animation/arrow_00057.png",
        "arrow-animation/arrow_00058.png",
        "arrow-animation/arrow_00059.png",
        "arrow-animation/arrow_00060.png"
    };
}
