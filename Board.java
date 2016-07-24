class Board{
    private int[][] gameBoard = new int[10][21];

    Board(){
        for(int i = 0; i<10;i++){
            for(int j = 0; j < 21; j++){
                gameBoard[i][j] = 0;
            }
        }
    }


    
    //ReturnBoard
    /*
      000l000000
      000lll0000
      ----------
      0000000000
      0000000000
      0000000000
      0000000000
      .
      .
      .
      1110000000
      1101000000

    */

}
