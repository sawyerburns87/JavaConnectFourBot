package connectsrc;

public class Vals{
    public static final int numWidth = 7;
    public static final int numHeight = 6;
    public static final int sizeX = numWidth * 120;
    public static final int sizeY = numHeight * 120;
    public static final int diaCircle = (sizeY / numHeight);

    public static int[][] copyArr(int[][] src){
        int[][] newArr = new int[src.length][src[0].length];
        for(int i = 0; i < src.length; i++){
            for(int a = 0; a < src[i].length; a++){
                newArr[i][a] = src[i][a];
            }
        }
        return newArr;
    }

    public static int getScoreDir(int x, int y, int dir, int dirUp, int dirRight, int[][] board, int forPlayer, int againstPlayer){ // 0 = up, 1 = up-right 2 = right
        if(!canConnect(x, y, dir)){
            return 0;
        }
        int score = 0;
        for(int i = 0; i < 4 ; i++){
            if(board[x][y] == forPlayer){
                score += 1;
            }
            if(board[x][y] == againstPlayer){
                return 0;
            }
            x += dirRight;
            y += dirUp;
        }

        return score;
    }

    public static boolean canConnect(int x, int y, int dir){ // 0 = up, 1 = up-right 2 = right
        if(dir == 0){
            return y <= 2;
        }
        if(dir == 1){
            return (y <= 2 && x <= 3);
        }
        if(dir == 2){
            return x <= 3;
        }
        return false;
    }

    public static int calcHeight(int[][] board, int dropIndex){
        for(int i = Vals.numHeight-1; i >= 0; i--){
            if(board[dropIndex][i] != 0){
                return i + 1;
            }
        }
        return 0;
    }

    public static int calcScore(int[][] theBoard, int forPlayer, int againstPlayer){
        int newScore = 0;
        for(int x = 0; x < Vals.numWidth; x++){
            for(int y = 0; y < Vals.numHeight; y++){
                newScore += getScoreDir(x, y, 0, 1, 0, theBoard, forPlayer, againstPlayer);
                newScore += getScoreDir(x, y, 1, 1, 1, theBoard, forPlayer, againstPlayer);
                newScore += getScoreDir(x, y, 2, 0, 1, theBoard, forPlayer, againstPlayer);
            }
        }
        if(checkWin.check(theBoard, forPlayer)){
            newScore += 10000000;
        }
        return newScore;
    }

    public static void printAll(int[][] drops){
        for (int x = 0; x < drops.length; x++) {
            for (int y = 0; y < drops[x].length; y++) {
                System.out.print(drops[x][y]);
            }
            System.out.println("");
        }
        System.out.println("=========================================");
    }
    public static void printAll(int[] drops){
        for (int x = 0; x < drops.length; x++) {
            System.out.println(x+1 + ": " + drops[x]);
        }
        System.out.println("=========================================");
    }
}