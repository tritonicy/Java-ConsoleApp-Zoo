import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;

public class Grid {
    public List<Tile> tileMap;


    public Grid(int x, int y) {
        tileMap = new ArrayList<>();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                tileMap.add(new Tile(j,i,null));
            }
        }
    }

    public Tile GetTile(int x, int y) {
    for (Tile tile : tileMap) {
        if(tile.getX() == x && tile.getY() == y) {
            return tile;
        }
    }
    return null;
    }

    public Vector2Int GetBound() {
        int x = tileMap.get(tileMap.size()-1).getX();
        int y = tileMap.get(tileMap.size()-1).getY();
        return new Vector2Int(x,y);
    }


    public boolean isTileFree(int x, int y) {
        Tile tile = GetTile(x,y);
        return tile.getAnimal() == null;
    }

    public void SetAnimal(Tile tile, Animal animal) {
        tile.setAnimal(animal);
    }

    public void PrintGrid() {
    for(Tile tile: tileMap) {
        if(tile.getAnimal() == null) {
            System.out.print("|");
        }
        else {
            System.out.print(tile.getAnimal().className);
        }
        if(tile.getX() == GetBound().getX()) {
            System.out.println();
        }
    }
    }

    public boolean ValidBounds(int x, int y) {
        Vector2Int vector2Int = GetBound();
        if(x >= 0 && x <= vector2Int.getX() && y >= 0 && y <= vector2Int.getY()) {
            return true;
        }
        return false;
    }

    public void ResetTile(Tile tile) {
        tile.setAnimal(null);
    }

    public void SetTile(Tile tile, Animal animal) {
        tile.setAnimal(animal);
    }






    // Vector2Int inner Classi

    public class Vector2Int {
        private int x;
        private int y;

        // Constructor
        public Vector2Int(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Getter ve Setter metodları
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
