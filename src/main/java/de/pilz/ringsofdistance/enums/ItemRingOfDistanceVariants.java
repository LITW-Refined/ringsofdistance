package de.pilz.ringsofdistance.enums;

public enum ItemRingOfDistanceVariants {
    
    LEVEL_1(0, 1.0D, "level1"),
    LEVEL_2(1, 3.0D, "level2"),
    LEVEL_3(2, 5.0D, "level3"),
    ;

    private final int meta;
    private final double modification;
    private final String name;
    
    private ItemRingOfDistanceVariants(int meta, double modification, String name) {
        this.meta = meta;
        this.modification = modification;
        this.name = name;
    }

    public int getMeta() {
        return meta;
    }

    public double getModification() {
        return modification;
    }

    public String getName() {
        return name;
    }
}
