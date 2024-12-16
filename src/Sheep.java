import java.util.List;
import java.util.ArrayList;

public class Sheep extends Animal{

    public Sheep(String sex, int range, List<String> canHunt, Grid grid) {
        super(sex,range,canHunt,grid);
        this.className =  Sheep.class.getName();
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
