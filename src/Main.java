import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int GANG_SIZE = 10;

    public static void main(String[] args) {
        List<Unit> whiteSide = new ArrayList<>();
        List<Unit> darkSide = new ArrayList<>();

        for (int i = 0; i < GANG_SIZE; i++) {
            whiteSide.add(getUnit(0, whiteSide));
            darkSide.add(getUnit(3, darkSide));
        }

        System.out.println("WHITE SIDE:");
        whiteSide.forEach(unit -> System.out.println(unit.getInfo()));
        System.out.println("DARK SIDE:");
        darkSide.forEach(unit -> System.out.println(unit.getInfo()));

        System.out.println("============================");
        whiteSide.forEach(Unit::step);
        darkSide.forEach(Unit::step);

        System.out.println("WHITE SIDE:");
        whiteSide.forEach(unit -> System.out.println(unit.getInfo()));
        System.out.println("DARK SIDE:");
        darkSide.forEach(unit -> System.out.println(unit.getInfo()));
    }

    private static Unit getUnit(int origin, List<Unit> side) {
        int num = new Random().nextInt(origin, origin + 4);
        return switch (num) {
            case 0 -> new Monk();
            case 1 -> new Robber();
            case 2 -> new Sniper();
            case 3 -> new Peasant();
            case 4 -> new Spearman();
            case 5 -> new Wizard(side);
            default -> new Xbowman();
        };
    }

    private static void filterUnitsByType(List<Unit> units, Class type) {
        for (Unit unit : units) {
            if (unit.getClass() == type) {
                System.out.println(unit);
            }
        }
    }
}