package data;

import java.io.Serializable;

import entity.World;

public class DataStorage implements Serializable {
    private World world;

    public DataStorage() {

    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
    }
}