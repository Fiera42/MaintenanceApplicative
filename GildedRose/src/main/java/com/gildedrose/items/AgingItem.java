package com.gildedrose.items;

public class AgingItem extends QualityRestrictedItem {
    public AgingItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        sellIn--;
        quality++;
        super.update();
    }
}
