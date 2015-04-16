package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Point;

import java.util.Arrays;

/**
 * @author Mat�j Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Map {

    private Chunk[][] map;

    public Map() {
        // initialize map
        map = new Chunk[Main.BOARD_HEIGHT][];
        Arrays.fill(map, new Chunk[Main.BOARD_WIDTH]);
    }

    public void setChunk(Point position, Chunk value) {

    }

    public Chunk getChunk(Point position) {

    }

    public Chunk[][] getMap() {
        return map;
    }

    public static Map getUniversal() {
        Map map = new Map();
        for (int i = 0; i < map.getMap().length; i++) {
            Arrays.fill(map.getMap()[i], Chunk.UNKNOWK);
        }
        return map;
    }


    public enum Chunk {
        UNKNOWK,
        WALL,
        SPACE_DONE,
        SPACE_NOT_DONE
    }
}