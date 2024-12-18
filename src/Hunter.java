import java.util.List;
import java.util.ArrayList;

// Avci altsinifi
public class Hunter extends Animal {

    // Bazi ozellikler ture ozel oldugu icin construct islemine burada devam ediyoruz.
    public Hunter(String sex, int range, List<String> canHunt, Grid grid, int killRange) {
        super(sex, range, canHunt, grid, killRange);
        this.className = Hunter.class.getName();
        SetAnimalRandomCoordinate(grid, this);
    }

    // Her hayvan move edebilir fakat hangi hayvanin move ettigini bilmemiz icin bir override yapmamiz gerekiyor.
    @Override
    public void Move(int range, Grid grid) {
        super.Move(range, grid);
        // bu move kodunun devami kod biraz bozuk oldu.
        grid.SetAnimal(currentTile, this);
    }


    // Avci ciftlesemez bu sebeple breed kismi bos
    @Override
    public void Breed(Grid grid) {
        return;
    }


}
