import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public abstract class Animal {

    protected String sex;
    protected int range;
    protected List<String> canHunt;
    protected String className;
    protected Tile currentTile;


    public Animal(String sex, int range, List<String> animal, Grid grid) {
        this.sex = sex;
        this.range = range;
        this.canHunt = animal;
    }

    protected void SetAnimalRandomCoordinate(Grid grid, Animal animal){
        Random rand = new Random();
        int boundX = grid.GetBound().getX();
        int boundY = grid.GetBound().getY();

        int randomX = rand.nextInt(boundX);
        int randomY = rand.nextInt(boundY);
        if(grid.isTileFree(randomX,randomY)) {
            Tile tile = grid.GetTile(randomX, randomY);
            grid.SetAnimal(tile,animal);
            this.currentTile = tile;
        }
    }

    public void Move(int range, Grid grid) {
        boolean canMove = false;
        while (!canMove) {
            Random rand = new Random();
            int moveX = rand.nextInt(range + 1);
            int moveY = range - moveX;

            int newX = currentTile.getX() + moveX;
            int newY = currentTile.getY() + moveY;

            if (!grid.ValidBounds(newX, newY)) {
                continue;
            }
            if (!grid.isTileFree(newX, newY)) {
                continue;
            }
            canMove = true;

            // Tilei resetliyoruz
            grid.ResetTile(currentTile);

            // Yeni tilea yerlesme olayi subclassin isi
            currentTile = grid.GetTile(newX,newY);

        }


    };
    public  void Hunt(String[] canHunt, Grid grid) {

    };
    public  void Breed(Grid grid) {

    };

}
