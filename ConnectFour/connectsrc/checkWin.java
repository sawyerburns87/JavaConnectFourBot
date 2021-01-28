package connectsrc;

import connectsrc.Vals;

public class checkWin {
    public static boolean check(int[][] calcBoard, int checkPlayer){
        boolean win = false;
        for(int x = 0; x < calcBoard.length; x++){
            for(int y = 0; y < calcBoard[0].length; y++){
                if(getDir(x, y + 1, calcBoard, checkPlayer, 0, "up")){
                    return true;
                }
                if(getDir(x, y - 1, calcBoard, checkPlayer, 0, "down")){
                    return true;
                }
                if(getDir(x - 1, y, calcBoard, checkPlayer, 0, "left")){
                    return true;
                }
                if(getDir(x + 1, y, calcBoard, checkPlayer, 0, "right")){
                    return true;
                }
                if(getDir(x + 1, y + 1, calcBoard, checkPlayer, 0, "upright")){
                    return true;
                }
                if(getDir(x - 1, y + 1, calcBoard, checkPlayer, 0, "upleft")){
                    return true;
                }
                if(getDir(x - 1, y - 1, calcBoard, checkPlayer, 0, "downleft")){
                    return true;
                }
                if(getDir(x + 1, y - 1, calcBoard, checkPlayer, 0, "downright")){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean getDir(int x, int y, int[][] calcBoard, int player, int inRow, String dir){
        if(y == calcBoard[0].length || y < 0 || x < 0 || x == calcBoard.length){
            return false;
        }
        inRow++;
        if(calcBoard[x][y] == player){
            if(inRow == 4){
                return true;
            }
            else{
                if(dir.equals("up")){
                    y++;
                }
                if(dir.equals("down")){
                    y--;
                }
                if(dir.equals("left")){
                    x--;
                }
                if(dir.equals("right")){
                    x++;
                }
                if(dir.equals("upright")){
                    y++;
                    x++;
                }
                if(dir.equals("upleft")){
                    y++;
                    x--;
                }
                if(dir.equals("downright")){
                    y--;
                    x++;
                }
                if(dir.equals("downleft")){
                    y--;
                    x--;
                }
                return getDir(x, y, calcBoard, player, inRow, dir);
            }
        }
        return false;
    }

    public static int getLength(int x, int y, int[][] calcBoard, int player, int otherPlayer, int[] numConnect, String[][] dirMap){
        int len = 1;
        int possFour = 0;
        String dir = "";
        dir = getDir2(x, y, calcBoard, player);
        if(dirMap[x][y] == null){
            dirMap[x][y] = "";
        }
        if(dir == "" || (dirMap[x][y].contains(dir))){
            numConnect[len]++;
            return 0;
        }
        dirMap[x][y] += dir;

        int x2 = x;
        int y2 = y;
        String dir2 = getOppDir(dir);
        for(int i = len; i <= 4; i++){
            x2 = inc(dir2, x2, y2, true);
            y2 = inc(dir2, x2, y2, false);
            if(calcBoard[x][y] == otherPlayer){
                break;
            }
            if(i == 4){
                possFour++;
            }
        }

        while(checkDir(x, y, calcBoard, dir, player)){
            len++;
            x = inc(dir, x, y, true);
            y = inc(dir, x, y, false);
        }

        for(int i = len; i <= 4; i++){
            x = inc(dir, x, y, true);
            y = inc(dir, x, y, false);
            if(calcBoard[x][y] == otherPlayer){
                break;
            }
            if(i == 4){
                possFour++;
            }
        }

        numConnect[len]++;
        return possFour;
    }

    public static String getOppDir(String dir){
        if(dir == "up"){
            return "down";
        }
        if(dir == "down"){
            return "up";
        }
        if(dir == "left"){
            return "right";
        }
        if(dir == "right"){
            return "left";
        }
        if(dir == "upRight"){
            return "downLeft";
        }
        if(dir == "upLeft"){
            return "downRight";
        }
        if(dir == "downRight"){
            return "upLeft";
        }
        if(dir == "downLeft"){
            return "upRight";
        }
        return "";
    }

    public static int inc(String dir, int x, int y, boolean incX){
        if(y < Vals.numHeight - 1 && dir == "up"){
            if(incX){
                return x;
            }
            else{
                return y+1;
            }
        }
        if(y > 0 && dir == "down"){
            if(incX){
                return x;
            }
            else{
                return y-1;
            }
        }
        if(x > 0 && dir == "left"){
            if(incX){
                return x-1;
            }
            else{
                return y;
            }
        }
        if(x < Vals.numHeight - 1 && dir == "right"){
            if(incX){
                return x+1;
            }
            else{
                return y;
            }
        }
        if(y < Vals.numHeight - 1 && x < Vals.numHeight - 1 && dir == "upRight"){
            if(incX){
                return x+1;
            }
            else{
                return y+1;
            }
        }
        if(y < Vals.numHeight - 1 && x > 0 && dir == "upLeft"){
            if(incX){
                return x-1;
            }
            else{
                return y+1;
            }
        }
        if(y > 0 && x < Vals.numHeight - 1 && dir == "downRight"){
            if(incX){
                return x+1;
            }
            else{
                return y-1;
            }
        }
        if(y > 0 && x > 0 && dir == "downLeft"){
            if(incX){
                return x-1;
            }
            else{
                return y-1;
            }
        }
        if(incX){
            return x;
        }
        else{
            return y;
        }
    }

    public static String getDir2(int x, int y, int[][] calcBoard, int player){
        if(y < Vals.numHeight - 1 && calcBoard[x][y+1] == player){
            return "up";
        }
        if(y > 0 && calcBoard[x][y] == player){
            return "down";
        }
        if(x > 0 && calcBoard[x][y] == player){
            return "left";
        }
        if(x < Vals.numHeight - 1 && calcBoard[x][y] == player){
            return "right";
        }
        if(y < Vals.numHeight - 1 && x < Vals.numHeight - 1 && calcBoard[x][y] == player){
            return "upRight";
        }
        if(y < Vals.numHeight - 1 && x > 0 && calcBoard[x][y] == player){
            return "upLeft";
        }
        if(y > 0 && x < Vals.numHeight - 1 && calcBoard[x][y] == player){
            return "downRight";
        }
        if(y > 0 && x > 0 && calcBoard[x][y] == player){
            return "downLeft";
        }
        return "";
    }

    public static boolean checkDir(int x, int y, int[][] calcBoard, String dir, int player){
        if(dir == "up" && y < Vals.numHeight - 1){
            if(calcBoard[x][y+1] == player){
                return true;
            }
        }
        if(dir == "down" && y > 0){
            if(calcBoard[x][y-1] == player){
                return true;
            }
        }
        if(dir == "left" && x > 0){
            if(calcBoard[x-1][y] == player){
                return true;
            }
        }
        if(dir == "right" && x < Vals.numHeight - 1){
            if(calcBoard[x+1][y] == player){
                return true;
            }
        }
        if(dir == "upRight" && y < Vals.numHeight - 1 && x < Vals.numHeight - 1){
            if(calcBoard[x+1][y+1] == player){
                return true;
            }
        }
        if(dir == "upLeft" && y < Vals.numHeight - 1 && x > 0){
            if(calcBoard[x-1][y+1] == player){
                return true;
            }
        }
        if(dir == "downRight" && y > 0 && x < Vals.numHeight - 1){
            if(calcBoard[x+1][y-1] == player){
                return true;
            }
        }
        if(dir == "downLeft" && y > 0 && x > 0){
            if(calcBoard[x-1][y-1] == player){
                return true;
            }
        }
        return false;
    }
}
