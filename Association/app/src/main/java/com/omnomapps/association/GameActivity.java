package com.omnomapps.association;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eli on 7/5/2015.
 */
public class GameActivity extends Activity {

    private ArrayList<Button> left, right;
    private String selectedButton;
    private String[] puzzles;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layout = findViewById(R.id.layout);

        puzzles = readFile(1);

        selectedButton = "";

        left = new ArrayList<>();
        right = new ArrayList<>();

        populateButtons();

        setText(0);

        randomize();
    }

    private void populateButtons(){
        left.add((Button)findViewById(R.id.leftButton0));
        left.add((Button)findViewById(R.id.leftButton1));
        left.add((Button)findViewById(R.id.leftButton2));
        left.add((Button)findViewById(R.id.leftButton3));
        left.add((Button)findViewById(R.id.leftButton4));
        left.add((Button)findViewById(R.id.leftButton5));
        left.add((Button)findViewById(R.id.leftButton6));
        left.add((Button)findViewById(R.id.leftButton7));
        left.add((Button)findViewById(R.id.leftButton8));
        left.add((Button)findViewById(R.id.leftButton9));

        right.add((Button)findViewById(R.id.rightButton0));
        right.add((Button)findViewById(R.id.rightButton1));
        right.add((Button)findViewById(R.id.rightButton2));
        right.add((Button)findViewById(R.id.rightButton3));
        right.add((Button)findViewById(R.id.rightButton4));
        right.add((Button)findViewById(R.id.rightButton5));
        right.add((Button)findViewById(R.id.rightButton6));
        right.add((Button)findViewById(R.id.rightButton7));
        right.add((Button)findViewById(R.id.rightButton8));
        right.add((Button)findViewById(R.id.rightButton9));
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
        }
        else {
            String swap = "";

            if(selectedButton.startsWith("R")) {
                swap = left.get(Integer.parseInt(selectedButton.substring(1))).getText().toString();
                left.get(Integer.parseInt(selectedButton.substring(1))).setText(button.getText().toString());
                right.get(Integer.parseInt(selectedButton.substring(1))).clearFocus();
            }
            else if(selectedButton.startsWith("L")) {
                swap = right.get(Integer.parseInt(selectedButton.substring(1))).getText().toString();
                right.get(Integer.parseInt(selectedButton.substring(1))).setText(button.getText().toString());
                left.get(Integer.parseInt(selectedButton.substring(1))).clearFocus();
            }

            button.setText(swap);

            selectedButton = "";

            layout.requestFocus();
        }
    }

    private String[] readFile(int setNumber){
        InputStream inputStream = getResources().openRawResource(R.raw.set1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString().split("\\r?\\n");
    }

    private void setText(int puzzleNumber){
        String word = "";
        int wordCount = 0;

        for(int i = 0; i < puzzles[puzzleNumber].length(); i++){
            if(puzzles[puzzleNumber].charAt(i) == '|') {
                if (wordCount % 2 == 0)
                    left.get(wordCount / 2).setText(word);
                else
                    right.get(wordCount / 2).setText(word);
                word = "";
                wordCount++;
            }
            else
                word += puzzles[puzzleNumber].charAt(i);
        }

        right.get(wordCount/2).setText(word);
    }

    private void randomize(){
        Random random = new Random();
        int r;
        String swap;

        for(int i = 0; i < left.size(); i++){
            r = random.nextInt(left.size());
            swap = left.get(r).getText().toString();
            left.get(r).setText(left.get(i).getText().toString());
            left.get(i).setText(swap);
        }
    }

}
