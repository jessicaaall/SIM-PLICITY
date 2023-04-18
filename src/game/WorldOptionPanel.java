package game;

import entity.Rumah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class WorldOptionPanel extends JPanel implements ActionListener {
    public JButton toMainMenuButton = new JButton("<html>Back to<br>Main Menu</html>");
    public JButton addHouseButton = new JButton("Add House");
    public MainPanel mp;
    public WorldPanel wp;
    public WorldOptionPanel(MainPanel mainPanel, WorldPanel worldPanel){
        mp = mainPanel; wp = worldPanel;
//        this.setBounds(2,mp.getHeight()/4, (mp.getWidth() - wp.getWidth())/2 -4, mp.getHeight()/2);
        this.setPreferredSize(new Dimension((mp.getWidth() - wp.getWidth())/3 -4, mp.getHeight()/2));
        this.setBackground(Color.gray);
        toMainMenuButton.setFocusable(false);
        toMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        toMainMenuButton.setVerticalTextPosition(JButton.CENTER);
        toMainMenuButton.setFont(new Font("Comic Sans", Font.BOLD, 15));

        toMainMenuButton.addActionListener(this);
        toMainMenuButton.setBackground(Color.yellow);
        addHouseButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        addHouseButton.addActionListener(this);
        addHouseButton.setFocusable(false);
        addHouseButton.setHorizontalTextPosition(JButton.CENTER);
        addHouseButton.setVerticalTextPosition(JButton.CENTER);
        addHouseButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        add(toMainMenuButton);
        add(addHouseButton);
        wp.wop = this;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toMainMenuButton){
            System.out.println("Exit Game");
            wp.stopMusic();
            wp.mainThread.interrupt();
            wp.mainThread =null;
            mp.remove(toMainMenuButton);
            mp.add(wp.mmp, BorderLayout.CENTER);
            mp.remove(this);
            mp.remove(wp);
            mp.revalidate();
            mp.repaint();
        }
        else if (e.getSource() == addHouseButton){
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel label = new JLabel("<html>Set the coordinate of your house:<br></html>");
            JTextField xField = new JTextField();
            JTextField yField = new JTextField();
            JTextField colorField = new JTextField();
            panel.add(label);
            panel.add(new JLabel("<html>X Coordinate:<br></html>"));
            panel.add(xField);
            panel.add(new JLabel("<html>Y Coordinate:<br></html>"));
            panel.add(yField);
            panel.add(new JLabel("<html>Color:<br></html>"));
            panel.add(colorField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Add House",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                String colorText = colorField.getText();
                Field field;
                try {
                    field = Color.class.getDeclaredField(colorText);
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException(ex);
                }
                field.setAccessible(true);
                Color color;
                try {
                    color = (Color) field.get(new Color(0,0,0));
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                // Do something with x and y...
                Rumah rumahBaru = new Rumah(x, y, color, wp.getWorld());
                wp.getWorld().tambahRumah(rumahBaru);
            }
        }
    }
}
