package com.learn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @program: snake
 * @description: 游戏面板
 * @author: HuQi
 * @date: 2021/3/31 20:05
 * @Version: 1.0
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    /**
     *定义蛇的数据结构
     *蛇的长度,蛇的X坐标,蛇的Y坐标,初始化方向,游戏当前的状态默认是停止
     */
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String fx;

    boolean isStart = false;
    boolean isFail = false;

    String fxR = "R";
    String fxL = "L";
    String fxU = "U";
    String fxD = "D";

    int snakeMinX =25;
    int snakeMaxX=850;
    int snakeMinY =75;
    int snakeMaxY =650;
    int score;

    /**
     *食物的坐标
     */
     int foodX;
     int foodY;
     Random random = new Random();

    /**
     * 定时器,100毫秒执行一次
     */
    Timer timer = new Timer(100,this);


    public GamePanel() {
        init();
    }

    /**
     * @description: 初始化方法 
     * @params: [] 
     * @return: void
     * @exception:        
     * @author: HuQi
     * @date: 2021/4/1 14:43
     */ 
    public void init() {
        //蛇初始长度
      length = 3;
        //蛇脑袋的坐标
      snakeX[0] = 100; snakeY[0] = 100;
        //蛇第一个身体的坐标
      snakeX[1] = 75; snakeY[1] = 100;
        //蛇第二个身体的坐标
      snakeX[2] = 50; snakeY[2] = 100;
        //初始方向
       fx = "R";
        //游戏一开始定时器就启动
       timer.start();
        //把食物随机分配在界面上
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);
        score = 0;
    }

    /**
   *绘制画板,游戏当中的所有东西都是用这个画笔绘制
   */
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        //设置背景色为白色
        this.setBackground(Color.white);
        //头部广告栏画上去
        Data.header.paintIcon(this,g,25,11);
        //画一个矩形(游戏界面)
        g.fillRect(25,75,850,600);
        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度"+length,750,35);
        g.drawString("分数"+score,750,50);

        //把食物画上去
        Data.food.paintIcon(this,g,foodX,foodY);
        //把小蛇画上去(蛇头,第一个身体,第二个身体.....)

        if(fxR.equals(fx)){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fxL.equals(fx)){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fxU.equals(fx)){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fxD.equals(fx)){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for (int i = 1; i <length ; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //若游戏状态是停止提示按下空格开始游戏
        if(!isStart) {
           g.setColor(Color.white);
           g.setFont(new Font("微软雅黑",Font.BOLD,40));
           g.drawString("按下空格开始游戏",300,300);
        }
        //当失败时,提示按下空格开始游戏
        if(isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败,按下空格开始游戏",300,300);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
   /**
    * @description: 键盘监听事件
    * @params: [e] 
    * @return: void 
    * @exception:        
    * @author: HuQi
    * @date: 2021/4/1 17:18
    */ 
    @Override
    public void keyPressed(KeyEvent e) {
        //获得按下的是键盘的哪一个键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            if (isFail){
                isFail= false;
                init();
            }else {
                isStart = !isStart;
                repaint();
            }

        }
        if (keyCode == KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }else if (keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @description: 事件监听,需要通过固定的时间刷新,比如1秒10次
     * @params: [e]
     * @return: void
     * @exception:
     * @author: HuQi
     * @date: 2021/4/1 20:16
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏是开始状态,就让小蛇动起来
    if(isStart && !isFail){
        //吃食物后蛇的长度加1分数加10,并且重新生成食物
        if (snakeX[0] == foodX && snakeY[0] == foodY){
            length++;
            score+=10;
            foodX = 25 + 25*random.nextInt(34);
            foodY = 75 + 25*random.nextInt(24);
        }
        //移动(后一节身体移到前一节的位置,第一节移到头的位置snakeX[1] = snakeX[0])
        for (int i = length - 1; i > 0 ; i--) {
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
       //判断走向
        if (fxU.equals(fx)){
            snakeY[0] = snakeY[0]-25;
            if(snakeY[0] < snakeMinY) {snakeY[0] = snakeMaxY; }
        }else if (fxD.equals(fx)){
            snakeY[0] = snakeY[0]+25;
            if(snakeY[0] >snakeMaxY) {snakeY[0] = snakeMinY; }
        }else if (fxL.equals(fx)){
            snakeX[0] = snakeX[0]-25;
            if(snakeX[0] < snakeMinX) {snakeX[0] = snakeMaxX; }
        }else if (fxR.equals(fx)){
            snakeX[0] = snakeX[0]+25;
            //边界判断
            if(snakeX[0] > snakeMaxX) {snakeX[0] = snakeMinX; }
        }
            //失败判断,撞到自己算失败
        int i = 1;
        while (i <length) {
            if (snakeX[0] == snakeX[i]&&snakeY[0] == snakeY[i]){
                isFail = true;
                break;
            }
            i++;
        }

        //重画页面
          repaint();
    }

    }
}
