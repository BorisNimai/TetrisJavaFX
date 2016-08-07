class Score{
    // score
    // 1 Lines = 40
    // 2 Lines = 100
    // 3 Lines = 300
    // 4 Lines = 1200

    private int lvl = 0;
    private Board board;
    private int score = 0;
    private int[] linesClearScore = {40,100,300,1200};
    private int linesClear = 0;

    Score(int lvl, Board board){
	this.lvl = lvl;
	this.board = board;
    }

    //GET-------------------------------------------------

    //get score
    public int getScore(){
	return score;
    }

    //get lvl
    public int getLvl(){
	return lvl;
    }
    
    //SET--------------------------------------------------    

    //set lvl
    public void setLvl(){
	if(linesClear / (lvl+1) >= 10 ){
	    lvl++;
	}
    }

    public void setCustomLvL(int lvl){
	this.lvl = lvl;
    }

    //FUNCTION----------------------------------------------

    //ugly
    public void setSpeed(){
	int speed = (int)(60* Math.pow(0.75,lvl));
	Tetris.speed = speed;
	Tetris.ogSpeed = speed;
    }

    public void calculateScore(){
	int lines = board.clearLines();
	if(lines == 4) System.out.println(lines); // fdsfjdsklfjskldjfkl
	if(lines != 0){	    
	    score += linesClearScore[lines - 1] * (lvl + 1);
	    linesClear += lines;
	    setLvl();
	    setSpeed();
	    System.out.println(score);
	}
    }
    
          
    
    
}
