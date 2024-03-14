import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

public class Block extends JComponent {
    private int blockSize;
    private final int blocksInLine;
    private int row;
    private int col;
    private boolean hovered = false;
    private boolean mined = false;
    private boolean revealed = false;
    private boolean isFlagged = false;
    private final GUI gui;
    private final BlockMouse bm = new BlockMouse ();

    public Block(int bs, int r, int c, int x, int y, GUI mainGUI) {
        this.setVisible (true);
        gui = mainGUI;
        blocksInLine = mainGUI.getBlocksInLine ();
        this.addMouseListener (bm);
        setBlockSize (bs);
        setPosition (c, r);
        this.setPreferredSize (new Dimension (bs, bs));
        this.setBounds (x, y, blockSize, blockSize);
    }

    public void setBlockSize(int bs) {
        blockSize = bs;
    }

    public void setPosition(int c, int r) {
        row = r;
        col = c;
    }
//    public void getPosition() {
//        System.out.println("Row = "+row+" Column = "+col);
//    }

    public void setHover(boolean flag) {
        hovered = flag;
    }

    public boolean isValid(int r, int c) {
        return ((r >= 0) && (r < blocksInLine) && (c >= 0) && (c < blocksInLine) && (!gui.getArrayList (blocksInLine * (r) + (c)).isFlagged));
    }

    public void floatIsland(int r, int c) {
        //System.out.println ("\n" + r + " " + c);
        if (isValid (r - 1, c - 1))
            gui.getArrayList (blocksInLine * (r - 1) + (c - 1)).reveal ();

        if (isValid (r - 1, c))
            gui.getArrayList (blocksInLine * (r - 1) + (c)).reveal ();

        if (isValid (r - 1, c + 1))
            gui.getArrayList (blocksInLine * (r - 1) + (c + 1)).reveal ();

        if (isValid (r, c + 1))
            gui.getArrayList (blocksInLine * (r) + (c + 1)).reveal ();

        if (isValid (r, c - 1))
            gui.getArrayList (blocksInLine * (r) + (c - 1)).reveal ();

        if (isValid (r + 1, c - 1))
            gui.getArrayList (blocksInLine * (r + 1) + (c - 1)).reveal ();

        if (isValid (r + 1, c + 1))
            gui.getArrayList (blocksInLine * (r + 1) + (c + 1)).reveal ();

        if (isValid (r + 1, c))
            gui.getArrayList (blocksInLine * (r + 1) + (c)).reveal ();
    }

    public void reveal() {
        if (!revealed) {
            revealed = true;
            gui.revealedBlocksCounterFun (true);
            if (gui.getGameTable (row, col) == 0)
                floatIsland (row, col);
            if (gui.getGameTable (row, col) == 9) {
                mined = true;
                gui.endGame ();
            }
        }
        revealed = true;
        this.removeMouseListener (bm);
        this.paint (getGraphics ());
        gui.isWin ();
        //System.out.println ("\n Value = " + gui.getGameTable(row, col));
    }

    public void flaging() {
        if ((gui.getFlagsLeft () > 0) || (this.isFlagged)) {
            this.isFlagged = !this.isFlagged;
            gui.flagCounter (this.isFlagged);

            if (gui.getGameTable (row, col) == 9) {
                gui.setMinesFlagged (this.isFlagged);
            }
            this.paint (getGraphics ());
            gui.isWin ();
        }
    }

    public boolean getFlag() {
        return this.isFlagged;
    }

    public void endGame() {
        this.removeMouseListener (bm);
        if ((gui.getGameTable (row, col) == 9) && (!this.isFlagged)) {
            this.revealed = true;
            repaint ();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.hovered)
            g.setColor (Color.CYAN);
        else
            g.setColor (Color.GREEN);
        if (this.revealed) {
            String str = String.valueOf (gui.getGameTable (row, col));
            String s = str + ".png";
            if (mined)
                s = "mine_red.png";
            ImageIcon icon = new ImageIcon (Objects.requireNonNull (getClass ().getResource (s)));
            Image img1 = icon.getImage ();
            g.drawImage (img1, 0, 0, this);
        } else if (this.isFlagged) {
            ImageIcon icon = new ImageIcon (Objects.requireNonNull (getClass ().getResource ("flag.png")));
            Image img1 = icon.getImage ();
            g.drawImage (img1, 0, 0, this);
        } else
            g.fillRect (0, 0, blockSize, blockSize);
    }

    public class BlockMouse implements MouseInputListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            Block o = (Block) e.getSource ();
            if (SwingUtilities.isRightMouseButton (e)) {
                o.flaging ();
                //System.out.println("Block R-clicked ");
                //o.getPosition();
            } else if (!o.getFlag ()) {
                //System.out.println("Block L-clicked ");
                if (!gui.getFirstClick ())
                {
                    gui.setFirstClick();
                    gui.startTimer();
                }

                //o.getPosition();
                o.reveal ();
            }


        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Block o = (Block) e.getSource ();
            o.setHover (true);
            o.paint (getGraphics ());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Block o = (Block) e.getSource ();
            o.setHover (false);
            o.paint (getGraphics ());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // useless, but without is giving syntax
        }
    }

}