package game;

import entity.Sim;
import entity.World;
import thread.ThreadAksi;

import javax.swing.*;
import java.util.List;

public class DaftarThreadPane extends JScrollPane {
    World world;
    Sim sim;

    DefaultListModel<ThreadAksi> defaultListModel = new DefaultListModel<>();
    List<ThreadAksi> listAksi;
}
