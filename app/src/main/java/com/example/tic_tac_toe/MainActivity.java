package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:O and 1:X

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //2:empty
    int[][] positionToWin ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOver = true;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int tappedCounter =Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && gameOver) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] positionToWin : positionToWin) {


                if (gameState[positionToWin[0]] == gameState[positionToWin[1]] && gameState[positionToWin[1]] == gameState[positionToWin[2]] && gameState[positionToWin[0]] != 2) {
                    //one of the two player has won
                    String winner = " ";
                        gameOver = false;
                    if (activePlayer == 0) {
                        winner = "O";

                    } else {
                        winner = "X";

                    }
//                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView =(TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won!");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }
        }
    }
    public void playAgain(View view) {
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
         //2:empty
        }
        activePlayer = 0;

        gameOver = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}