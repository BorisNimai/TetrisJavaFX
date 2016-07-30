class Board{

    //Data****************************************************

    private int[][] gameBoard = new int[21][10];

    //Methods**************************************************

    Board(){
        for(int i = 0; i<10;i++){
            for(int j = 0; j < 21; j++){
                gameBoard[j][i] = 0;
            }
        }
    }

    public boolean isThereFreeSpaceForShape(int[] positionXAndY){
        int x;
        int y;
        for(int index = 0; index < 4; index++){
            x = positionXAndY[index];
            y = positionXAndY[index + 4];
            if(y > 20 || x > 9 || x < 0){
                return false;
            }
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 21 ; j++){
                    if((gameBoard[y][x] < 100 && gameBoard[y][x] > 0)){
                        return false;
                    }
                }
            }
        }
        return true;

    }

    public int[] findFalling(){
        int[] movingPositionXandY = new int[8];
        int index = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 21; j++){
                if(gameBoard[j][i] >= 100){
                    movingPositionXandY[index] = i;
                    movingPositionXandY[index + 4] = j;
                    index++;
                }
            }
        }
        return movingPositionXandY;
    }


    boolean ghostOverLapping(int[] positionXAndY){
        int[] movingPositionXandY = findFalling();
        for(int i = 0; i < 4; i++){
            if(movingPositionXandY[i] == positionXAndY[i] && movingPositionXandY[i + 4] == positionXAndY[i + 4]){
                return true;
            }
        }
        return false;
    }



    //Draw ghost %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public void drawGhost(int[] positionXAndY){
        deleteGhost();
        if(!ghostOverLapping(positionXAndY)){
            int x;
            int y;
            for(int i = 0; i < 4; i++){
                x = positionXAndY[i];
                y = positionXAndY[i + 4];
                if(gameBoard[y][x] < 100){
                    gameBoard[y][x] = -1;
                }
            }
        }
    }


    public void drawFalling(int[] positionXAndY){
        deleteFalling();
        deleteGhost();
        int x;
        int y;
        for(int i = 0; i < positionXAndY.length / 2; i++){
            x = positionXAndY[i];
            y = positionXAndY[i + 4];
            gameBoard[y][x] = 100; // + colorIdNumber;
        }
    }

    public void drawStationary(int[] positionXAndY){
        deleteFalling();
        deleteGhost();
        int x;
        int y;
        for(int i = 0; i < positionXAndY.length / 2; i++){
            x = positionXAndY[i];
            y = positionXAndY[i + 4];
            gameBoard[y][x] = 1; // + colorIdNumber;
        }
    }


    public void deleteGhost(){
        for(int i = 0; i < 21; i++){
            for(int j = 0; j <10; j++){
                if(gameBoard[i][j] == -1){
                    gameBoard[i][j] = 0;
                }
            }
        }
    }

    public void deleteFalling(){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j <gameBoard[0].length; j++){
                if(gameBoard[i][j] >= 100){
                    gameBoard[i][j] = 0;
                }
            }
        }
    }


//ReturnBoard

    public int[][] returnBoard(){
        return gameBoard;
    }

}
