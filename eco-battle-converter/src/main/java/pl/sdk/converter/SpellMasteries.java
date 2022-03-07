package pl.sdk.converter;

import java.util.List;

public class SpellMasteries {

   public enum SpellMasterLevel {
        BASIC(0), ADVANCED(1), MASTER(2);

        private final int value;

        SpellMasterLevel(int aValue) {
            value = aValue;
        }
    }

    private final SpellMasterLevel fire;
    private final SpellMasterLevel earth;
    private final SpellMasterLevel water;
    private final SpellMasterLevel air;
    
    public SpellMasteries() {
        air = SpellMasterLevel.BASIC;
        fire = SpellMasterLevel.BASIC;
        earth = SpellMasterLevel.BASIC;
        water = SpellMasterLevel.BASIC;
    }

    public SpellMasteries(SpellMasterLevel aAir, SpellMasterLevel aFire, SpellMasterLevel aEarth,
                          SpellMasterLevel aWater) {
        air = aAir;
        fire = aFire;
        earth = aEarth;
        water = aWater;
    }

    public SpellMasterLevel findMaxLevel() {
        return List.of(air, fire, earth).stream().max((l1, l2) -> l1.value - l2.value).get();
    }

    public SpellMasterLevel getAir() {
        return air;
    }

    public SpellMasterLevel getFire() {
        return fire;
    }

    public SpellMasterLevel getEarth() {
        return earth;
    }

    public SpellMasterLevel getWater() {
        return water;
    }
}
