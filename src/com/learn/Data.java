package com.learn;

import javax.swing.*;
import java.net.URL;

/**
 * @program: snake
 * @description: 数据
 * @author: HuQi
 * @date: 2021/3/31 20:25
 * @Version: 1.0
 */
public class Data {
    public static URL bodyURL = Data.class.getResource("/static/body.png");
    public static URL downURL = Data.class.getResource("/static/down.png");
    public static URL foodURL = Data.class.getResource("/static/food.png");
    public static URL headerURL = Data.class.getResource("/static/header.png");
    public static URL leftURL = Data.class.getResource("/static/left.png");
    public static URL rightURL = Data.class.getResource("/static/right.png");
    public static URL upURL = Data.class.getResource("/static/up.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon food = new ImageIcon(foodURL);
    public static ImageIcon header = new ImageIcon(headerURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon up = new ImageIcon(upURL);

   
}
