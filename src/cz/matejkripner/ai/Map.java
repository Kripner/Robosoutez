package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Point;

import java.util.Arrays;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
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
        map[position.getY()][position.getX()] = value;
    }

    public Chunk getChunk(Point position) {
        return map[position.getY()][position.getX()];
    }

    public Chunk[][] getMap() {
        return map;
    }

    public static Map getUniversal() {
        Map map = new Map();
        for (int i = 0; i < map.getMap().length; i++) {
            Arrays.fill(map.getMap()[i], Chunk.UNKNOWN);
        }
        return map;
    }


    public enum Chunk {
        UNKNOWN,
        WALL,
        SPACE_DONE,
        SPACE_NOT_DONE
    }
}
