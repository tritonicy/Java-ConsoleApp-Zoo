public class Tile {
    private int x;
    private int y;
    private Animal animal;

    public Tile(int x, int y, Animal animal) {
        this.x = x;
        this.y = y;
        this.animal = animal;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public Animal getAnimal() {
        return animal;
    }
}
