import java.util.List;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static String sheep = Sheep.class.getName();
    public static String cow = Cow.class.getName();
    public static String chicken = Chicken.class.getName();
    public static String wolf = Wolf.class.getName();
    public static String cockerel = Cockerel.class.getName();
    public static String lion = Lion.class.getName();
    public static String hunter = Hunter.class.getName();

    public static void main(String[] args) {
        Grid grid = new Grid(500,500);

        // Disi koyun
        for (int i = 0; i < 15; i++) {
            List<String> canHunt = new ArrayList<String>();
            Sheep sheep = new Sheep("Disi",2, canHunt,grid);
        }
        // Erkek koyun
        for (int i = 0; i < 15; i++) {
            List<String> canHunt = new ArrayList<String>();
            Sheep sheep = new Sheep("Erkek",2, canHunt,grid);
        }
        // Erkek Inek
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            Cow cow = new Cow("Erkek",2, canHunt,grid);
        }
        // Disi Inek
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            Cow cow = new Cow("Disi",2, canHunt,grid);
        }
        // Tavuk
        for (int i = 0; i < 10; i++) {
            List<String> canHunt = new ArrayList<String>();
            Chicken chicken = new Chicken("Disi",1, canHunt,grid);
        }
        // Horoz
        for (int i = 0; i < 10; i++) {
            List<String> canHunt = new ArrayList<String>();
            Cockerel cockerel = new Cockerel("Erkek",1, canHunt,grid);
        }
        // Disi kurt
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(chicken);
            canHunt.add(cockerel);
            Wolf wolf = new Wolf("Disi",3, canHunt,grid);
        }
        // Erkek kurt
        for (int i = 0; i < 5; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(chicken);
            canHunt.add(cockerel);
            Wolf wolf = new Wolf("Erkek",3, canHunt,grid);
        }
        // Disi Aslan
        for (int i = 0; i < 4; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(cow);
            canHunt.add(sheep);
            Lion lion = new Lion("Disi",4, canHunt,grid);
        }
        // Erkek Aslan
        for (int i = 0; i < 4; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(cow);
            canHunt.add(sheep);
            Lion lion = new Lion("Erkek",4, canHunt,grid);
        }
        // Avci
        for (int i = 0; i < 1; i++) {
            List<String> canHunt = new ArrayList<String>();
            canHunt.add(sheep);
            canHunt.add(cow);
            canHunt.add(chicken);
            canHunt.add(wolf);
            canHunt.add(cockerel);
            canHunt.add(lion);
            Hunter hunter = new Hunter("Erkek",1, canHunt,grid);
        }

        grid.PrintGrid();


    }
}