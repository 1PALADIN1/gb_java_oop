import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Unit> units = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            units.add(getUnit());
        }

        units.forEach(System.out::println);
        System.out.println("====================================");
        filterUnitsByType(units, Wizard.class);
    }

    private static Unit getUnit() {
        int num = new Random().nextInt(7);
        return switch (num) {
            case 0 -> new Peasant();
            case 1 -> new Monk();
            case 2 -> new Robber();
            case 3 -> new Sniper();
            case 4 -> new Spearman();
            case 5 -> new Wizard();
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