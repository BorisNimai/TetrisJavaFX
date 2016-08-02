import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

class DrawBoard{
    private Board board;
    private GridPane tetrisGrid;

    private Canvas[][] tetrisTiles;
    private GraphicsContext[][] arrayGc;
    private int[][] tegnBrett;

    //Constructor
    DrawBoard(GridPane tetrisGrid, Board board){
        this.board = board;
        this.tetrisGrid = tetrisGrid;
        setUp();
        paintBoard();
    }


    private void setUp(){
        Canvas tempCanvas;
        GraphicsContext tempGc;

        tetrisTiles = new Canvas[10][20];
        arrayGc = new GraphicsContext[10][20];
        tegnBrett = board.returnBoard();


        // canvas firkant blir laget
        // adder canvas i griden
        // array for canvas
        // get Graphic contect
        // array of graphic contect

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                tempCanvas = new Canvas(20,20);
                tetrisGrid.add(tempCanvas ,i ,j);
                tetrisTiles[i][j] = tempCanvas;
                tempGc = tempCanvas.getGraphicsContext2D();
                arrayGc[i][j] = tempGc;
            }
        }
    }





    public void paintBoard(){
        int[][] tegnBrett = board.returnBoard();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){

                // if empty space
                if(tegnBrett[j+1][i] == 0){
                    arrayGc[i][j].setFill(Color.BLACK);
                    arrayGc[i][j].fillRect(0,0,tetrisTiles[i][j].getWidth(),
                                           tetrisTiles[i][j].getHeight());

                    arrayGc[i][j].setFill(Color.BLUE);
                    arrayGc[i][j].fillRect(2,2,tetrisTiles[i][j].getWidth() - 2,
                                           tetrisTiles[i][j].getHeight() - 2);
                }
                //if occupied
                if(tegnBrett[j+1][i] >= 1){
                    findColor(tegnBrett[j+1][i],i,j);
                    arrayGc[i][j].fillRect(0,0,tetrisTiles[i][j].getWidth(),
                                           tetrisTiles[i][j].getHeight());
                }

                //if Ghost
                if(tegnBrett[j+1][i] == -1){
                    arrayGc[i][j].setFill(Color.DIMGRAY);
                    arrayGc[i][j].fillRect(0,0,tetrisTiles[i][j].getWidth(),
                                           tetrisTiles[i][j].getHeight());

                    arrayGc[i][j].setFill(Color.BLUE);
                    arrayGc[i][j].fillRect(2,2,tetrisTiles[i][j].getWidth() - 2,
                                           tetrisTiles[i][j].getHeight() - 2);
                }
            }
        }

    }

    private void findColor(int colorID, int i, int j){

        switch (colorID) {

            // I shape --> Isblå
        case 1: case 101: case 100: // case 100: just for testing sake
            arrayGc[i][j].setFill(Color.CYAN);
            break;

            // J shape --> mørkeblå
        case 2: case 102:
	    arrayGc[i][j].setFill(Color.ROYALBLUE);
            break;

            // L shape --> orange
        case 3: case 103:
	    arrayGc[i][j].setFill(Color.ORANGE);
            break;

            // O shape --> gul
        case 4: case 104:
	    arrayGc[i][j].setFill(Color.YELLOW);
            break;

            // S shape --> Grøn
        case 5: case 105:
	    arrayGc[i][j].setFill(Color.GREEN);
            break;

            // T shape --> lilla
        case 6: case 106:
	    arrayGc[i][j].setFill(Color.PURPLE);
            break;

            // z shape --> rød
        case 7: case 107:
	    arrayGc[i][j].setFill(Color.RED);
            break;

        default:
	    arrayGc[i][j].setFill(Color.BLACK);
            break;
        }

    }

    public void setBoard(Board board){
        this.board = board;
    }

}
