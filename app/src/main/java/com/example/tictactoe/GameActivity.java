package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.tictactoe.databinding.ActivityGameBinding;

import java.util.Objects;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private final char playerOne = 'X';
    private final char playerTwo = '0';
    private String xo = "";
    private int xoColor = 0;
    private boolean checkNum;
    private int countOneWin = 0;
    private int countTwoWin = 0;
    private int row;
    private int column;
    private static boolean turn = true;

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.gameToolbar);
        getSupportActionBar().setTitle("TicTacToe");
        getSupportActionBar().setSubtitle("ver 1.0");

        Intent intent = getIntent();

        checkNum = Objects.requireNonNull(intent.getExtras()).getBoolean("checkPlayersNum");
        String name1st = intent.getExtras().getString("name1st");
        binding.playerOneNameTV.setText(name1st);

        if (checkNum) {
            String name2nd = intent.getExtras().getString("name2nd");
            binding.playerTwoNameTV.setText(name2nd);
        } else {
            binding.playerTwoNameTV.setText("Computer");
        }

        binding.gameStartBTN.setOnClickListener(v -> {
            GameBoard.fillBoard(GameBoard.gameBoard);
            boardEnable();
            showPlayerTurn();
            binding.gameStartBTN.setEnabled(false);
            binding.gameStartBTN.setAlpha(0.5f);
            binding.gameStartBTN.setTextColor(Color.GRAY);
            binding.resetStatsBTN.setEnabled(false);
            binding.resetStatsBTN.setAlpha(0.5f);
            binding.resetStatsBTN.setTextColor(Color.GRAY);
            if (!checkNum && !turn) {
                computerTurn();
            }
        });

        binding.resetStatsBTN.setOnClickListener(v -> {
            countOneWin = 0;
            countTwoWin = 0;
            binding.playerOneScoreTV.setText(String.valueOf(countOneWin));
            binding.playerTwoScoreTV.setText(String.valueOf(countTwoWin));
        });

        turnPlayers();
    }

    private void operation(int row, int column, char player, AppCompatButton button) {
        GameBoard.gameBoard[row][column] = player;
        getSymColor(player);
        button.setText(xo);
        button.setTextColor(xoColor);
        button.setEnabled(false);
    }

    private AppCompatButton compButton(int row, int column) {
        if (row == 0 && column == 0) return binding.zeroZero;
        if (row == 0 && column == 1) return binding.zeroOne;
        if (row == 0 && column == 2) return binding.zeroTwo;
        if (row == 1 && column == 0) return binding.oneZero;
        if (row == 1 && column == 1) return binding.oneOne;
        if (row == 1 && column == 2) return binding.oneTwo;
        if (row == 2 && column == 0) return binding.twoZero;
        if (row == 2 && column == 1) return binding.twoOne;
        if (row == 2 && column == 2) return binding.twoTwo;
        return null;
    }

    private void turnPlayers() {
        binding.zeroZero.setOnClickListener(v -> {
            turnOperations(0, 0, binding.zeroZero);
        });

        binding.zeroOne.setOnClickListener(v -> {
            turnOperations(0, 1, binding.zeroOne);
        });

        binding.zeroTwo.setOnClickListener(v -> {
            turnOperations(0, 2, binding.zeroTwo);
        });

        binding.oneZero.setOnClickListener(v -> {
            turnOperations(1, 0, binding.oneZero);
        });

        binding.oneOne.setOnClickListener(v -> {
            turnOperations(1, 1, binding.oneOne);
        });

        binding.oneTwo.setOnClickListener(v -> {
            turnOperations(1, 2, binding.oneTwo);
        });

        binding.twoZero.setOnClickListener(v -> {
            turnOperations(2, 0, binding.twoZero);
        });

        binding.twoOne.setOnClickListener(v -> {
            turnOperations(2, 1, binding.twoOne);
        });

        binding.twoTwo.setOnClickListener(v -> {
            turnOperations(2, 2, binding.twoTwo);
        });
    }

    private void turnOperations(int row, int column, AppCompatButton button) {
        if (checkNum) {
            if (turn) {
                turn = false;
                operation(row, column, playerOne, button);
                showPlayerTurn();
                checkWinner();
            } else {
                turn = true;
                operation(row, column, playerTwo, button);
                showPlayerTurn();
                checkWinner();
            }
        } else {
            if (turn) {
                turn = false;
                if (!isGameFinished()) {
                    operation(row, column, playerOne, button);
                    showPlayerTurn();
                    checkWinner();
                }
                if (!isGameFinished()) {
                    computerTurn();
                }
            }
        }
    }
    private void computerTurn() {
        if (GameBoard.checkEmpty()) {
            return;
        }
        do {
            row = new Random().nextInt(3);
            column = new Random().nextInt(3);
        } while (!GameBoard.checkEmptySlot(row, column));
        new Handler().postDelayed(() -> {
            operation(row, column, playerTwo, compButton(row, column));
            turn = true;
            showPlayerTurn();
            checkWinner();
        }, 500);
    }

    private void checkWinner() {
        if (GameBoard.checkWin(playerOne)) {
            binding.turnTV.setText(binding.playerOneNameTV.getText().toString() + " WIN");
            countOneWin++;
            binding.playerOneScoreTV.setText(String.valueOf(countOneWin));
            setEnabledButtons();
            boardErase();
            return;
        }
        if (GameBoard.checkWin(playerTwo)) {
            binding.turnTV.setText(binding.playerTwoNameTV.getText().toString() + " WIN");
            countTwoWin++;
            binding.playerTwoScoreTV.setText(String.valueOf(countTwoWin));
            setEnabledButtons();
            boardErase();
            return;
        }
        if (GameBoard.checkEmpty()) {
            binding.turnTV.setText("DRAW");
            setEnabledButtons();
            boardErase();
        }
    }

    private void setEnabledButtons() {
        binding.gameStartBTN.setEnabled(true);
        binding.gameStartBTN.setAlpha(1.0f);
        binding.gameStartBTN.setTextColor(Color.WHITE);
        binding.resetStatsBTN.setEnabled(true);
        binding.resetStatsBTN.setAlpha(1.0f);
        binding.resetStatsBTN.setTextColor(Color.WHITE);
    }

    private boolean isGameFinished() {
        return GameBoard.checkWin(playerOne) ||
                GameBoard.checkWin(playerTwo) ||
                GameBoard.checkEmpty();
    }
    @SuppressLint("SetTextI18n")
    private void showPlayerTurn() {
        if (turn) {
            binding.turnTV.setText(binding.playerOneNameTV.getText().toString() + " TURN");
        } else {
            binding.turnTV.setText(binding.playerTwoNameTV.getText().toString() + " TURN");
        }
    }

    private void getSymColor(char symbol) {
        if (symbol == playerOne) {
            xo = String.valueOf(playerOne);
            xoColor = ContextCompat.getColor(this, R.color.pink);
        } else {
            xo = String.valueOf(playerTwo);
            xoColor = ContextCompat.getColor(this, R.color.black);
        }
    }

    private void boardEnable() {
        binding.zeroZero.setEnabled(true);
        binding.zeroOne.setEnabled(true);
        binding.zeroTwo.setEnabled(true);
        binding.oneZero.setEnabled(true);
        binding.oneOne.setEnabled(true);
        binding.oneTwo.setEnabled(true);
        binding.twoZero.setEnabled(true);
        binding.twoOne.setEnabled(true);
        binding.twoTwo.setEnabled(true);
    }

    private void boardErase() {
        binding.zeroZero.setText("");
        binding.zeroOne.setText("");
        binding.zeroTwo.setText("");
        binding.oneZero.setText("");
        binding.oneOne.setText("");
        binding.oneTwo.setText("");
        binding.twoZero.setText("");
        binding.twoOne.setText("");
        binding.twoTwo.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}