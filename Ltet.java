import java.util.Arrays;

class Ltet extends Shape{


    //[ ][ ][ ][ ][ ][1][ ][ ][ ][ ]
    //[ ][ ][ ][1][1][1][ ][ ][ ][ ]

    // state:
    //     1              2               3               4
    // [ ][ ][1]      [ ][1][ ]       [ ][ ][ ]       [1][1][ ]
    // [1][1][1]      [ ][1][ ]       [1][1][1]       [ ][1][ ]
    // [ ][ ][ ]      [ ][1][1]       [1][ ][ ]       [ ][1][ ]

    Ltet(){
        this.tetrimino.addAll(Arrays.asList(0,0,1,   //[ ][ ][1]/
                                            1,1,1,   //[1][1][1]/ 1
                                            0,0,0,   //[ ][ ][ ]/
                                            
                                            0,1,0,   //[ ][1][ ]:
                                            0,1,0,   //[ ][1][ ]: 2
                                            0,1,1,   //[ ][1][1]:
                                            
                                            0,0,0,   //[ ][ ][ ]/
                                            1,1,1,   //[1][1][1]/ 3
                                            1,0,0,   //[1][ ][ ]/
                                            
                                            1,1,0,   //[1][1][ ]:
                                            0,1,0,   //[ ][1][ ]: 4
                                            0,1,0)); //[ ][1][ ]:
    }
    






}