import java.util.ArrayList;

class Shape{

    //Data
    protected int x = -1;
    protected int y = -1;
    protected boolean falling = false;
    protected int state = 0;
    protected ArrayList<Integer> tetrimino = new ArrayList<Integer>();
    protected int dimentionX;
    protected int dimentionY;
    protected Board board;




    //--------------------Method's---------- 3x3 Overrided for other dimentions


    // finnElementPisition takes in state, tetriminodata and position on the board for the tetrimio grid
    // and return array with position fot each element in the tetrimino
    private int[] findElementPosition(int lstate,
                                      ArrayList<Integer> ltetrimino,
                                      int midPosX, int midPosY){
        int[] xAY = new int[8];
        int counter = 0;

        int startPositionX = midPosX - 1;
        int startPositionY = midPosY - 1;

        for(int i  = lstate * 9; i < lstate + 1 * 9; i++){
            if(ltetrimino.get(i) != 0){
                xAY[counter] = i % 3 + startPositionX;
                xAY[counter + 4] = i / 3 + startPositionY;
                counter++;
            }
        }
        return xAY;
    }



    //Spawn
    // spanw a new tetrimino on the board
    public void spawnTetrimino() { // throw exception here later
       
        //if(falling = false){
        falling = true;
        this.x = 4;
        this.y = 1;
        state = 0;
        int[] xAndYPositions = new int[8];


        xAndYPositions = findElementPosition(state,tetrimino,x,y);

       
        if(board.isThereFreeSpaceForShape(xAndYPositions)){
            board.drawFalling(xAndYPositions);
       
            //}
            for(int i = 0; i < xAndYPositions.length; i++){
                System.out.println(xAndYPositions[i]);
            }
        }
    }


    //timeStep
    public void timeStep(){
        // if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x,y+1))){
            board.deleteFalling();
            board.drawFalling(findElementPosition(state,tetrimino,x,y+1));
            y += 1;
            falling = true;
            /*}else if(falling == true){
            falling = false;
        }else if(falling == false){
            board.drawStationary(findElementPosition(state,tetrimino,x,y));
        }
            */
    }

    //moveLeft
    public void moveLeft(){
        if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x-1,y))){
            board.deleteFalling();
            board.drawFalling(findElementPosition(state,tetrimino,x-1,y));
        }
    }

    //moveRight
    public void moveRight(){
        if(board.isThereFreeSpaceForShape(findElementPosition(state,tetrimino,x+1,y))){
            board.deleteFalling();
            board.drawFalling(findElementPosition(state,tetrimino,x+1,y));
        }
    }

    //rotateLeft
    public void rotateLeft(){
        if(board.isThereFreeSpaceForShape(findElementPosition(state - 1 % 4, tetrimino, x , y))){
            board.deleteFalling();
            board.drawFalling(findElementPosition(state - 1 % 4, tetrimino, x , y));
        }
    }

    //rotateRight
    public void rotateRight(){
        if(board.isThereFreeSpaceForShape(findElementPosition(state + 1 % 4, tetrimino, x , y))){
            board.deleteFalling();
            board.drawFalling(findElementPosition(state + 1 % 4, tetrimino, x , y));
        }
    }

    //drop
    public void drop(){

    }

    //Status -- If there is a tetrimino falling or not
    public boolean status(){
        return true;
    }





    // shapes know their position, they take in (game board from this class)



}
