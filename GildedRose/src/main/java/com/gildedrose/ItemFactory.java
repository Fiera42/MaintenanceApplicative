package com.gildedrose;

import com.gildedrose.items.*;

public class ItemFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    public static UpdatableItem createItem(String name, int sellIn, int quality) {
        return createItem(name, sellIn, quality, false);
    }

    public static UpdatableItem createItem(String name, int sellIn, int quality, boolean conjured) {
        UpdatableItem res;
        switch(name) {
            case AGED_BRIE:
                res = new AgingItem(name, sellIn, quality);
                break;
            case SULFURAS_HAND_OF_RAGNAROS:
                res = new LegendaryItem(name, sellIn, quality);
                break;
            case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                res = new BackstagePassesItem(name, sellIn, quality);
                break;
            default:
                res = new GenericItem(name, sellIn, quality);
        }

        if(conjured) {
            res = new ConjuredItem(res, name, sellIn, quality);
        }

        return res;
    }
}
