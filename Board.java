class Board{
    private int[][] gameBoard = new int[21][10];

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
        int j;
        for(int i = 0; i < positionXAndY.length / 2; i++){
            j = 4 + i;
            x = positionXAndY[i];
            y = positionXAndY[j];
            if(gameBoard[y][x] != 0){
                return false;
            }
        }
        return true;
    }

    public void drawFalling(int[] positionXAndY){
        int x;
        int y;
        int j;
        for(int i = 0; i < positionXAndY.length / 2; i++){
            j = 4 + i;
            x = positionXAndY[i];
            y = positionXAndY[j];
            gameBoard[y][x] = 100; // + colorIdNumber;
        }
    }

    public void drawStationary(int[] positionXAndY){
        int x;
        int y;
        int j;
        for(int i = 0; i < positionXAndY.length / 2; i++){
            j = 4 + i;
            x = positionXAndY[i];
            y = positionXAndY[j];
            gameBoard[y][x] = 1; // + colorIdNumber;
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
    /*
      000l000000
      000lll0000
      ----------
      0000000000
      0000000000
      0000000000
      0000000000
      .
      .
      .
      1110000000
      1101000000

    */

    public int[][] returnBoard(){
        return gameBoard;
    }

}
