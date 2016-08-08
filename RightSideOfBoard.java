import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;
import java.util.Arrays;
import java.util.ArrayList;

public class RightSideOfBoard{
    private VBox vb;
    private ArrayList<Shape> tetrominoBag;
    private Score score;
    private int intIndexForNextTetrimino;
    private int lvl;

    private Canvas scoreCanvas;
    private GraphicsContext scoreGc;

    private Canvas lvlCanvas;
    private GraphicsContext lvlGc;

    private Canvas linesCanvas;
    private GraphicsContext linesGc;


    RightSideOfBoard(VBox vb, ArrayList<Shape> tetrominoBag, Score score){
        this.vb = vb;
        this.tetrominoBag = tetrominoBag;
        this.score = score;

        //SCORE SETUP
        this.scoreCanvas = new Canvas(100,100);
        vb.getChildren().add(scoreCanvas);
        this.scoreGc = scoreCanvas.getGraphicsContext2D();
        drawScore();

        //LVL SETUP
        this.lvlCanvas = new Canvas(100,100);
        vb.getChildren().add(lvlCanvas);
        this.lvlGc = lvlCanvas.getGraphicsContext2D();
        drawScore();

        //LINES CLEARED
	this.linesCanvas = new Canvas(100,100);
	vb.getChildren().add(linesCanvas);
	this.linesGc = linesCanvas.getGraphicsContext2D();
	drawLines();
	

    }


    public void upDate(ArrayList<Shape> tetrominoBag, int indexForNextTetriMino){
        this.tetrominoBag = tetrominoBag;
        this.intIndexForNextTetrimino = indexForNextTetriMino; // + 1 ?

    }

    public void drawInfo(){

    }

    public void drawScore(){
        scoreGc.setFill(Color.WHITE);
        scoreGc.fillRect(0,0,100,100);

        scoreGc.setFill( Color.RED );
        scoreGc.setStroke( Color.BLACK );
        scoreGc.setLineWidth(1);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 16 );
        scoreGc.setFont( theFont );
        scoreGc.fillText( "SCORE:\n\t" + score.getScore(), 15, 40 );
        scoreGc.strokeText( "SCORE:\n\t" + score.getScore(), 15, 40 );
    }

    public void drawLvl(){
        lvlGc.setFill(Color.WHITE);
        lvlGc.fillRect(0,0,100,100);

        lvlGc.setFill( Color.RED );
        lvlGc.setStroke( Color.BLACK );
        lvlGc.setLineWidth(1);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 16 );
        lvlGc.setFont( theFont );
        lvlGc.fillText( "LVL:\n\t" + score.getLvl(), 15, 40 );
        lvlGc.strokeText( "LVL:\n\t" + score.getLvl(), 15, 40 );
    }

    public void drawLines(){
	linesGc.setFill(Color.WHITE);
        linesGc.fillRect(0,0,100,100);

        linesGc.setFill( Color.RED );
        linesGc.setStroke( Color.BLACK );
        linesGc.setLineWidth(1);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 16 );
        linesGc.setFont( theFont );
        linesGc.fillText( "LINES\n\t" + score.getLines(), 15, 40 );
        linesGc.strokeText( "LINES:\n\t" + score.getLines(), 15, 40 );
    }




}
