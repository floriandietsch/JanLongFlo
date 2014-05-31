package gildedrose;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GildedRoseTest {

	@Parameters
	public static Collection<	Object[]> data() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.addAll(Arrays
				.asList(new Object[][] { 
						{
						"At the end of each day our system lowers both quality and sell-in for every item",
						"Item with arbitrary name", 5, 49, 4, 48 
						},
						{
						"SellIn is negative -> Quality degrades twice as fast", "Rusty Shortsword", -1,35,-2,33	
						},
						{
						"Quality is never negative | Starts at 0","Elixir of the Mongoose",2,0,1,0	
						},
						{
						"Quality is never negative | Starts from negative","Elixir of the Mongoose",2,-1,1,0	
						},	
						{
						"Sulfuras Test","Sulfuras, Hand of Ragnaros", 0,80,0,80	
						},
						{
						"Aged Brie increases in Quality","Aged Brie",2,0,1,1	
						},
						{
						"Quality of item is never over 50 | Starts at over 50","Magisters Staff",5,560,4,50
						},
						{
						"Quality of item is never over 50 | Aged Brie with Quality 49","Aged Brie",5,50,4,50
						},
						{
						"Backstage: Quality+1 when SellIn > 10","Backstage passes to a TAFKAL80ETC concert",15,1,14,2
						},
						{
						"Backstage: Quality +2 when SellIn < 10","Backstage passes to a TAFKAL80ETC concert",10,1,9,3
						},
						{
						"Backstage: Quality +3 when SellIn < 5","Backstage passes to a TAFKAL80ETC concert",5,1,4,4
						},
						{
						"Backstage: Quality drops to 0 when sellIn = 0","Backstage passes to a TAFKAL80ETC concert",0,20,-1,0
						},
						
				}));
		return data;
	}

	String message;
	String itemName;
	int sellIn;
	int quality;
	int expectedSellIn;
	int expectedQuality;

	Item item;

	public GildedRoseTest(String message, String itemName, int sellIn,
			int quality, int expectedSellIn, int expectedQuality) {
		this.message = message;
		this.itemName = itemName;
		this.sellIn = sellIn;
		this.quality = quality;
		this.expectedSellIn = expectedSellIn;
		this.expectedQuality = expectedQuality;
	}

	@Before
	public void setUp() {
		List<Item> items = new ArrayList<Item>();
		items.add(item = new Item(itemName, sellIn, quality));
		GildedRose.setItems(items);
	}

	@Test
	public void testQualityUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " Quality ", expectedQuality, item.getQuality());
	}

	@Test
	public void testSellInUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " SellIn", expectedSellIn, item.getSellIn());
	}
	
}
