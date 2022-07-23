package com.TankWar;

import javax.swing.*;
import java.awt.*;

public class HelpView extends JDialog {
    public HelpView(Frame owner, String title) {
        super(owner, title);
        //获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //窗口框架
        setResizable(false);
        setBounds(360, 200, 700, 630);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //载入画面
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/helpView.jpg"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(new Rectangle(700, 593));
        this.add(jLabel);
    }
}
