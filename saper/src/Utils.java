import java.util.Random;
public class Utils {
    public Utils(int inlineBlocks, int minesNum, int[][] matrix) {
        new Random ();
        for (int i = 0; i < inlineBlocks; i++) {
            for (int j = 0; j < inlineBlocks; j++) {
                matrix[i][j] = 0;
            }
        }
        int currMines = 0;
        while (currMines < minesNum) {
            int row = (int)Math.floor(Math.random()*(inlineBlocks));
            int col = (int)Math.floor(Math.random()*(inlineBlocks));
            if (matrix[row][col] != 9) {
                int localMines = 0;
                for(int i = 0; i < inlineBlocks; i++){
                    if(matrix[row][i] == 9 || matrix[i][col] == 9)
                        localMines++;
                }
                int maxInLine = inlineBlocks/2 + 1;
                if(localMines > maxInLine)
                    continue;
                matrix[row][col] = 9;
                currMines++;
                if ((row - 1) >= 0) {
                    if ((col - 1) >= 0)
                        if (matrix[row - 1][col - 1] != 9)
                            matrix[row - 1][col - 1]++;
                    if (matrix[row - 1][col] != 9)
                        matrix[row - 1][col]++;
                    if ((col + 1) < inlineBlocks)
                        if (matrix[row - 1][col + 1] != 9)
                            matrix[row - 1][col + 1]++;
                }
                if ((col - 1) >= 0)
                    if (matrix[row][col - 1] != 9)
                        matrix[row][col - 1]++;
                if ((col + 1) < inlineBlocks)
                    if (matrix[row][col + 1] != 9)
                        matrix[row][col + 1]++;

                if ((row + 1) < inlineBlocks) {
                    if ((col - 1) >= 0)
                        if (matrix[row + 1][col - 1] != 9)
                            matrix[row + 1][col - 1]++;
                    if ((col + 1) < inlineBlocks)
                        if (matrix[row + 1][col + 1] != 9)
                            matrix[row + 1][col + 1]++;
                    if (matrix[row + 1][col] != 9)
                        matrix[row + 1][col]++;
                }
            }
        }
//        System.out.println ("Mines generated: " + currMines);
//        for (int i = 0; i < inlineBlocks; i++) {
//            for (int j = 0; j < inlineBlocks; j++) {
//                System.out.print (matrix[i][j]);
//            }
//            System.out.println ();
//        }
    }
}
