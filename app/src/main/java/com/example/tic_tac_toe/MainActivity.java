package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 private  Button[][] buttons = new Button[3][3];
 private  boolean player1Turn = true;
 private int roundCount;
 private  int player1Points = 0;
 private  int player2Points =0;
 private TextView txvPlayer1;
 private  TextView txvPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvPlayer1 = (TextView) findViewById(R.id.text_view_p1);
        txvPlayer2 = (TextView) findViewById(R.id.text_view_p2);

        for(int i = 0 ; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String buttonId = "button_"+i+j ;
                int resID = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]  = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setBackgroundColor(Color.parseColor("#EDCF73"));
             //   buttons[i][j].setBackgroundResource(R.color.colorAccent);
            }
        }
        Button buttonReset = (Button) findViewById(R.id.btn_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         resetGame();
        }
        });
    }

    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals("") ) {
            return;
        }

        if(player1Turn) {
            ((Button) v).setText("X");
            Log.d("btn","X");
        }


        else {
            ((Button)v).setText("O");
            Log.d("btn","O");
        }

        roundCount++;
        if(checkForWin()) {
            if(player1Turn) {
                player1Wins();
            }
            else {
                player2Wins();
            }
        }
        else if (roundCount == 9) {
            draw();
        }
        else  {
            player1Turn = !player1Turn;
        }

    }
    private  boolean checkForWin() {
        String[][] field = new String[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j= 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString(); // dung field de tra ve chuoi cua btn
            }
        }
       //  hàng
        for(int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1])  && field[i][1].equals(field[i][2])  && !field[i][0].equals("") ) {
                return  true;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(field[0][i].equals(field[1][i])  && field[1][i].equals(field[2][i])  && !field[0][i].equals("") ) {
                return  true;
            }
        }
        if(field[0][0].equals(field[1][1])  && field[0][0].equals(field[2][2])  && !field[0][0].equals("") ) {
            return  true;
        }
        if(field[0][2].equals(field[1][1])  && field[0][2].equals(field[2][0])  && !field[0][2].equals("") ) {
            return  true;
        }
        return  false;
    }
    private void player1Wins() {
        player1Points++;
        Toast.makeText(this,"Player 1 win",Toast.LENGTH_SHORT).show();
        updatePointText();
        CountDownTimer countDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                resetBoard();
            }
        }.start();

    }
    private void player2Wins() {
        player2Points++;
        Toast.makeText(this,"Player 2 win",Toast.LENGTH_SHORT).show();
        updatePointText();
        CountDownTimer countDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                resetBoard();
            }
        }.start();

    }
    private void draw() {
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetBoard();

    }
    private  void updatePointText() {
        txvPlayer1.setText("player 1 :" +player1Points);
        txvPlayer2.setText("player 2 :" +player2Points);

    }
    private void resetBoard() {
        for(int i = 0 ;i < 3 ; i++) {
            for(int j=0 ; j<3;j++) {
                buttons[i][j].setText("");
            }
            roundCount =0;
            player1Turn = true;
        }
    }
    private  void resetGame() {
        player1Points=0;
        player2Points=0;
        updatePointText();
        resetBoard();
    }


}