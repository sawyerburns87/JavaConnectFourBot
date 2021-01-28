package connectsrc;

import java.util.Arrays;
import java.util.ArrayList;

import connectsrc.Vals;
import connectsrc.checkWin;

public class Board {
    int curPlayer = 1;
    boolean usingBot = true;
    boolean winner = false;

    private int[][] drops = new int[Vals.numWidth][Vals.numHeight];
    ArrayList<Integer> moveList = new ArrayList<Integer>();
    
    static Drawing graphic;
    Calc cal = new Calc();

    public static void main(String[] args) {
        graphic = new Drawing();
    }

    public void attemptDrop(int x, int y){
        if((curPlayer == 2 && usingBot) || winner){
            System.out.println("Cant go sorry");
            return;
        }
        double tempDrop = (float)x / ((float)Vals.sizeX / (float)Vals.numWidth);
        int DropIndex = (int)Math.round(Math.floor(tempDrop));
        System.out.println("Attempting drop at index " + DropIndex);

        if(drops[DropIndex][Vals.numHeight - 1] != 0){ //The col is full
            System.out.println("Col " + DropIndex + 1 + " is full, g");
        }
        else{
            drop(DropIndex);
            if(checkWin.check(drops, 1)){
                winner = true;
                System.out.println("WINNER PLAYER 1");
            }
            else if(usingBot){
                drop(cal.newMove(Vals.copyArr(drops), 1)); //CALC the next move
                if(checkWin.check(drops, 2)){
                    winner = true;
                    System.out.println("WINNER PLAYER 2");
                }
            }
        }
    }

    public void drop(int DropIndex){
        int yIndex = Vals.calcHeight(drops, DropIndex);
        System.out.println("Dropped at " + DropIndex + ", " + yIndex);
        drops[DropIndex][yIndex] = curPlayer;
        
        moveList.add(DropIndex);
        graphic.addCircle(DropIndex, yIndex, curPlayer);
        
        if(curPlayer == 1){
            curPlayer = 2;
        }
        else{
            curPlayer = 1;
        }
    }

    
}