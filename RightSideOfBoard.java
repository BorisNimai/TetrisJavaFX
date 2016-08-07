import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;

import java.util.ArrayList;

class RightSideOfBoard{
    private VBox vb;
    private ArrayList<Shape> tetrominoBag;
    private Score score;
    private int intIndexForNextTetrimino;
    private int lvl;

    private Canvas scoreCanvas;
    private GraphicsContext scoreGc;

    RightSideOfBoard(VBox vb, ArrayList<Shape> tetrominoBag, Score score){
        this.vb = vb;
        this.tetrominoBag = tetrominoBag;
        this.score = score;

        //SCORE SETUP
        this.scoreCanvas = new Canvas(100,50);
        vb.getChildren().add(scoreCanvas);
        this.scoreGc = scoreCanvas.getGraphicsContext2D();
        drawScore();

    }


    public void upDate(ArrayList<Shape> tetrominoBag, int indexForNextTetriMino){
        this.tetrominoBag = tetrominoBag;
        this.intIndexForNextTetrimino = indexForNextTetriMino;
        
    }

    public void drawInfo(){

    }

    public void drawScore(){
        scoreGc.setFill(Color.WHITE);
        scoreGc.fillRect(0,0,100,50);
	
        scoreGc.setFill( Color.RED );
        scoreGc.setStroke( Color.BLACK );
        scoreGc.setLineWidth(1);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 20 );
        scoreGc.setFont( theFont );
        scoreGc.fillText( "" + score.getScore(), 15, 40 );
        scoreGc.strokeText( "" + score.getScore(), 15, 40 );
    }



}
