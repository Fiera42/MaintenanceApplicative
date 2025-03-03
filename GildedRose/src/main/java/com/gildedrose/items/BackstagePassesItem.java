package com.gildedrose.items;

public class BackstagePassesItem extends AgingItem {

    public BackstagePassesItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        if(sellIn <= 10) quality++;
        if(sellIn <= 5) quality++;
        super.update();
        // Aging and restrictions should not override the quality set
        // We add one because quality is decreased in super
        if(sellIn+1 <= 0) quality = 0;
    }
}
