import java.util.ArrayList;

class Shape{

    //Data
    protected int x = -1;
    protected int y = -1;
    protected boolean falling = false;
    protected boolean done = false;

    protected int state = 0;
    protected ArrayList<Integer> tetrimino = new ArrayList<Integer>();
    protected Board board;
    protected int colorID;




    //--------------------Method's---------- 3x3 Overrided for other dimentions


    // findElementPosition takes in state, tetriminodata and position on the board for the tetrimio grid
    // and return array with position fot each element in the tetrimino
    private int[] findElementPosition(int lstate,
                                      ArrayList<Integer> ltetrimino,
                                      int midPosX, int midPosY){
        int[] xAY = new int[8];
        int counter = 0;
        int startPositionX = midPosX - 1;
        int startPositionY = midPosY - 1;
        int yPositionHelper;

        for(int i  = lstate * 9; i < (lstate + 1) * 9; i++){
            if(ltetrimino.get(i) != 0){
                xAY[counter] = i % 3 + startPositionX;
                yPositionHelper = i % 9;
                xAY[counter + 4] = yPositionHelper / 3 + startPositionY;
                counter++;
            }
        }
        return xAY;
    }

    public int[] findGhostPosition(int[] fallingPositionXandY){
        int[] testGhostFallingPosition = fallingPositionXandY;

        while(true){
            for(int i = 0; i < 4; i++){
                if(testGhostFallingPosition[i + 4] == 20){
                    return testGhostFallingPosition;
                }
            }

            if(!board.isThereFreeSpaceForShape(testGhostFallingPosition)){// ta hensy til at rotasjonen går opp og ned
                for(int j = 0; j < 4; j++){
                    testGhostFallingPosition[j + 4] -= 1;
                }
                return testGhostFallingPosition;
            }

            for(int i = 0; i < 4; i++){
                testGhostFallingPosition[i + 4] += 1;
            }

        }
    }



    //Spawn
    // spanw a new tetrimino on the board
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


    //timeStep
    public void timeStep(){
        if(!done){
            if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y+1))){
                y += 1;
                board.drawFalling(findElementPosition(state,tetrimino,x,y),colorID);
                board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));
                falling = true;
            }else if(falling == true){
                falling = false;
            }else if(falling == false){
                board.drawStationary(findElementPosition(state,tetrimino,x,y),colorID);
                done = true;
            }
        }

    }

    //moveLeft
    public void moveLeft(){
        if(!done){
            x--;
            if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y))){
                board.drawFalling(findElementPosition(state,tetrimino,x,y),colorID);
                board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));
            }else{
                x++;
            }
        }
    }

    //moveRight
    public void moveRight(){
        if(!done){
            x++;
            if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y))){
                board.drawFalling(findElementPosition(state,tetrimino,x,y),colorID);
                board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));
            }else{
                x--;
            }
        }
    }

    //rotateLeft
    public void rotateLeft(){
        if(!done){
            state--;
            if(state == -1) state = 3;
            if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y))){
                board.drawFalling(findElementPosition(state, tetrimino, x , y),colorID);
                board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));

            }else{
                state = (state == 3) ?  0 : state++;
            }
        }
    }

    //rotateRight
    public void rotateRight(){
        if(!done){
            state++;
            if(state == 4) state = 0;
            if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y))){
                board.drawFalling(findElementPosition(state, tetrimino, x , y),colorID);
                board.drawGhost(findGhostPosition(findElementPosition(state,tetrimino,x,y)));
            }else{
                state = (state == 0) ?  3 : state--;
            }
        }
    }


    //drop
    public void drop(){
        board.drawStationary(findGhostPosition(findElementPosition(state,tetrimino,x,y)),colorID);
        done = true;
    }
    

    //Status -- If there is a tetrimino falling or not
    public boolean status(){
        return done;
    }

    // shapes know their position, they take in (game board from this class)

}
