import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Inek altsinifi
public class Cow extends Animal {

    // Bazi ozellikler ture ozel oldugu icin construct islemine burada devam ediyoruz.
    public Cow(String sex, int range, List<String> canHunt, Grid grid, int killRange) {
        super(sex, range, canHunt, grid, killRange);
        this.className = Cow.class.getName();
        SetAnimalRandomCoordinate(grid, this);

    }

    @Override
    // Her hayvan move edebilir fakat hangi hayvanin move ettigini bilmemiz icin bir override yapmamiz gerekiyor.
    public void Move(int range, Grid grid) {
        super.Move(range, grid);
        // bu move kodunun devami kod biraz bozuk oldu.
        grid.SetAnimal(currentTile, this);
    }


    @Override
    // Hayvanin ureme metodu
    public void Breed(Grid grid) {
        List<Animal> animalList = grid.CheckForAnimalsInRange(currentTile.getX(), currentTile.getY(), 3);

        for (Animal animal : animalList) {
            if (animal.className.equals(this.className) && !animal.sex.equals(this.sex)) {
                Random rand = new Random();
                String sex = rand.nextInt(2) == 1 ? "Erkek" : "Disi";
                Cow cow = new Cow(sex, 2, canHunt, grid, 0);
                SetAnimalRandomCoordinate(grid, cow);
                grid.copyAnimalList.add(cow);
            }
        }
    }


}
