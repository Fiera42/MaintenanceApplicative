package com.gildedrose.items;

import com.gildedrose.UpdatableItem;

public class ConjuredItem extends UpdatableItem {

    private final UpdatableItem conjuredItem;

    public ConjuredItem(UpdatableItem conjuredItem, String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.conjuredItem = conjuredItem;
    }

    @Override
    public void update() {
        conjuredItem.update();
        conjuredItem.sellIn = sellIn; // Reset sellIn to avoid any weird behaviour on really special items
        conjuredItem.update();

        sellIn = conjuredItem.sellIn;
        quality = conjuredItem.quality;
    }
}

