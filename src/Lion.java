import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class Lion extends Animal{

    public Lion(String sex, int range, List<String> canHunt,Grid grid, int killRange) {
        super(sex,range,canHunt,grid, killRange);
        this.className =  Lion.class.getName();
        SetAnimalRandomCoordinate(grid, this);


    }

    @Override
    public void Move(int range,Grid grid) {
        super.Move(range,grid);
        // bu move kodunun devami kod biraz bozuk oldu.
        grid.SetAnimal(currentTile, this);
    }


    @Override
    public void Breed(Grid grid) {
        List<Animal> animalList = grid.CheckForAnimalsInRange(currentTile.getX(), currentTile.getY(), 3);

        for (Animal animal: animalList) {
            if(animal.className.equals(this.className) && !animal.sex.equals(this.sex)) {
                Random rand = new Random();
                String sex = rand.nextInt(2) == 1 ? "Erkek" :"Disi";
                Lion lion = new Lion(sex,4, canHunt,grid,5);
                SetAnimalRandomCoordinate(grid,lion);
                grid.copyAnimalList.add(lion);
            }
        }
    }


}
