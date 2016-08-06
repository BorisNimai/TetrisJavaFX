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

    protected boolean leftGard = false;
    protected boolean rightGard = false;





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
            // if line 20
            for(int i = 0; i < 4; i++){
                if(testGhostFallingPosition[i + 4] == 20 && board.isThereFreeSpaceForShape(testGhostFallingPosition)){
                    return testGhostFallingPosition;
                }
            }

            // if it detect that is not free space
            if(!board.isThereFreeSpaceForShape(testGhostFallingPosition)){
                for(int j = 0; j < 4; j++){
                    testGhostFallingPosition[j + 4] -= 1;
                }
                return testGhostFallingPosition;
            }

            // increment
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
//********************************************************************************
//********************************************************************************

    //rotateLeft //counter clockwise
    public void rotateLeft(){
        if(!done){
            int preState = state;

            state--;
            if(state == -1) state = 3;

            int[] xAY = findElementPosition(state,tetrimino,x,y);

            //IF
            if(board.isThereFreeSpaceForShape(xAY)){
                board.drawFalling(xAY,colorID);
                board.drawGhost(findGhostPosition(xAY));
            }

            //ELSE NO FREE SPACE FOR TETRIMINO TO ROTATE
            else{
                //PUll AWAY FROM WALL ?
                if(pullAwayFromWall(xAY)){
                    xAY = findElementPosition(state,tetrimino,x,y);
                    if(board.isThereFreeSpaceForShape(xAY)){
                        board.drawFalling(xAY,colorID);
                        board.drawGhost(findGhostPosition(xAY));
                    }
                    else{
                        state = preState;
                    }
                }
                // NO
                else{
                    System.out.println("Rotate Left is Not happning"); // tester for rotation on ground
                    if(rotateOnGround(xAY, preState)){
			System.out.println("ITs happning");
                        board.drawFalling(findElementPosition(state,tetrimino,x,y),colorID);
                    }else{
                        //DO NOTHING "RESET"
                        state = preState;
                    }
                }
            }
        }
    }


//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


    //rotateLeft //counter clockwise
    public void rotateRight(){
        if(!done){
            int preState = state;

            state++;
            if(state == 4) state = 0;

            int[] xAY = findElementPosition(state,tetrimino,x,y);

            //IF
            if(board.isThereFreeSpaceForShape(xAY)){
                board.drawFalling(xAY,colorID);
                board.drawGhost(findGhostPosition(xAY));
            }

            //ELSE NO FREE SPACE FOR TETRIMINO TO ROTATE
            else{
                //PUll AWAY FROM WALL ?
                if(pullAwayFromWall(xAY)){
                    xAY = findElementPosition(state,tetrimino,x,y);
                    if(board.isThereFreeSpaceForShape(xAY)){
                        board.drawFalling(xAY,colorID);
                        board.drawGhost(findGhostPosition(xAY));
                    }
                    else{
                        state = preState;
                    }
                }
                // NO
                else{
                    System.out.println("Rotate Right Not happning"); // tester for rotation on ground
                    if(rotateOnGround(xAY, preState)){
			System.out.println("IT's happning");
                        board.drawFalling(findElementPosition(state,tetrimino,x,y),colorID);
                    }else{
                        //DO NOTHING "RESET"
                        state = preState;
                    }
                }
            }
        }
    }

    //Helper Method  //finn den nederste "brikken før rotasjonen", finn ut om brukken  er under
    // etter rotasjonen om så bomp tetrimnoen oppover , må teste at det går ann å bompe oppover
    private boolean rotateOnGround(int[] xAY, int preState){
        int[] oldXAY = findElementPosition(preState,tetrimino,x,y);
        //IF ON GROUND OR ON TETRIMINO
        if(findGhostPosition(oldXAY) == oldXAY){
            for(int i = 0; i < 4; i++){
                if(xAY[i+4] > oldXAY[i+4]){
                    y--;
                    if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y))){
                        return true;
                    }else{
                        y++;
                    }
                }
            }
        }
        return false;
    }

    //HELPER METHOD
    private boolean pullAwayFromWall(int[] xAY){
        for(int i = 0; i < 4; i++){
            if(xAY[i] == -1){
                x++;
                return true;
            }
            if(xAY[i] == 10){
                x--;
                return true;
            }
        }
        return false;
    }

//********************************************************************************
//********************************************************************************


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
