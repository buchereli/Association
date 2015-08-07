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
    private String selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        selectedButton = "";

        left = new ArrayList<>();
        right = new ArrayList<>();

        populateButtons();
    }

    private void populateButtons(){
        left.add((Button)findViewById(R.id.leftButton1));
        left.add((Button)findViewById(R.id.leftButton2));
        left.add((Button)findViewById(R.id.leftButton3));
        left.add((Button)findViewById(R.id.leftButton4));
        left.add((Button)findViewById(R.id.leftButton5));
        left.add((Button)findViewById(R.id.leftButton6));
        left.add((Button)findViewById(R.id.leftButton7));
        left.add((Button)findViewById(R.id.leftButton8));
        left.add((Button)findViewById(R.id.leftButton9));
        left.add((Button)findViewById(R.id.leftButton10));

        right.add((Button)findViewById(R.id.rightButton1));
        right.add((Button)findViewById(R.id.rightButton2));
        right.add((Button)findViewById(R.id.rightButton3));
        right.add((Button)findViewById(R.id.rightButton4));
        right.add((Button)findViewById(R.id.rightButton5));
        right.add((Button)findViewById(R.id.rightButton6));
        right.add((Button)findViewById(R.id.rightButton7));
        right.add((Button)findViewById(R.id.rightButton8));
        right.add((Button)findViewById(R.id.rightButton9));
        right.add((Button)findViewById(R.id.rightButton10));
    }

    public void clicked(View v){
        Button button = (Button) v;

        if(selectedButton.equals("")) {
            button.setFocusableInTouchMode(true);
            button.requestFocus();

            selectedButton = button.toString();
            while(!selectedButton.substring(0, 7).equals("app:id/"))
                selectedButton = selectedButton.substring(1);
            selectedButton = selectedButton.substring(7, selectedButton.length()-1);
            if(selectedButton.startsWith("left"))
                selectedButton = "L" + selectedButton.substring(10);

            System.out.println(selectedButton);
        }
        else {
            String swap = "";

            if(selectedButton.startsWith("R")) {
                swap = left.get(Integer.parseInt(selectedButton.substring(1))).getText().toString();
                left.get(Integer.parseInt(selectedButton.substring(1))).setText(button.getText().toString());
                left.get(Integer.parseInt(selectedButton.substring(1))).setFocusableInTouchMode(false);
            }
            else if(selectedButton.startsWith("L")) {
                swap = right.get(Integer.parseInt(selectedButton.substring(1))).getText().toString();
                right.get(Integer.parseInt(selectedButton.substring(1))).setText(button.getText().toString());
                right.get(Integer.parseInt(selectedButton.substring(1))).setFocusableInTouchMode(false);
            }

            button.setText(swap);
        }
    }
}
