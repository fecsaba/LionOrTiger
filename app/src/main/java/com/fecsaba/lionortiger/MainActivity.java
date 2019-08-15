package com.fecsaba.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, No
    }

    Player currentPlayer = Player.ONE;

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7},
            {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    Player[] playerChoices = new Player[9];

    private boolean gameOver = false;

    private Button btnReset;

    private GridLayout mGridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int index;
        /*playerChoices[0] = Player.No;
        playerChoices[1] = Player.No;
        playerChoices[2] = Player.No;
        playerChoices[3] = Player.No;
        playerChoices[4] = Player.No;
        playerChoices[5] = Player.No;
        playerChoices[6] = Player.No;
        playerChoices[7] = Player.No;
        playerChoices[8] = Player.No;*/

        for ( index = 0; index < playerChoices.length; index++) {
            playerChoices[index] = Player.No;
        }

        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTheGame();
            }
        });
        mGridLayout = findViewById(R.id.grid);

    }

    private void resetTheGame() {

        for (int i = 0; i < mGridLayout.getChildCount(); i++ ) {

            ImageView imageView = (ImageView) mGridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
            /*currentPlayer = Player.ONE;
            playerChoices[0] = Player.No;
            playerChoices[1] = Player.No;
            playerChoices[2] = Player.No;
            playerChoices[3] = Player.No;
            playerChoices[4] = Player.No;
            playerChoices[5] = Player.No;
            playerChoices[6] = Player.No;
            playerChoices[7] = Player.No;
            playerChoices[8] = Player.No;*/

            for (int ind = 0; ind < playerChoices.length; ind++) {
                playerChoices[ind] = Player.No;
            }
            gameOver=false;
            btnReset.setVisibility(View.INVISIBLE);
        }

    }


    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;

        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tiTag] == Player.No && !gameOver) {

            tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;


            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).
                    alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this, tappedImageView.getTag().toString(),
                    Toast.LENGTH_SHORT).show();
            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                        playerChoices[winnerColumns[0]] != Player.No) {
                    gameOver = true;
                    btnReset.setVisibility(View.VISIBLE);
                /*Toast.makeText(this,
                        "We have a winner",
                        Toast.LENGTH_LONG).show();*/
                    String winnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player two";
//                    Toast.makeText(this, "Player two is the winner",
//                    Toast.LENGTH_LONG).show();
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player one";
//                    Toast.makeText(this, "Player one is the winner",
//                            Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, winnerOfGame + " is the winner",
                            Toast.LENGTH_LONG).show();


                }

            }
        }
    }
}
