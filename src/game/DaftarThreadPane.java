package game;

import entity.Sim;
import entity.World;
import thread.ThreadAksiPasif;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DaftarThreadPane extends JScrollPane {
    World world;
    Sim sim;

    DefaultListModel<ThreadAksiPasif> defaultListModel;
    JList<ThreadAksiPasif> listAksi;
    public DaftarThreadPane(World world, Sim sim) {
        this.world = world;
        this.sim = sim;
        defaultListModel = new DefaultListModel<>();
        listAksi = new JList<>(defaultListModel);
        listAksi.setBackground(new Color(105, 180, 110));
        setBackground(Color.pink);
        setOpaque(true);
        setViewportView(listAksi);
    }

    public void tambahThread(ThreadAksiPasif thread) {
        defaultListModel.addElement(thread);
    }

    public void hapusThread(ThreadAksiPasif thread) {
        defaultListModel.removeElement(thread);
    }

    public void update(){
        defaultListModel.clear();
        for (ThreadAksiPasif threadAksiPasif : world.getListThreadAksiPasif()){
           tambahThread(threadAksiPasif);
        }
        listAksi = new JList<>(defaultListModel);
        listAksi.setBackground(new Color(105, 180, 110));
        setViewportView(listAksi);
    }
}
