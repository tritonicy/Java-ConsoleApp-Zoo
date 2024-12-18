import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Aslan altsinifi
public class Lion extends Animal {

    // Bazi ozellikler ture ozel oldugu icin construct islemine burada devam ediyoruz.
    public Lion(String sex, int range, List<String> canHunt, Grid grid, int killRange) {
        super(sex, range, canHunt, grid, killRange);
        this.className = Lion.class.getName();
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
                Lion lion = new Lion(sex, 4, canHunt, grid, 5);
                SetAnimalRandomCoordinate(grid, lion);
                grid.copyAnimalList.add(lion);
            }
        }
    }


}
