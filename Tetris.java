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
       launch(args);
       }

    */
    static public int speed = 60; //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    static public int ogSpeed = 60;


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
	Score score = new Score(0, board);

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
                    if(input.contains("DOWN")){
                        input.remove("DOWN");
                        speed = ogSpeed;
                    }
                }
            });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent e){
                    String code = e.getCode().toString();
                    if((!input.contains(code)) && (code.compareTo("DOWN") == 0)){
                        input.add(code);
                        System.out.println(code);
                    }
                }
            });

        ///game bag
        Random rand = new Random();
        int m;
        ArrayList<Shape> tetriminoBag = new ArrayList<Shape>();

        for(int i = 0; i < 7; i++){
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
	
	
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Jtet(board));
        tetriminoBag.add(new Otet(board));
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Jtet(board));
        tetriminoBag.add(new Otet(board));
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Ttet(board));
        tetriminoBag.add(new Stet(board));
        tetriminoBag.add(new Jtet(board));
        tetriminoBag.add(new Otet(board));
        tetriminoBag.get(0).spawnTetrimino();

        //GameLoop
        new AnimationTimer()
        {
            int fi = 0; //iterator\
            int tetriminoCounter = 0;

            @Override
            public void handle(long currentNanoTime){
                if(tetriminoBag.get(tetriminoCounter).status()){
                    tetriminoCounter++;
                    tetriminoBag.get(tetriminoCounter).spawnTetrimino();
                }

	     
                fi++;


		//INPUT
		
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

                if(input.contains("DOWN")){
                    speed = 1;
                    input.remove("DOWN");
                }


                //hold (c)

                // pause esc


		//TIME STEP
		if(fi%speed == 0){
                    tetriminoBag.get(tetriminoCounter).timeStep();
		}

		//Removes lines and calculates the score
		score.calculateScore();

                drawBoard.paintBoard();

            }
        }.start();



        //********************
        primaryStage.show();
    }
}
