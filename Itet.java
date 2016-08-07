import java.util.Arrays;
import java.util.ArrayList;

class Itet extends Shape{



    // [ ][ ][ ][ ]   [ ][ ][1][ ]    [ ][ ][ ][ ]    [ ][1][ ][ ]
    // [1][1][1][1]   [ ][ ][1][ ]    [ ][ ][ ][ ]    [ ][1][ ][ ]
    // [ ][ ][ ][ ]   [ ][ ][1][ ]    [1][1][1][1]    [ ][1][ ][ ]
    // [ ][ ][ ][ ]   [ ][ ][1][ ]    [ ][ ][ ][ ]    [ ][1][ ][ ]

    Itet(Board board){
        this.board = board;
        this.tetrimino.addAll(Arrays.asList(0,0,0,0,   //[ ][ ][ ][ ]/
                                            1,1,1,1,   //[1][1][1][1]/ 1
                                            0,0,0,0,   //[ ][ ][ ][ ]/
                                            0,0,0,0,   //[ ][ ][ ][ ]/

                                            0,0,1,0,   //[ ][ ][1][ ]:
                                            0,0,1,0,   //[ ][ ][1][ ]: 2
                                            0,0,1,0,   //[ ][ ][1][ ]:
                                            0,0,1,0,   //[ ][ ][1][ ]/

                                            0,0,0,0,   //[ ][ ][ ][ ]/
                                            0,0,0,0,   //[ ][ ][ ][ ]/ 3
                                            1,1,1,1,   //[1][1][1][1]/
                                            0,0,0,0,   //[ ][ ][ ][ ]/

                                            0,1,0,0,   //[ ][1][ ][ ]:
                                            0,1,0,0,   //[ ][1][ ][ ]: 4
                                            0,1,0,0,   //[ ][1][ ][ ]:
                                            0,1,0,0)); //[ ][1][ ][ ]:
        colorID = 1;
    }

    protected int[] findElementPosition(int lstate,
                                        ArrayList<Integer> ltetrimino,
                                        int midPosX, int midPosY){
        int[] xAY = new int[8];
        int counter = 0;
        int startPositionX = midPosX - 1;
        int startPositionY = midPosY - 1;
        int yPositionHelper;

        for(int i  = lstate * 16; i < (lstate + 1) * 16; i++){
            if(ltetrimino.get(i) != 0){
                xAY[counter] = i % 4 + startPositionX; // x
                yPositionHelper = i % 16;
                xAY[counter + 4] = yPositionHelper / 4 + startPositionY;  //y
                counter++;
            }
        }
        return xAY;
    }
}
