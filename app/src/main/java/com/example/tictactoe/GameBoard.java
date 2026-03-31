package com.example.tictactoe;

public class GameBoard {
    public static char[][] gameBoard = new char[3][3];


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

    static boolean checkWin(char whoWin) {
        if (gameBoard[0][0] == whoWin && gameBoard[0][1] == whoWin && gameBoard[0][2] == whoWin) return true;
        if (gameBoard[1][0] == whoWin && gameBoard[1][1] == whoWin && gameBoard[1][2] == whoWin) return true;
        if (gameBoard[2][0] == whoWin && gameBoard[2][1] == whoWin && gameBoard[2][2] == whoWin) return true;
        if (gameBoard[0][0] == whoWin && gameBoard[1][0] == whoWin && gameBoard[2][0] == whoWin) return true;
        if (gameBoard[0][1] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][1] == whoWin) return true;
        if (gameBoard[0][2] == whoWin && gameBoard[1][2] == whoWin && gameBoard[2][2] == whoWin) return true;
        if (gameBoard[0][0] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][2] == whoWin) return true;
        if (gameBoard[0][2] == whoWin && gameBoard[1][1] == whoWin && gameBoard[2][0] == whoWin) return true;
        return false;
    }

}
