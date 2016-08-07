import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;


import javafx.animation.AnimationTimer;   //gameLoop

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

//import java.util.Random;


public class Tetris extends Application{
    /* public static void main(String args[]){
       launch(args);
       }

    */
    static public int speed = 60; //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    static public int ogSpeed = 60;
    ArrayList<Shape> tetriminoBag;
    


    public void start(Stage primaryStage){


        //Setup
        primaryStage.setTitle("Tetris");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);


        // Tetris "canvas" setup
        GridPane tetrisGrid = new GridPane();

	VBox rightSide = new VBox();
        root.setCenter(tetrisGrid);
        root.setRight(rightSide);

	


        Board board = new Board();
        Score score = new Score(0, board);

	//RIGHT SIDE MENU class
	RightSideOfBoard rsob = new RightSideOfBoard(rightSide,tetriminoBag,score);
	
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
        Bag bag = new Bag(board);

        bag.generateBag();
        tetriminoBag = bag.getBag();
        tetriminoBag.get(0).spawnTetrimino();


	
        //GameLoop
        new AnimationTimer()
        {
            int fi = 0; //iterator\
            int tetriminoCounter = 0;


            @Override
            public void handle(long currentNanoTime){
                if(!tetriminoBag.get(tetriminoCounter).getGameOverStatus()){
                    if(tetriminoBag.get(tetriminoCounter).status()){
                        tetriminoCounter++;
                        if(tetriminoCounter == 7){
                            bag.generateBag();
                            tetriminoBag.addAll(bag.getBag());
                            tetriminoCounter = 0;
                        }

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
                        speed = 3;
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
		    // rsob.upDate(tetriminoBag,tetriminoCounter);
		    rsob.drawScore();
		    
                    drawBoard.paintBoard();
                }
                //GAME OVER
                else{
                    System.out.println("GAME OVER\t\t" + score.getScore()+"\t\t" + score.getLvl());
                }
            }
        }.start();



        //********************
        primaryStage.show();
    }
}
