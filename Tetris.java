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

    //(10,20)Dimentions

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


        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                tempCanvas = new Canvas(20,20);
                tetrisArea.add(tempCanvas ,i ,j);              
                tetrisTiles[i][j] = tempCanvas;

                tgc = tempCanvas.getGraphicsContext2D();
                arrayGc[i][j] = tgc;

                tgc.setFill(Color.BLACK);
                tgc.fillRect(0,0,tempCanvas.getWidth(),tempCanvas.getHeight());
                tgc.setFill(Color.BLUE);
                tgc.fillRect(2,2,tempCanvas.getWidth()-2,tempCanvas.getHeight()-2);
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
            int n = 2; // speed
            int x = 0;
            int y = 0;
            int i = 0; //iterator
            
            
            @Override
            public void handle(long currentNanoTime){
                i++;
                if(i%n == 0){
                    x++;
                    x = x % 10;
                    if(x == 0){
                        y++;
                        y = y % 20;
                    }
                }
                arrayGc[x][y].setFill(Color.RED);

                arrayGc[x][y].fillRect(
                    0,0,tetrisTiles[x][y].getWidth(),
                    tetrisTiles[x][y].getHeight()
                    );
            }
        }.start();



        //********************
        primaryStage.show();
    }
}
