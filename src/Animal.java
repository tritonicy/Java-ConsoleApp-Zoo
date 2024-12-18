import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// Animal abstract classi sayesinde diger hayvan turlerini turetebiliyoruz.
// Her hayvanin sahip oldugu degiskenler ve metodlar bu classda tutuluyor.
public abstract class Animal {

    protected String sex;
    protected int range;
    protected int killRange;
    protected List<String> canHunt;
    protected String className;
    protected Tile currentTile;


    // Constructor
    public Animal(String sex, int range, List<String> animal, Grid grid, int killRange) {
        this.sex = sex;
        this.range = range;
        this.canHunt = animal;
        this.killRange = killRange;
    }

    // Hayvanlari rastgele ve valid bir sekilde bir tilea kaydediyoruz.
    protected void SetAnimalRandomCoordinate(Grid grid, Animal animal) {
        Random rand = new Random();
        int boundX = grid.GetBound().getX();
        int boundY = grid.GetBound().getY();


        boolean isFree = false;
        while (!isFree) {
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

    // Her hayvan move yapabiliyor bu sebeple kodun degismeyen ana parcasi bu classda tanimli
    public void Move(int range, Grid grid) {
        boolean canMove = false;
        Random rand = new Random();
        int newX = -1;
        int newY = -1;
        while (!canMove) {
            int moveX = rand.nextInt((range * 2) + 1) - range;
            int absMoveX = Math.abs(moveX);
            int moveY = (rand.nextInt(2) == 1) ? (range - absMoveX) : -(range - absMoveX);

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

        // Yeni tilea yerlesme olayi subclassin isi
        currentTile = grid.GetTile(newX, newY);


        if (currentTile == null) {
            throw new IllegalStateException("Tile cannot be null after move!");
        }
    }

    // Eger hayvanin kill rangesi 0 degilse avlanabiliyordur.
    // Bu sebeple avlanma kodu bu classda tanimli
    public void Hunt(List<String> canHunt, Grid grid) {
        List<Animal> animalsInRange = grid.CheckForAnimalsInRange(currentTile.getX(), currentTile.getY(), killRange);
        for (Animal animal : animalsInRange) {
            // aradigimiz hayvan coktan baska hayvanlar tarafindan oldurulmus olabilir
            if (animal.currentTile == null) {
                continue;
            }
            if (CanHuntSpecies(animal.className) && CanReach(animal.currentTile.getX(), animal.currentTile.getY(), killRange)) {
                animal.Die(grid, animal);
                // Bu metodda hayvan rangesine giren oldurebildigi her hayvani olduruyor
                // Eger sadece bir hayvani oldurmesini isterseniz assagidaki yorum satirini kaldirabilirsiniz.

//                break;
            }
        }

    }

    // Her hayvan birbirinden farkli sekillerde uruyor bu sebeple bu metodun icini her hayvan icin dolduracagiz.
    public abstract void Breed(Grid grid);

    // Avci hayvanin ulasabilecegi yaricap belli fakat her hayvan belli bir sayida move yapabiliyor
    // (ornek: Koyun 2 birim)
    // Bu sebeple avci hayvan bu yaricapin icindeki her avina yetisemeyebilir, bunu kontrol ediyoruz.
    public boolean CanReach(int targetX, int targetY, int range) {
        int difX = Math.abs(targetX - currentTile.getX());
        int difY = Math.abs(targetY - currentTile.getY());

        return difX + difY <= range;
    }

    // Avci hayvan hangi hayvanlari avlayabilir onu kontrol ediyoruz.
    public boolean CanHuntSpecies(String animalType) {
        return canHunt.contains(animalType);
    }

    // Hayvan oldugunde bulundugu tilei bosaltiyor.
    public void Die(Grid grid, Animal animal) {
        grid.copyAnimalList.remove(animal);
        grid.ResetTile(animal.currentTile);
        animal.currentTile = null;
    }

}
