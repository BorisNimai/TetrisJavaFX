import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.Node;

class LeftSideOfBoard{

    private GridPane gp;
    private int nextIndex = 1;
    private int dimention;
    private int fromPosistion = 0;

    private ArrayList<Canvas> canvasList;
    private ArrayList<GraphicsContext> gcList;

    public LeftSideOfBoard(GridPane gp, Bag bag, int currentIndex){
        this.nextIndex = currentIndex + 1;
        this.gp = gp;
        Canvas tempCanvas;
        GraphicsContext tempGc;

        for(int i = 0; i <80; i++){
            tempCanvas = new Canvas(10,10);



            tempGc = tempCanvas.getGraphicsContext2D();
            tempGc.setFill(Color.WHITE);
            tempGc.fillRect(1,1,9,9);
	   

            if(i <bag.getBag().get(nextIndex).getTetrimino().size()/4
               && bag.getBag().get(nextIndex).getTetrimino().get(i) != 0){
                tempGc.setFill(Color.RED);
                tempGc.fillRect(1,1,9,9);


            }
            if(bag.getBag().get(nextIndex).getTetrimino().size()/4 == 9){
                dimention =3;
            }else{
                dimention = 3;
            }

            gp.add(tempCanvas,i%dimention,i/dimention);
	    


            //gcList.add(tempGc);
            //   canvasList.add(tempCanvas);
            //drawNextTetriminos();
        }
    }
/*
  public void upDate(int nextIndex, ArrayList<Shape> bag){
  this.nextIndex = nextIndex;
  this.bag = bag;
  }

  public void drawNextTetriminos(){
  Shape next0 = bag.get(nextIndex);
  //   Shape next1 = bag.get(nextIndex + 1);
  //shape next2 = bag.get(nextIndex + 2);
  // shape next3 = bag.get(nextIndex + 4);
  //shape next4 = bag.get(nextIndex + 5);

  for(int i = 0; i < 80; i++){
  for(int j = 0; j < 16; j++ ){
  if(next0.getTetrimino().get(j) != 0){
  ((Canvas)getNodeFromGridPane(gp,i%4,i/4)).getGraphicsContext2D().setFill(Color.RED);
  ((Canvas)getNodeFromGridPane(gp,i%4,i/4)).getGraphicsContext2D().fillRect(1,1,9,9);
  }
  }
  }



  }
  public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
  for (Node node : gridPane.getChildren()) {
  if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
  return node;
  }
  }
  return null;
  }
*/

}
