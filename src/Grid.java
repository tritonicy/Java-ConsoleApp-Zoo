import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

// 500x500 bir gridin uzerinde 250.000 tane tile var
// Bu tilelar araciligiyla butun karsilastirma ve atama islemlerini yapiyoruz.
public class Grid {
    public Map<Integer, Tile> tileMap;
    public List<Animal> animalList;
    public List<Animal> copyAnimalList;
    public int width;


    public Grid(int x, int y) {
        // Grid initalizasyonu
        tileMap = new HashMap<>();
        animalList = new ArrayList<>();
        this.width = y;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                tileMap.put((i * y) + j, new Tile(j, i, null));
            }
        }
    }

    // Istenilen tilei donduruyoruz.
    public Tile GetTile(int x, int y) {
        return tileMap.get(width * y + x);
    }

    // Gridin boundunu aliyoruz.
    public Vector2Int GetBound() {
        int x = tileMap.get(tileMap.size() - 1).getX();
        int y = tileMap.get(tileMap.size() - 1).getY();
        return new Vector2Int(x, y);
    }

    // verilen koordinattaki tile bos mu kontrol ediyor.
    public boolean isTileFree(int x, int y) {
        Tile tile = GetTile(x, y);
        return tile.getAnimal() == null;
    }

    // Ilgili tilea ilgili animali set ediyor.
    public void SetAnimal(Tile tile, Animal animal) {
        tile.setAnimal(animal);
    }

    // Gridin son halini prinliyor
    // Bu demo icin bu metodu kullanmadim fakat istenirse mainin icine yazilip gridi gorsellestirebiliriz.
    public void PrintGrid() {
        for (int key : tileMap.keySet()) {
            Tile value = tileMap.get(key);
            if (value.getAnimal() == null) {
                System.out.print("[]");
            } else {
                System.out.print(value.getAnimal().className);
            }
            if (value.getX() == GetBound().getX()) {
                System.out.println();
            }

        }
        System.out.println();

    }

    // Verilen koordinat gridin icinde mi onu kontrol ediyoruz
    public boolean ValidBounds(int x, int y) {
        Vector2Int vector2Int = GetBound();
        if (x >= 0 && x <= vector2Int.getX() && y >= 0 && y <= vector2Int.getY()) {
            return true;
        }
        return false;
    }

    // Iligi tilei yeni hayvanlarin uzerine gelebilmesi icin resetliyoruz.
    public void ResetTile(Tile tile) {
        tile.setAnimal(null);
    }

    // Tilemapimizdaki butun hayvanlari kontrol edip yazdiriyoruz.
    // soruda erkek disi olarak yazdirmam istenmedigi icin o ayrimi kontrol etmedim istenirse basit bir sekilde eklenebilir.
    public void PrintAnimalCount() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int key : tileMap.keySet()) {
            Tile tile = tileMap.get(key);
            if (tile.getAnimal() != null) {
                String animal = tile.getAnimal().className;
                if (dictionary.containsKey(animal)) {
                    dictionary.put(animal, dictionary.get(animal) + 1);
                } else {
                    dictionary.put(animal, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            System.out.println("Animal: " + entry.getKey() + ", Count: " + entry.getValue());
        }

    }

    // Verilen bir nokta ve yaricap icerisinde olan hayvanlari donduruyoruz.
    // (Avlanmaktan uremeye cesitli kullanim alanlari var).
    public List<Animal> CheckForAnimalsInRange(int startX, int startY, int range) {
        List<Animal> animalsInRange = new ArrayList<>();

        for (int i = startY - range; i < startY + range; i++) {
            for (int j = startX - range; j < startX + range; j++) {
                if (!ValidBounds(i, j)) {
                    continue;
                }
                Tile tile = GetTile(i, j);
                if (tile.getAnimal() != null) {
                    animalsInRange.add(tile.getAnimal());
                }
            }
        }
        return animalsInRange;
    }


    // Vector2Int inner Classi
    // x ve y koordinatlarini her seferinde ayri parametreler olarak girmek yerine beraber kullanmamiza olanak sagliyor

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
