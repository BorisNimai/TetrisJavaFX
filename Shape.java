import java.util.ArrayList;

abstract class Shape{

    //Data
    protected int x = -1;
    protected int y = -1;
    protected boolean falling = false;
    protected int state = 1;
    protected ArrayList<Integer> tetrimino = new ArrayList<Integer>();
    protected int dimentionX;
    protected int dimentionY;

    private int[] xAndYPositions = new int[8];



    //--------------------Method's---------- 3x3 Overrided for other dimentions

    private int[] finnElementPosition(int state, ArrayList<Integer> tetrimino){

    }

    private void setElementPosition(){
        int counter = 0;
        if(x != -1 && y != -1){

            for(int i  = state-1 * 9; i < state * 9; i++){
                if(tetrimino.get(i) != 0){
                    xAndYPositions[counter] = tetrimino.get(i) % 3;
                    xAndYPositions[counter + 4] = tetrimino.get(i) / 3;
                    counter++;
                }
            }


        }
    }

    //Spawn
    public void spawnTetrimino(){
        falling = true;
        this.x = 4;
        this.y = 1;





    }

    //timeStep
    public void timeStep(){

    }

    //moveLeft
    public void moveLeft(){

    }

    //moveRight
    public void moveRight(){

    }

    //rotateLeft
    public void rotateLeft(){

    }

    //rotateRight
    public void rotateRight(){

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
