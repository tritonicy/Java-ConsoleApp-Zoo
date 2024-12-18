import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Grid {
    public Map<Integer,Tile> tileMap;
    public List<Animal> animalList;
    public List<Animal> copyAnimalList;
    public int width;


    public Grid(int x, int y) {
        tileMap = new HashMap<>();
        animalList = new ArrayList<>();
        this.width = y;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                tileMap.put((i * y) + j, new Tile(j,i,null));
//                tileMap.add(new Tile(j,i,null));
            }
        }
    }

    public Tile GetTile(int x, int y) {
        return tileMap.get(width * y + x);
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
    for(int key: tileMap.keySet()) {
        Tile value = tileMap.get(key);
        if(value.getAnimal() == null) {
            System.out.print("|");
        }
        else {
            System.out.print(value.getAnimal().className);
        }
        if(value.getX() == GetBound().getX()) {
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


    public void PrintAnimalCount() {
        Map<String,Integer> dictionary = new HashMap<>();
        for(int key: tileMap.keySet()) {
            Tile tile = tileMap.get(key);
            if(tile.getAnimal() != null) {
                String animal = tile.getAnimal().className;
                if(dictionary.containsKey(animal)) {
                    dictionary.put(animal, dictionary.get(animal) +1);
                }
                else{
                    dictionary.put(animal,1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            System.out.println("Animal: " + entry.getKey() + ", Count: " + entry.getValue());
        }

    }

    public List<Animal> CheckForAnimalsInRange(int startX, int startY, int range) {
        List<Animal> animalsInRange = new ArrayList<>();

        for (int i = startY - range; i < startY + range; i++) {
            for (int j = startX - range; j < startX + range; j++) {
                if(!ValidBounds(i,j)) {
                    continue;
                }
                Tile tile = GetTile(i,j);
                if(tile.getAnimal() != null) {
//                    System.out.println("Animal eklendi" + range);
                    animalsInRange.add(tile.getAnimal());
                }
            }
        }
        return animalsInRange;
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
