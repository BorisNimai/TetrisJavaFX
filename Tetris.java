import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;

//gameLoop
import javafx.animation.AnimationTimer;





public class Tetris extends Application{
    /* public static void main(String args[]){

    //(10,20)Dimentions

    //[][]
    //  [][] Z

    //  [][]
    //[][]   S

    //[][]
    //[][]   O

    //[][][][] I

    //[][]
    //  []
    //  []   L

    //[][]
    //[]
    //[]     J

    //   []  T
    // [][][]


    score
    1 Lines = 40
    2 Lines = 100
    3 Lines = 300
    4 Lines = 1200


    launch(args);
    }

    */

    public void start(Stage primaryStage){

        //Setup
        primaryStage.setTitle("Tetris");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);


        // Tetris "canvas" setup
        GridPane tetrisArea = new GridPane();
        root.setCenter(tetrisArea);

        //temp , I think i will make a class out of this
        // setup of board

        Canvas tempCanvas;
        Canvas[][] tetrisTiles = new Canvas[10][20];

        GraphicsContext tgc;
        GraphicsContext[][] arrayGc = new GraphicsContext[10][20];

        Board board = new Board();

        int[] tetrimini = {4,5,6,6,5,5,5,6};
        board.drawStationary(tetrimini);



        Ttet ttet = new Ttet(board);
        ttet.spawnTetrimino();
        int[][] tegnBrett = board.returnBoard();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                tempCanvas = new Canvas(20,20);
                tetrisArea.add(tempCanvas ,i ,j);
                tetrisTiles[i][j] = tempCanvas;

                tgc = tempCanvas.getGraphicsContext2D();
                arrayGc[i][j] = tgc;

                // if 0
                if(tegnBrett[j+1][i] == 0){
                    tgc.setFill(Color.BLACK);
                    tgc.fillRect(0,0,tempCanvas.getWidth(),tempCanvas.getHeight());
                    tgc.setFill(Color.BLUE);
                    tgc.fillRect(2,2,tempCanvas.getWidth()-2,tempCanvas.getHeight()-2);
                }
                //if 1 ****
                if(tegnBrett[j+1][i] == 1 || tegnBrett[j+1][i] >= 100){
                    tgc.setFill(Color.RED);
                    tgc.fillRect(0,0,tempCanvas.getWidth(),tempCanvas.getHeight());
                }

            }
        }

        //TODO: Make class tjat takes in the 2 2s arrays
        //      Then make subclasses or something for eatch tetrimino
        //
        // make gameboard class 000
        //                      000
        //                      011
        // use it to compare and save where stuff are on the gameb


        //GameLoop
        new AnimationTimer()
        {
            int[][] tegnBrett1;
            int n = 30; // speed
            int fi = 0; //iterator
            int lft = 0;
            int lol = 0; //%%%%%%%%%%%%%

            @Override
            public void handle(long currentNanoTime){
                fi++;

                if(fi%n == 0){
                    ttet.timeStep();
                    lft++;
                    ttet.rotateLeft();
                }


                tegnBrett1 = board.returnBoard();////////
                //************************************************************
                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 20; j++){


                        // if 0
                        if(tegnBrett1[j+1][i] == 0){
                            // tgc.setFill(Color.BLACK);
                            //tgc.fillRect(0,0,tempCanvas.getWidth(),tempCanvas.getHeight());
                            //tgc.setFill(Color.BLUE);
                            //tgc.fillRect(2,2,tempCanvas.getWidth()-2,tempCanvas.getHeight()-2);

                            arrayGc[i][j].setFill(Color.BLACK);
                            arrayGc[i][j].fillRect(0,0,tetrisTiles[i][j].getWidth(),
                                                   tetrisTiles[i][j].getHeight());

                            arrayGc[i][j].setFill(Color.BLUE);
                            arrayGc[i][j].fillRect(2,2,tetrisTiles[i][j].getWidth() - 2,
                                                   tetrisTiles[i][j].getHeight() - 2);

                        }
                        //if 1 ****
                        if(tegnBrett1[j+1][i] == 1 || tegnBrett1[j+1][i] >= 100){
                            //tgc.setFill(Color.RED);
                            //tgc.fillRect(0,0,tempCanvas.getWidth(),tempCanvas.getHeight());
                            arrayGc[i][j].setFill(Color.RED);
                            arrayGc[i][j].fillRect(0,0,tetrisTiles[i][j].getWidth(),
                                                   tetrisTiles[i][j].getHeight());
                        }
                    }
                }

                //************************************************************

            }
        }.start();



        //********************
        primaryStage.show();
    }
}
