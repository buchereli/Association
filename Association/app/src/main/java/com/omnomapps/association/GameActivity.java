package com.omnomapps.association;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Eli on 7/5/2015.
 */
public class GameActivity extends Activity {

    private ArrayList<Button> left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left = new ArrayList<>();
        right = new ArrayList<>();

        populate();
    }

    private void populate(){
        left.add((Button)findViewById(R.id.leftButton1));
        left.add((Button)findViewById(R.id.leftButton2));
        left.add((Button)findViewById(R.id.leftButton3));
        left.add((Button)findViewById(R.id.leftButton4));
        left.add((Button)findViewById(R.id.leftButton5));

        right.add((Button) findViewById(R.id.rightButton1));
        right.add((Button) findViewById(R.id.rightButton2));
        right.add((Button)findViewById(R.id.rightButton3));
        right.add((Button)findViewById(R.id.rightButton4));
        right.add((Button) findViewById(R.id.rightButton5));
    }

    public void clicked(View v){
        Button button = (Button) v;
        button.setText("Clicked");
    }
}
