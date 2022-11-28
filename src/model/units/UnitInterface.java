package model.units;

import java.util.List;

public interface UnitInterface {
    String getInfo();
    void step(List<Unit> opponents);
}
