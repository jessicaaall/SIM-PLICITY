package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel implements ActionListener {
    public MainPanel mp;
    public MainMenuPanel mmp;
    public JButton backButton;
    HelpPanel(MainPanel mp){
        this.mp = mp;
        backButton = new JButton("Back to Main Menu");
        setLayout(null);
        setPreferredSize(mp.getPreferredSize());
        setBounds(0,0, 800, 640);
        setVisible(true);
        backButton.setBounds(300, 480, 200, 100);
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        backButton.setForeground(Color.black);
        backButton.addActionListener(this);
        add(backButton);
//        backButton.setVisible(true);
    }

    public void paintComponent(Graphics g){
        String caraBermain = "Cara Bermain\n" +
                "1. Tekan \"Start Game\"\n" +
                "2. Pilih apakah mau buat World baru \natau load World yang sudah ada\n" +
                "3. Mainkan saja";
        Graphics2D g2d = (Graphics2D) g;
        String[] lines = caraBermain.split("\n");
        int y = 50;
        g2d.setFont(new Font("Impact",Font.PLAIN,25));
        g2d.setPaint(Color.black);
        for (String line:lines){
            g2d.drawString(line, 200, y);
            y += 30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            mp.add(mmp,BorderLayout.CENTER);
            mp.remove(this);
            mp.revalidate();
            mp.repaint();
        }
    }
}
