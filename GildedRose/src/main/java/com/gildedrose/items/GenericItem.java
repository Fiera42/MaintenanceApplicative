package com.gildedrose.items;

public class GenericItem extends QualityRestrictedItem {
    public GenericItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        quality--;
        if(sellIn < 0){
            quality--;
        }
        sellIn--;
        super.update();
    }
}
