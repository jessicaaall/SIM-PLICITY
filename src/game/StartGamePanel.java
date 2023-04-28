package game;

import entity.Rumah;
import entity.Sim;
import entity.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Random;

public class StartGamePanel extends JPanel implements ActionListener {
    public MainPanel mp;
    public MainMenuPanel mmp;
    private World worldChoice;
    public JButton newWorldButton;
    public JButton loadWorldButton;
    public JButton backButton;


    public StartGamePanel(MainPanel mp,  MainMenuPanel mmp){
        this.mp = mp;
        this.mmp = mmp;
        mmp.startGamePanel = this;
        setLayout(null);
        setPreferredSize(new Dimension(mp.width, mp.height));
        setBounds(0,0, mp.width, mp.height);
        setOpaque(false);
        setDoubleBuffered(true);
        setLocation(getX(), (mp.getHeight()-getWidth())/2);
        StartGameButton sgb = new StartGameButton();
        sgb.setBounds((mp.width-400)/2, (mp.height -400)/2, 400, 400);
        this.add(sgb);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorldButton){

            JPanel newSimPanel = new JPanel(new GridLayout(0,1));
            newSimPanel.setBackground(Color.white);
            JTextField namaSim = new JTextField();
            JLabel message = new JLabel("<html> Masukkan nama Sim mu<br></hmtl>");
            newSimPanel.add(message);
            newSimPanel.add(namaSim);
            int res = JOptionPane.showConfirmDialog(null, newSimPanel, "Create a New Sim",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION){
                String stringNamaSim = namaSim.getText();
                worldChoice = new World();
                Sim addedSim = new Sim(stringNamaSim, worldChoice);
                System.out.println(addedSim.getKekenyangan());
                System.out.println(addedSim.getKesehatan());
                System.out.println(addedSim.getMood());
                System.out.println(addedSim.getPekerjaan().getNamaPekerjaan());
                Random random = new Random();
                Rumah rumahBaru = new Rumah(0,0, addedSim, new Color(random.nextInt(16777216)), worldChoice);
                Sim sim = rumahBaru.getSim();
                addedSim.setLocRuang(rumahBaru.getDaftarRuangan().get(0));
                addedSim.setKepemilikanRumah(rumahBaru);
                worldChoice.tambahRumah(rumahBaru);
                worldChoice.tambahSim(rumahBaru.getSim());
                worldChoice.startThread();
                showWorldPanel();
            }

        } else if (e.getSource() == backButton) {
            mp.remove(this);
            mp.add(mmp);
            mp.revalidate();
            mp.repaint();

        } else if (e.getSource() == loadWorldButton) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public World getWorldChoice(){
        return worldChoice;
    }
    public void showWorldPanel() {
        WorldPanel worldPanel = new WorldPanel(worldChoice, mp, mmp);
        mp.remove(this);
        worldPanel.startMainThread();
        mp.add(worldPanel, BorderLayout.CENTER);
        mp.revalidate(); // to update the layout
        mp.repaint(); // to repaint the panel
    }

    private class StartGameButton extends JPanel{
        StartGameButton(){
            super(new GridLayout(0,1));
            setSize(400,400);
            newWorldButton = new JButton("New World");
            loadWorldButton = new JButton("Load World");
            backButton = new JButton("To Main Menu");
            Font thisFont = new Font("Comic Sans MS", Font.BOLD, 25);
            newWorldButton.setFont(thisFont);
            loadWorldButton.setFont(thisFont);
            backButton.setFont(thisFont);
            newWorldButton.setFocusable(false);
            loadWorldButton.setFocusable(false);
            backButton.setFocusable(false);
            newWorldButton.setHorizontalAlignment(JButton.CENTER);
            newWorldButton.setVerticalAlignment(JButton.CENTER);
            newWorldButton.addActionListener(StartGamePanel.this);
            newWorldButton.setBackground(Color.white);

            //create load button
            loadWorldButton.setHorizontalAlignment(JButton.CENTER);
            loadWorldButton.setVerticalAlignment(JButton.CENTER);

            loadWorldButton.addActionListener(StartGamePanel.this);
            loadWorldButton.setBackground(Color.white);

            //create back button
            backButton.setHorizontalAlignment(JButton.CENTER);
            backButton.setVerticalAlignment(JButton.CENTER);
            backButton.addActionListener(StartGamePanel.this);
            backButton.setBackground(Color.white);
            add(newWorldButton);
            add(loadWorldButton);
            add(backButton);
        }
    }

}
