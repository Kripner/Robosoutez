package cz.matejkripner.simulation;

import cz.matejkripner.Main;
import cz.matejkripner.ai.Map;
import cz.matejkripner.core.Point;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class PixelMap { // Note that pixel there means 1 mm
    private Map map;

    private Pixel getPixel(int x, int y) {
        return Pixel.getPixel(map.getChunk(new Point(x / Main.ONE_TILL_DISTANCE, y / Main.ONE_TILL_DISTANCE)));
    }

    enum Pixel {
        WALL,
        SPACE;

        public static Pixel getPixel(Map.Chunk chunk) {
            if(chunk == Map.Chunk.UNKNOWN) throw new IllegalArgumentException("Unknown??");
            return (chunk == Map.Chunk.WALL) ? Pixel.WALL : Pixel.SPACE;
        }
    }
}
