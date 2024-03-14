import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class GUI extends JFrame {
    private final int blocksInLine;
    private boolean firstClick = false;
    private int revealdBlocksCounter = 0;
    private final int[][] gameTable;
    private final ArrayList<Block> blocks = new ArrayList<> ();
    private int flagsLeft;
    private int minesFlagged;
    private final Display3x7 flagCounterD = new Display3x7 (0, 0, "flag_bcg.png");
    private final Display3x7 timer;
    private final Face face;


    public GUI(int bl, int mines) {
        this.setTitle ("Saper");
        blocksInLine = bl;
        flagsLeft = mines;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setResizable (false);
        gameTable = new int[blocksInLine][blocksInLine];
        int spacing = 5;
        // margin [up,right,down,left];
        int[] margin = {40, 10, 10, 20};
        int blockSize = 25;
        int windowHeight = blocksInLine * blockSize + margin[0] + margin[2] + (blocksInLine - 1) * spacing;
        int windowWidth = blocksInLine * blockSize + margin[1] + margin[3] + (blocksInLine - 1) * spacing;
        timer = new Display3x7 (windowWidth - 78, 0, "clock.png");
        face = new Face ((windowWidth + 14) / 2 - 12, 4, 25, 25, this, true);
        this.setPreferredSize (new Dimension (windowWidth + 14, windowHeight + 40));
        this.setBackground (Color.GRAY);
        System.out.print (windowWidth + "\n");
        System.out.print (this.getPreferredSize ());
        System.out.print (this.getSize ());
        System.out.print (this.getBounds () + "\n");
        new Utils (blocksInLine, mines, gameTable);
        Board board = new Board ();
        this.add (board);
        this.setContentPane (board);


        int x = margin[3];
        int y = margin[0];
        for (int i = 0; i < blocksInLine; i++) {
            for (int j = 0; j < blocksInLine; j++) {
                blocks.add (new Block (blockSize, i, j, x, y, this));
                x = x + blockSize + spacing;
            }
            x = margin[3];
            y = y + blockSize + spacing;
        }
        blocks.forEach (this::add);
        flagCounterD.setValue (flagsLeft);
        this.add (flagCounterD);
        this.add (timer);
        this.add (face);
        this.pack ();
        this.setVisible (true);
    }

    public int getBlocksInLine() {
        return blocksInLine;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public int getGameTable(int r, int c) {
        return gameTable[r][c];
    }

    public Block getArrayList(int a) {
        return blocks.get (a);
    }

    public boolean getFirstClick() {
        return firstClick;
    }

    public void setFirstClick() {
        firstClick = true;
    }
    public void startTimer(){
        timer.countTimer();
    }

    public void flagCounter(boolean down) {
        if (down)
            flagsLeft--;
        else
            flagsLeft++;
        flagCounterD.setValue (flagsLeft);
    }

    public void setMinesFlagged(boolean up) {
        if (up)
            minesFlagged++;
        else
            minesFlagged--;
        //System.out.println ("Total flagged mines: " + minesFlagged);
    }

    public void revealedBlocksCounterFun(boolean up) {
        if (up)
            revealdBlocksCounter++;
        else
            revealdBlocksCounter--;
    }

    public void endGame() {
        face.changeFace ("x.png");
        timer.stopTimer ();
        blocks.forEach (Block::endGame);
    }

    public void isWin() {
        if (revealdBlocksCounter + minesFlagged == blocksInLine * blocksInLine) {
            face.changeFace ("tick.png");
            timer.stopTimer ();
        }
    }

    public static class Board extends JComponent {
        public Board() {
            //this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        }
    }
}