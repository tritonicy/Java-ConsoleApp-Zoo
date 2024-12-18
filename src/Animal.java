import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public abstract class Animal {

    protected String sex;
    protected int range;
    protected int killRange;
    protected List<String> canHunt;
    protected String className;
    protected Tile currentTile;


    public Animal(String sex, int range, List<String> animal, Grid grid, int killRange) {
        this.sex = sex;
        this.range = range;
        this.canHunt = animal;
        this.killRange = killRange;
    }

    protected void SetAnimalRandomCoordinate(Grid grid, Animal animal){
        Random rand = new Random();
        int boundX = grid.GetBound().getX();
        int boundY = grid.GetBound().getY();


        boolean isFree = false;
        while(!isFree) {
        int randomY = rand.nextInt(boundY);
        int randomX = rand.nextInt(boundX);
            if (grid.isTileFree(randomX, randomY)) {
                Tile tile = grid.GetTile(randomX, randomY);
                grid.SetAnimal(tile, animal);
                this.currentTile = tile;
                isFree = true;
//            System.out.println(tile.getX() + "  " + tile.getY());
            }
        }
    }

    public void Move(int range, Grid grid) {
        boolean canMove = false;
        Random rand = new Random();
        int newX = -1;
        int newY = -1;
        while (!canMove) {
            int moveX = rand.nextInt((range * 2) + 1) - range;
            int absMoveX = Math.abs(moveX);
            int moveY = (rand.nextInt(2) == 1) ? (range - absMoveX) : -(range - absMoveX);

//            System.out.println(currentTile.getAnimal());
//            System.out.println(currentTile.getX());
//            System.out.println(currentTile.getY());
//            System.out.println(moveX);
//            System.out.println(moveY);

            newX = currentTile.getX() + moveX;
            newY = currentTile.getY() + moveY;

            if (!grid.ValidBounds(newX, newY)) {
                continue;
            }
            if (!grid.isTileFree(newX, newY)) {
                continue;
            }
            canMove = true;
        }

            // Tilei resetliyoruz
            grid.ResetTile(currentTile);
//            System.out.println("old " + currentTile.getX() + " " + currentTile.getY());

            // Yeni tilea yerlesme olayi subclassin isi
            currentTile = grid.GetTile(newX,newY);
//            System.out.println("new " +currentTile.getX() + " " + currentTile.getY());


        if (currentTile == null) {
            throw new IllegalStateException("Tile cannot be null after move!");
        }


    };
    public  void Hunt(List<String> canHunt, Grid grid) {
        List<Animal> animalsInRange = grid.CheckForAnimalsInRange(currentTile.getX(), currentTile.getY(), killRange);
        for(Animal animal : animalsInRange) {
            // aradigimiz hayvan coktan baska hayvanlar tarafindan oldurulmus olabilir
            if(animal.currentTile == null) {
                continue;
            }
            if(CanHuntSpecies(animal.className) && CanReach(animal.currentTile.getX(),animal.currentTile.getY(),killRange)) {
//                System.out.println("Animal Killed at " + animal.currentTile.getX() + " " +animal.currentTile.getY());
                animal.Die(grid, animal);
            }
        }

    };
    public abstract void Breed(Grid grid);


    public boolean CanReach(int targetX, int targetY, int range) {
        int difX = Math.abs(targetX - currentTile.getX());
        int difY = Math.abs(targetY - currentTile.getY());

        return difX + difY <= range;
    }

    public boolean CanHuntSpecies(String animalType) {
        return canHunt.contains(animalType);
    }

    public void Die(Grid grid, Animal animal) {
        grid.copyAnimalList.remove(animal);
        grid.ResetTile(animal.currentTile);
        animal.currentTile = null;
    }

}
