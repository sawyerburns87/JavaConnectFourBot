package connectsrc;
import java.util.ArrayList;
import java.util.Arrays;

import connectsrc.Vals;
import connectsrc.checkWin;

public class boardEntry{
    int score = 0;
    int[][] theBoard;

    int playerToMove = 0;
    int lastPlayer = 0;
    int thisMove;

    boardEntry(int[][] newEntry, int newLastPlayer, int newMove){
        lastPlayer = newLastPlayer;
        playerToMove = (lastPlayer % 2) + 1;

        thisMove = newMove;
        theBoard = newEntry;
        score = Vals.calcScore(theBoard, lastPlayer, playerToMove);
    }

    public int getScore(){
        return score;
    }

    public int getMove(){
        return thisMove;
    }

    public int[][] getTheBoard(){
        return theBoard;
    }
    public int[][] getTheCopyBoard(){
        return Vals.copyArr(theBoard);
    }
}
