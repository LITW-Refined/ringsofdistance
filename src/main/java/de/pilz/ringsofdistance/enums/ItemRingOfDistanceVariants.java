package de.pilz.ringsofdistance.enums;

public enum ItemRingOfDistanceVariants {

    LEVEL_1(0, 1, 1.0D),
    LEVEL_2(1, 2, 3.0D),
    LEVEL_3(2, 3, 5.0D),;

    private final int meta;
    private final int level;
    private final double modification;

    private ItemRingOfDistanceVariants(int meta, int level, double modification) {
        this.meta = meta;
        this.level = level;
        this.modification = modification;
    }

    public int getMeta() {
        return meta;
    }

    public int getLevel() {
        return level;
    }

    public double getModification() {
        return modification;
    }

    public String getName() {
        return "level" + level;
    }
}
