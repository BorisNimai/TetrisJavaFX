import java.util.Arrays;
class Jtet extends Shape{
    
    // [1][ ][ ]      [ ][1][1]       [ ][ ][ ]       [ ][1][ ]
    // [1][1][1]      [ ][1][ ]       [1][1][1]       [ ][1][ ]
    // [ ][ ][ ]      [ ][1][ ]       [ ][ ][1]       [1][1][ ]

    // [ ][ ][ ][ ][1][1][ ][ ][ ][ ]
    // [ ][ ][ ][1][1][ ][ ][ ][ ][ ]

    Jtet(){
        this.tetrimino.addAll(Arrays.asList(0,1,1,   //[ ][1][1]/
                                            1,1,0,   //[1][1][ ]/ 1
                                            0,0,0,   //[ ][ ][ ]/
                                            
                                            0,1,0,   //[ ][1][ ]:
                                            0,1,1,   //[ ][1][1]: 2
                                            0,0,1,   //[ ][ ][1]:
                                            
                                            0,0,0,   //[ ][ ][ ]/
                                            0,1,1,   //[ ][1][1]/ 3
                                            1,1,0,   //[1][1][ ]/
                                            
                                            1,0,0,   //[1][ ][ ]:
                                            1,1,0,   //[1][1][ ]: 4
                                            0,1,0)); //[ ][1][ ]:
        this.dimentionX = 3;
        this.dimentiony = 3;
    }
    
}