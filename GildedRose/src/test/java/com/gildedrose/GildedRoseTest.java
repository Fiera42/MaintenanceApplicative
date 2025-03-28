package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing GildedRoseClass for...")
class GildedRoseTest {

    // ------------------------------------------------ General
    @Test
    @DisplayName("SellIn decay")
    public void testSellInDecay() {
        UpdatableItem[] items = new UpdatableItem[] { ItemFactory.createItem("Fromage", 10, 9), ItemFactory.createItem("Patates", 1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn, "sellIn did not decayed by 1");
        assertEquals(0, app.items[1].sellIn, "sellIn did not decayed by 1");
    }

    @Test
    @DisplayName("SellIn can be negative")
    public void testSellInCanBeNegative() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem("Patates", 0, 3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn, "sellIn did not decayed by 1");
    }

    @Test
    @DisplayName("Quality decay")
    public void testQualityDecay() {
        UpdatableItem[] items = new UpdatableItem[] { ItemFactory.createItem("Fromage", 10, 9), ItemFactory.createItem("Patates", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(8, app.items[0].quality, "quality did not decayed by 1");
        assertEquals(0, app.items[1].quality, "quality did not decayed by 1");
    }

    @Test
    @DisplayName("No negative quality")
    public void testNoNegativeQuality() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem("Idiot sandwich", 3, 0), ItemFactory.createItem("Oopsi", -1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "Quality should never go below 0");
        assertEquals(0, app.items[1].quality, "Quality should never go below 0");
    }

    @Test
    @DisplayName("No quality over 50")
    public void testQualityOver50() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem("Oopsi", 3, 55)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "Quality should not be over 50");
    }

    @Test
    @DisplayName("Quality decay twice as fast when sellIn < 0")
    public void testQualityDecayTwiceAsFast() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem("Oopsi", -1, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, app.items[0].quality, "Quality should decay twice as fast");
    }

    // ------------------------------------------------ Special Items
    @Test
    @DisplayName("Sulfuras quality never goes down")
    public void testSulfurasQualityNeverGoesDown() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, 3, 30), ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, 0, 30), ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, -1, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(30, app.items[0].quality);
        assertEquals(30, app.items[1].quality);
        assertEquals(30, app.items[2].quality);
    }

    @Test
    @DisplayName("Sulfuras sellIn never goes down")
    public void testSulfurasSellInNeverGoesDown() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, 3, 30), ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, 0, 30), ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, -1, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(0, app.items[1].sellIn);
        assertEquals(-1, app.items[2].sellIn);
    }

    @Test
    @DisplayName("Sulfuras cannot exceed 50 quality")
    public void testSulfurasQualityOver50() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem(ItemFactory.SULFURAS_HAND_OF_RAGNAROS, 3, 55)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "Quality should not be over 50");
    }

    @Test
    @DisplayName("Aged Brie & Backstage passes quality goes up over time")
    public void testAgedBrieAndBackstagePassesQualityGoesUpOverTime() {
        UpdatableItem[] items = new UpdatableItem[] {
                ItemFactory.createItem(ItemFactory.AGED_BRIE, 11, 3),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 3)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality, "Quality should go up by 1");
        assertEquals(4, app.items[1].quality, "Quality should go up by 1");
    }

    @Test
    @DisplayName("Aged Brie quality goes up steadily")
    public void testAgedBrieQualityGoesUpSteadily() {
        UpdatableItem[] items = new UpdatableItem[] {
                ItemFactory.createItem(ItemFactory.AGED_BRIE, 3, 3),
                ItemFactory.createItem(ItemFactory.AGED_BRIE, 1, 3),
                ItemFactory.createItem(ItemFactory.AGED_BRIE, 0, 3)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality, "Quality should go up");
        assertEquals(4, app.items[1].quality, "Quality should go up");
        assertEquals(4, app.items[2].quality, "Quality should go up");
    }

    @Test
    @DisplayName("Backstage passes quality grow by 2 when sellIn <= 10")
    public void testBackstagePassesQualityGrowBy2WhenSellInLessThan10() {
        UpdatableItem[] items = new UpdatableItem[] {
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 2),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 7, 2)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality, "Quality should go up by 1");
        assertEquals(4, app.items[1].quality, "Quality should go up by 2");
    }

    @Test
    @DisplayName("Backstage passes quality grow by 3 when sellIn <= 5")
    public void testBackstagePassesQualityGrowBy3WhenSellInLessThan5() {
        UpdatableItem[] items = new UpdatableItem[] {
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 2),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 1, 2)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality, "Quality should go up by 3");
        assertEquals(5, app.items[1].quality, "Quality should go up by 3");
    }

    @Test
    @DisplayName("Backstage passes quality should be 0 when sellIn <= 0")
    public void testBackstagePassesQualityShouldBe0WhenSellInLessThan0() {
        UpdatableItem[] items = new UpdatableItem[] {ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 0, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "Quality should be 0");
    }

    @Test
    @DisplayName("Aged Brie & Backstage passes quality should not exceed 50")
    public void testAgedBrieAndBackstagePassesQualityNotExceed50() {
        UpdatableItem[] items = new UpdatableItem[] {
                ItemFactory.createItem(ItemFactory.AGED_BRIE, 11, 50),
                ItemFactory.createItem(ItemFactory.AGED_BRIE, -1, 50),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 50),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 49),
                ItemFactory.createItem(ItemFactory.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 48)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "Quality should not exceed 50");
        assertEquals(50, app.items[1].quality, "Quality should not exceed 50");
        assertEquals(50, app.items[2].quality, "Quality should not exceed 50");
        assertEquals(50, app.items[3].quality, "Quality should not exceed 50");
    }
}
