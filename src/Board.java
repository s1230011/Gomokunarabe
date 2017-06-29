import javax.swing.*;
import java.awt.*;

/**
 * Created by Nanako on 2017/06/22.
 */
public class Board extends JApplet{
    int T[][] = new int[5][5];
    int Wsize = 100;
    int Dsize = (Wsize*8/10);
    int x,y,i,j;
    int Xpos,Ypos;
    int flag = 0;


    Board(){
       T[2][2] = 1;
    }

    public void view(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,Wsize*5,Wsize*5);
        g.setColor(Color.BLACK);
        for(i=0; i<6; i++)
        {
            g.drawLine(0,Wsize*i,Wsize*5,Wsize*i);    // 横線
            g.drawLine(Wsize*i,0,Wsize*i,Wsize*5);    // 縦線
        }
        for(i=0,y=10; i<5; i++,y+=Wsize)
        {
            for(j=0,x=10; j<5; j++,x+=Wsize)
            {
            if (T[i][j]!=0) Disk(g,x,y,T[i][j]);
            }
        }
    }

    void  Disk(Graphics g, int x, int y, int s)
    {
        if (s==1)
        {   g.setColor(Color.black);
            g.fillOval(x,y,Dsize,Dsize);
        }
        if (s==-1)
        {   g.setColor(Color.white);
            g.fillOval(x,y,Dsize,Dsize);
        }
    }

    boolean Check(){  //空いてるところがあるか
        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                System.out.println(T[i][j]);
               if(T[i][j] == 0){
                   return true;
               }
            }
        }
        return false;
    }

    int CheckPlay(int player,int xp,int yp){ //駒を置く
        SetPos(xp,yp);
        if(T[Ypos][Xpos] == 1||T[Ypos][Xpos] == -1) return 1; //すでに駒がある場合
        if (Check()) {
            T[Ypos][Xpos] = player;
            finishCheck(player);
            if(flag != 0) return 2;
            return 0;
        }
        else {
            flag = 0;
            return 2;
        }
    }

    void finishCheck(int player){ //終わり判定
        int judge1=0,judge2=0,judge3=0,judge4=0,flag1=0,flag2=0;

        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                judge1 += T[i][j];
                if(judge1 == 5 || judge1 == -5) flag1 = 1;
            }
            judge1 = 0;
        }

        for(j=0;j<5;j++){
            for(i=0;i<5;i++){
                judge2 += T[i][j];
                if(judge2 == 5 || judge2 == -5) flag2 = 1;
            }
        }

        for(i=0;i<5;i++){
            judge3 += T[i][i];
            judge4 += T[4-i][i];
        }

        if(flag1 == 1||flag2 == 1||judge3 == 5||judge3 == -5||judge4 == 5||judge4 == -5){
             flag = player;
        }
    }

    void SetPos(int xp,int yp){
        Xpos = xp/Wsize;
        Ypos = yp/Wsize;
    }
}
