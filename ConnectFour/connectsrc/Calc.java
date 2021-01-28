package connectsrc;
import java.util.ArrayList;

import connectsrc.Vals;
import connectsrc.boardEntry;
import connectsrc.checkWin;

public class Calc {
    boardEntry head;
    int panicInt = 0;

    public int newMove(int[][] drops, int playerJustMoved){
        head = new boardEntry(drops, playerJustMoved, 0);

        

        int move = findEndingMove(head, (playerJustMoved % 2) + 1, playerJustMoved);
        /*int rootingPlayer = (playerJustMoved % 2) + 1;

        
        for(int i = 0; i < Vals.numWidth; i++){
            int[][] nextDrop = Vals.copyArr(drops);
            int y = Vals.calcHeight(nextDrop, i);
            nextDrop[i][y] = rootingPlayer;
            System.out.println(Vals.calcScore(nextDrop, rootingPlayer, playerJustMoved));
        }*/

        return move;
    }

    public int findEndingMove(boardEntry curBoard, int rootingPlayer, int playerJustMoved){
        ArrayList<Integer> moves = new ArrayList<Integer>();
        ArrayList<Integer> endingScore = new ArrayList<Integer>();

        for(int i = 0; i < Vals.numWidth; i++){
            int[][] rawBoard = curBoard.getTheCopyBoard();
            if(Vals.calcHeight(rawBoard, i) < (Vals.numHeight)){ //The col is not full
                int yIndex = Vals.calcHeight(rawBoard, i);
                rawBoard[i][yIndex] = rootingPlayer;
                boardEntry nextBoard = new boardEntry(rawBoard, rootingPlayer, i);
                
                moves.add(i);
                int thisScore = findEndingScore(curBoard, rootingPlayer, playerJustMoved, nextBoard.getScore());
                endingScore.add(thisScore);
                System.out.println("Score at move " + i + " is " + endingScore.get(endingScore.size()-1));
                if(findPanic(curBoard.getTheCopyBoard(), playerJustMoved)){
                    return panicInt;
                }
            }
        }

        int bestIndex = moves.get(0);
        int best = endingScore.get(0);
        for (int i = 0; i < endingScore.size(); i++) {
            if(endingScore.get(i) > best){
                best = endingScore.get(i);
                bestIndex = moves.get(i);
            }
        }

        return bestIndex;
    }

    public int findEndingScore(boardEntry curBoard, int rootingPlayer, int playerToMove, int curScore){       
        if(rootingPlayer == playerToMove){
            curScore -= curBoard.getScore();
        }
        else{
            curScore += curBoard.getScore();
        }

        for(int i = 0; i < Vals.numWidth; i++){
            int[][] rawBoard = curBoard.getTheCopyBoard();

            if(Vals.calcHeight(rawBoard, i) < (Vals.numHeight)){ //The col is not full
                int yIndex = Vals.calcHeight(rawBoard, i);
                rawBoard[i][yIndex] = playerToMove;
                boardEntry nextBoard = new boardEntry(rawBoard, playerToMove, i);
                return findEndingScore(nextBoard, rootingPlayer, (playerToMove % 2) + 1, curScore);
            }
        }
        return curScore;
    }

    boolean findPanic(int[][] theBoard, int findPlayer){
        for(int i = 0; i < Vals.numWidth; i++){

            if(Vals.calcHeight(theBoard, i) < (Vals.numHeight)){ //The col is not full
                int yIndex = Vals.calcHeight(theBoard, i);
                theBoard[i][yIndex] = findPlayer;
                if(checkWin.check(theBoard, findPlayer)){
                    panicInt = i;
                    return true;
                }
            }
        }
        return false;
    }
}
