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
                    if(gameBoard[y][x] < 100 &&  gameBoard[y][x] != 0){                        
                        return false;
                    }
                }
            }
        }
        return true;

    }


    public void drawFalling(int[] positionXAndY){
        deleteFalling();
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
        deleteFalling();
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

    public int[][] returnBoard(){
        return gameBoard;
    }

}
