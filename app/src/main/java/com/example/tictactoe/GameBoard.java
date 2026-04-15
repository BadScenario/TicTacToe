package com.example.tictactoe;

import java.util.Random;

public class GameBoard {
    public static char[][] gameBoard = new char[3][3];
    public static char[][] winBoard = new char[3][3];

    public static void fillWinBoard() {
        for (int i = 0; i < winBoard.length; i++) {
            for (int j = 0; j < winBoard.length; j++) {
                winBoard[i][j] = '-';
            }
        }
    }


    public static char[][] fillBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
        return gameBoard;
    }

    public static boolean checkEmpty() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkEmptySlot(int row, int column) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[row][column] != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkCenter(char playerOne, char computer) {
        if (gameBoard[1][1] != playerOne && gameBoard[1][1] != computer) return true;
        return false;
    }

    static boolean checkWin(char whoWin) {
        fillWinBoard();
        if (gameBoard[0][0] == whoWin && gameBoard[0][1] == whoWin && gameBoard[0][2] == whoWin) {
            winBoard[0][0] = whoWin;
            winBoard[0][1] = whoWin;
            winBoard[0][2] = whoWin;
            return true;
        }

        if (gameBoard[1][0] == whoWin && gameBoard[1][1] == whoWin && gameBoard[1][2] == whoWin) {
            winBoard[1][0] = whoWin;
            winBoard[1][1] = whoWin;
            winBoard[1][2] = whoWin;
            return true;
        }

        if (gameBoard[2][0] == whoWin && gameBoard[2][1] == whoWin && gameBoard[2][2] == whoWin) {
            winBoard[2][0] = whoWin;
            winBoard[2][1] = whoWin;
            winBoard[2][2] = whoWin;
            return true;
        }
        if (gameBoard[0][0] == whoWin && gameBoard[1][0] == whoWin && gameBoard[2][0] == whoWin) {
            winBoard[0][0] = whoWin;
            winBoard[1][0] = whoWin;
            winBoard[2][0] = whoWin;
            return true;
        }

        if (gameBoard[0][1] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][1] == whoWin) {
            winBoard[0][1] = whoWin;
            winBoard[1][1] = whoWin;
            winBoard[2][1] = whoWin;
            return true;
        }

        if (gameBoard[0][2] == whoWin && gameBoard[1][2] == whoWin && gameBoard[2][2] == whoWin) {
            winBoard[0][2] = whoWin;
            winBoard[1][2] = whoWin;
            winBoard[2][2] = whoWin;
            return true;
        }
        if (gameBoard[0][0] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][2] == whoWin) {
            winBoard[0][0] = whoWin;
            winBoard[1][1] = whoWin;
            winBoard[2][2] = whoWin;
            return true;
        }
        if (gameBoard[0][2] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][0] == whoWin) {
            winBoard[0][2] = whoWin;
            winBoard[1][1] = whoWin;
            winBoard[2][0] = whoWin;
            return true;
        }
        return false;
    }
}
