package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for(Item item : items) {
            switch(item.name) {
                case AGED_BRIE:
                    agedBrie(item);
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    // Sulfuras is legendary : we never change it
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                    backstagePasses(item);
                    break;
                default:
                    genericItem(item);
            }

            if(item.quality > 50) item.quality = 50;
            if(item.quality < 0) item.quality = 0;
        }
    }

    private void genericItem(Item item) {
        item.quality--;
        if(item.sellIn < 0) item.quality--;
        item.sellIn--;
    }

    private void agedBrie(Item item) {
        item.quality++;
        item.sellIn--;
    }

    private void backstagePasses(Item item) {
        item.quality++;
        if(item.sellIn <= 10) item.quality++;
        if(item.sellIn <= 5) item.quality++;
        if(item.sellIn <= 0) item.quality = 0;
        item.sellIn--;
    }
}
