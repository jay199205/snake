package com.learn;

import javax.swing.*;

/**
 * @program: snake
 * @description: 启动类
 * @author: HuQi
 * @date: 2021/3/31 19:28
 * @Version: 1.0
 */
public class StartGame  {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        start(jFrame);
    }

    public static void start(JFrame jFrame) {
        // 设置窗口可见
        jFrame.setVisible(true);
        //设置窗口不能由用户调整大小
        jFrame.setResizable(false);
        //设置窗口大小
        jFrame.setBounds(10,10,900,720);
        //退出应用程序默认窗口关闭操作
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("贪吃蛇");
        GamePanel gamePanel = new GamePanel();
        jFrame.add(gamePanel);
        //获得焦点和键盘事件
        jFrame.setFocusable(true);
        jFrame.addKeyListener(gamePanel);
    }
}
