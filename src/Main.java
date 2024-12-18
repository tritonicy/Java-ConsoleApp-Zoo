import java.util.List;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static String sheep = Sheep.class.getName();
    public static String cow = Cow.class.getName();
    public static String chicken = Chicken.class.getName();
    public static String wolf = Wolf.class.getName();
    public static String lion = Lion.class.getName();
    public static String hunter = Hunter.class.getName();


    public static void main(String[] args) {
        Grid grid = new Grid(500,500);

        // Disi koyun
        for (int i = 0; i < 15; i++) {
            List<String> canHunt = new ArrayList<String>();
            Sheep sheep = new Sheep("Disi",2, canHunt,grid,0);
            grid.animalList.add(sheep);
        }
        // Erkek koyun
        for (int i = 0; i < 15; i++) {
            List<String> canHunt = new ArrayList<String>();
            Sheep sheep = new Sheep("Erkek",2, canHunt,grid,0);
            grid.animalList.add(sheep);

        }
        // Erkek Inek
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            Cow cow = new Cow("Erkek",2, canHunt,grid,0);
            grid.animalList.add(cow);

        }
        // Disi Inek
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            Cow cow = new Cow("Disi",2, canHunt,grid,0);
            grid.animalList.add(cow);

        }
        // Tavuk (disi)
        for (int i = 0; i < 10; i++) {
            List<String> canHunt = new ArrayList<String>();
            Chicken chicken = new Chicken("Disi",1, canHunt,grid,0);
            grid.animalList.add(chicken);

        }
        // Horoz (erkek tavuk)
        for (int i = 0; i < 10; i++) {
            List<String> canHunt = new ArrayList<String>();
            Chicken cockerel = new Chicken("Erkek",1, canHunt,grid,0);
            grid.animalList.add(cockerel);

        }
        // Disi kurt
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(chicken);
            Wolf wolf = new Wolf("Disi",3, canHunt,grid,4);
            grid.animalList.add(wolf);

        }
        // Erkek kurt
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(chicken);
            Wolf wolf = new Wolf("Erkek",3, canHunt,grid,4);
            grid.animalList.add(wolf);

        }
        // Disi Aslan
        for (int i = 0; i < 4; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(cow);
            canHunt.add(sheep);
            Lion lion = new Lion("Disi",4, canHunt,grid,5);
            grid.animalList.add(lion);

        }
        // Erkek Aslan
        for (int i = 0; i < 4; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(cow);
            canHunt.add(sheep);
            Lion lion = new Lion("Erkek",4, canHunt,grid,5);
            grid.animalList.add(lion);

        }
        // Avci
        for (int i = 0; i < 1; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(cow);
            canHunt.add(chicken);
            canHunt.add(wolf);
            canHunt.add(lion);
            Hunter hunter = new Hunter("Erkek",1, canHunt,grid,8);
            grid.animalList.add(hunter);
//            System.out.println(hunter.currentTile.getX());
//            System.out.println(hunter.currentTile.getY());

        }

        grid.PrintGrid();
//        grid.PrintAnimalCount();

        for (int i = 0; i <1000; i++) {
            grid.copyAnimalList = new ArrayList<>(grid.animalList);
            System.out.println();
            System.out.println();


//             Move 1 turn for all animals
//            System.out.println(grid.animalList.size());
            for (Animal animal: grid.animalList) {
                animal.Move(animal.range,grid);
            }
            for (Animal animal: grid.animalList) {
                // Eger listedeki daha onceki hayvanlar tarafindan oldurulduyse avlanamaz
                if(animal.currentTile == null) {
                    continue;
                }
                animal.Hunt(animal.canHunt,grid);
            }
            for (Animal animal: grid.animalList) {
                // Eger hayvan ayni tur icinde olduyse
                if(animal.currentTile == null) {
                    continue;
                }
                animal.Breed(grid);
            }

            grid.animalList = new ArrayList<>(grid.copyAnimalList);
//                grid.PrintGrid();
                System.out.println();
                System.out.println();

            }

                grid.PrintAnimalCount();
    }
}