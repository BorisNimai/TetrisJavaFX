import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;


import javafx.animation.AnimationTimer;   //gameLoop


public class Tetris extends Application{
    /* public static void main(String args[]){

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
        GridPane tetrisGrid = new GridPane();
        root.setCenter(tetrisGrid);

        Board board = new Board();



        int[] tetrimini = {5,6,7,7,5,5,5,6};
        board.drawStationary(tetrimini);


        Ttet ttet = new Ttet(board);
        ttet.spawnTetrimino();


        DrawBoard drawBoard = new DrawBoard(tetrisGrid, board);

        //GameLoop
        new AnimationTimer()
        {
            int[][] tegnBrett1;//ææææææææææææææææææææææ
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
                    ttet.rotateRight();
                }

		drawBoard.paintBoard();
		

            }
        }.start();



        //********************
        primaryStage.show();
    }
}
