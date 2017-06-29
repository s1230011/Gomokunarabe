import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nanako on 2017/06/22.
 */
public class Gomokunarabe extends JPanel implements MouseListener{
    Board board;
    int state = 0;
    int player = -1;


    public void main(String[] args){
      JFrame frame = new JFrame("Gomokunarabe");
      setSize(800,500);
    }

    public void init(){
        board = new Board();
        addMouseListener(this);
    }

    public void paint(Graphics g){
        Dimension size = getSize();
        board.view(g);
    }

    public void mouseClicked(MouseEvent e){
        int check;

        switch(state){
            case 0:
                System.out.println("Game start!");
                state = 1;
                break;
            case 1:
                check = board.CheckPlay(player,e.getX(),e.getY());
                switch(check){
                    case 0:
                        player = 0-player;
                        break;
                    case 1:
                        player = player;
                        System.out.println("Don't put disk");
                        break;
                    case 2:
                        System.out.println("Finish");
                        if(board.flag == 1) System.out.println("Black win!");
                        else if (board.flag == -1) System.out.println("White win!");
                        else if(board.flag == 0) System.out.println("Draw!");
                        break;
                }
                break;
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
