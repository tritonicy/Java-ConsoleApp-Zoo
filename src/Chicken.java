import java.util.List;
import java.util.ArrayList;

public class Chicken extends Animal{

    public Chicken(String sex, int range,List<String> canHunt,Grid grid) {
        super(sex,range,canHunt,grid);
        this.className =  Chicken.class.getName();
        SetAnimalRandomCoordinate(grid, this);


    }

    @Override
    public void Move(int range,Grid grid) {
        super.Move(range,grid);
        // bu move kodunun devami kod biraz bozuk oldu.
        grid.SetAnimal(currentTile, this);
    }

    @Override
    public void Hunt(String[] canHunt,Grid grid) {

    }

    @Override
    public void Breed(Grid grid) {

    }
}
