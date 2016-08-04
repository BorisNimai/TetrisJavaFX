import java.util.Arrays;
class Otet extends Shape{




    // [ ][1][1][ ]   [ ][1][1][ ]    [ ][1][1][ ]    [ ][1][1][ ]
    // [ ][1][1][ ]   [ ][1][1][ ]    [ ][1][1][ ]    [ ][1][1][ ]
    // [ ][ ][ ][ ]   [ ][ ][ ][ ]    [ ][ ][ ][ ]    [ ][ ][ ][ ]


    Otet(Board board){
        this.board = board;
        this.tetrimino.addAll(Arrays.asList(0,1,1,0,   //[ ][1][1][ ]/
                                            0,1,1,0,   //[ ][1][1][ ]/ 1
                                            0,0,0,0,   //[ ][ ][ ][ ]/


                                            0,1,1,0,   //[ ][1][1][ ]:
                                            0,1,1,0,   //[ ][1][1][ ]: 2
                                            0,0,0,0,   //[ ][ ][ ][ ]:


                                            0,1,1,0,   //[ ][1][1][ ]/
                                            0,1,1,0,   //[ ][1][1][ ]/ 3
                                            0,0,0,0,   //[ ][ ][ ][ ]/

                                            0,1,1,0,   //[ ][1][1][ ]:
                                            0,1,1,0,   //[ ][1][1][ ]: 4
                                            0,0,0,0)); //[ ][ ][ ][ ]:
        colorID = 4;
    }

    @Override
    private int[] findElementPosition(int lstate,
                                      ArrayList<Integer> ltetrimino,
                                      int midPosX, int midPosY){
        int[] xAY = new int[8];
        int counter = 0;
        int startPositionX = midPosX - 1;
        int startPositionY = midPosY - 1;
        int yPositionHelper;

        for(int i  = lstate * 12; i < (lstate + 1) * 12; i++){
            if(ltetrimino.get(i) != 0){
                xAY[counter] = i % 3 + startPositionX;
                yPositionHelper = i % 9;                                           // < ----Finish this method  Start Here 
                xAY[counter + 4] = yPositionHelper / 3 + startPositionY;
                counter++;
            }
        }
        return xAY;
    }



    //Spawn
    // spanw a new tetrimino on the board
    @Override
    public void spawnTetrimino() { // throw exception here later

        //if(falling = false){
        falling = true;
        this.x = 4;
        this.y = 1;///////
        state = 0;
        int[] xAndYPositions = new int[8];


        xAndYPositions = findElementPosition(state,tetrimino,x,y);


        if(board.isThereFreeSpaceForShape(xAndYPositions)){
            board.drawFalling(xAndYPositions,colorID);
            board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));

            //}
            for(int i = 0; i < xAndYPositions.length; i++){
                System.out.println(xAndYPositions[i]);
            }
        }
    }

    @Override
    

    
}
