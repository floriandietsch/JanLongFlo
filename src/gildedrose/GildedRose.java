package gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQuality();
	}

	public static void updateQuality() {
		for (int i = 0; i < items.size(); i++) {
			
			String name = items.get(i).getName();
			boolean legend = false;
			int itemQuality = items.get(i).getQuality();
			
			if(itemQuality == 80){legend = true;} 
			if(name.equals("Aged Brie")){
				items.get(i).setQuality(itemQuality+1);
			}
			else if (legend){
				//nothing happens :^)
			}
			else if (name.contains("Conjured")){
				if(items.get(i).getSellIn() >= 0){
					items.get(i).setQuality(itemQuality-2);
				}
				else{
					items.get(i).setQuality(itemQuality-4);
				}
			}
			else if (name.contains("Backstage pass")){
				int itemSellIn = items.get(i).getSellIn();
				if(itemSellIn > 10){
					items.get(i).setQuality(itemQuality+1);
				}
				else if(itemSellIn > 5){
					items.get(i).setQuality(itemQuality+2);
				}
				else if(itemSellIn > 0){
					items.get(i).setQuality(itemQuality+3);
				}
				else{items.get(i).setQuality(0);}
			}
			else { //default case
				if(items.get(i).getSellIn() >= 0){
					items.get(i).setQuality(itemQuality-1);
				}
				else{
					items.get(i).setQuality(itemQuality-2);
				}
			}
			if(!legend){items.get(i).setSellIn(items.get(i).getSellIn()-1);}
			if(items.get(i).getQuality() < 0) items.get(i).setQuality(0); //Long method again call sice item quality is different from itemQuality
			if(items.get(i).getQuality() > 50 && !legend) items.get(i).setQuality(50);
		}

	}

	/*
	 * minimal addition to ensure testability
	 */
	public static void setItems(List<Item> items) {
		GildedRose.items = items;
	}
}