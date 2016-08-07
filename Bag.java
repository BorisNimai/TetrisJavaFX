import java.util.ArrayList;
import java.util.Random;

class Bag{


    private  ArrayList<Shape> tetriminoBag = new ArrayList<Shape>();
    private Board board;

    Bag(Board board){
        this.board = board;
    }


    public void generateBag(){
        Random rand = new Random();
        int m;
	tetriminoBag.removeAll(tetriminoBag);
	

        for(int i = 0; i < 14; i++){
            m = rand.nextInt(6);
            switch(m){
            case 0:
                tetriminoBag.add(new Itet(board));
                break;

            case 1:
                tetriminoBag.add(new Jtet(board));
                break;
            case 2:
                tetriminoBag.add(new Ltet(board));
                break;
            case 3:
                tetriminoBag.add(new Otet(board));
                break;
            case 4:
                tetriminoBag.add(new Stet(board));
                break;
            case 5:
                tetriminoBag.add(new Ttet(board));
                break;
            case 6:
                tetriminoBag.add(new Ztet(board));
                break;
            }
        }
    }


    public ArrayList<Shape> getBag(){
        return tetriminoBag;
    }


}
