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

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Random;


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



        int[] tetrimini = {5,6,7,7,5,5,5,6};////
        board.drawStationary(tetrimini, 4);////


        Ttet ttet = new Ttet(board);////
        ttet.spawnTetrimino();/////


        DrawBoard drawBoard = new DrawBoard(tetrisGrid, board);

        ArrayList<String> input = new ArrayList<String>();

        // hashmap here ?? hmm

        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent e){
                    String code = e.getCode().toString();
                    if(!input.contains(code)){
                        input.add(code);
                        System.out.println(code);
                    }
                }
            });

        ///game bag
        Random rand = new Random();
        int n;
        ArrayList<Shape> tetriminoBag = new ArrayList<Shape>();

        for(int i = 0; i < 7; i++){
            n = rand.nextInt(6);
            switch(n){
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

	


        //GameLoop
        new AnimationTimer()
        {
            int[][] tegnBrett1;//ææææææææææææææææææææææ
            int n = 30; // speed
            int fi = 0; //iterator
            int lft = 0;
            int lol = 0; //%%%%%%%%%%%%%
	    int tetriminoCounter = 0;
	    
            @Override
            public void handle(long currentNanoTime){
		if(tetriminoBag.get(tetriminoCounter).status()){
		    tetriminoCounter++;
		}
		
		fi++;
		
                // move left
                if(input.contains("LEFT")){
                    tetriminoBag.get(tetriminoCounter).moveLeft();
                    input.remove("LEFT");
                }

                //move right
                if(input.contains("RIGHT")){
                    tetriminoBag.get(tetriminoCounter).moveRight();
                    input.remove("RIGHT");
                }

                //drop
                if(input.contains("SPACE")){
                    tetriminoBag.get(tetriminoCounter).drop();
                    input.remove("SPACE");
                }

                //rotate left
                if(input.contains("Z")){
                    tetriminoBag.get(tetriminoCounter).rotateLeft();
                    input.remove("Z");
                }
		
                //rotate right
                if(input.contains("X")){
                    tetriminoBag.get(tetriminoCounter).rotateRight();
                    input.remove("X");
                }

                //hold (c)

                // down (softdrop )

                // pause esc



                if(fi%n == 0){
                    tetriminoBag.get(tetriminoCounter).timeStep();
                }

                drawBoard.paintBoard();

            }
        }.start();



        //********************
        primaryStage.show();
    }
}
