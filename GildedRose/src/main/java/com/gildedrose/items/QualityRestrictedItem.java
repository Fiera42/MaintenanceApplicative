package com.gildedrose.items;

import com.gildedrose.UpdatableItem;

public class QualityRestrictedItem extends UpdatableItem {
    public QualityRestrictedItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        if(quality > 50) quality = 50;
        if(quality < 0) quality = 0;
    }
}
