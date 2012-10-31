package com.cs2340.spacetrader;

import java.util.Random;

//import android.util.Log;

public class Good {
	public String name; // name of good
	private int minProd; // min lvl to produce (buy)
	private int minUse; // min lvl to use (sell)
	private int idealLvl; // base lvl
	private int basePrice; // base price
	private int incPerLvl; // multiplier when off base lvl
	private int var; // random variance range
	private int price;

	public Good(String name, int minProd, int minUse, int idealLvl,
			int basePrice, int incPerLvl, int var) {

		this.name = name;
		this.minProd = minProd;
		this.minUse = minUse;
		this.idealLvl = idealLvl;
		this.basePrice = basePrice;
		this.incPerLvl = incPerLvl;
		this.var = var;
	}
	
	public void resetPrice(int planetTechLvl) {
		int price; // returned buy price, sell price = 10% lower
		Random rand = new Random();
		int variance = rand.nextInt(var * 2) - var;

		price = basePrice + Math.abs(planetTechLvl - minProd) * incPerLvl
				+ basePrice * (variance) / 100;

		// test code, prints to LogCat
		// Log.d("BasePrice", Integer.toString(basePrice));
		// Log.d("EventMod", Integer.toString(eventMod)); // eventMod feature
		// has been removed.
		// Log.d("Variance", Integer.toString(variance));
		// Log.d("Variance Result", Integer.toString(basePrice*(variance+
		// eventMod)/100));
		// Log.d("TechDiff*inc",
		// Integer.toString(Math.abs(planetTechLvl-minProd)*incPerLvl));
	}

	public int price(){
		return price;
	}
	
	public int importPriceforPlanet(int planetTechLvl){
		return basePrice + Math.abs(planetTechLvl - minProd) * incPerLvl;
	}
	
	public int generateAmount(int planetTechLvl) {
		resetPrice(planetTechLvl);
		int amount; // returned amount of item on the planet
		int baseAmount = 50;
		Random rand = new Random();

		amount = rand.nextInt(baseAmount - Math.abs(idealLvl - planetTechLvl));

		return amount;
	}

	public boolean canSell(int planetTechLvl) {
		// returns true if the player can sell the good at the planet
		return (planetTechLvl >= minUse);
	}

	public boolean canBuy(int planetTechLvl) {
		// returns true if the player can buy the good at the planet
		return (planetTechLvl >= minProd);
	}

	public String getName() {
		return name;
	}
	
	public int getMLTP () { // Minimum Level To Produce
		return minProd;
	}
	
	public int getMLTU () { // Minimum Level To Use
		return minProd;
	}
	

	/*
	 * This method is serves like a database that holds the values of all the goods. 	 
	 */

	public static Good[] getDataList() {
		Good[] dataList;

		dataList = new Good[10];
		dataList[0] = new Good("Water", 0, 0, 2, 30, 3, 4);
		dataList[1] = new Good("Furs", 0, 0, 0, 250, 10, 10);
		dataList[2] = new Good("Food", 1, 0, 1, 100, 5, 5);
		dataList[3] = new Good("Ore", 2, 2, 3, 350, 20, 10);
		dataList[4] = new Good("Games", 3, 1, 6, 250, -10, 5);
		dataList[5] = new Good("Firearms", 3, 1, 5, 1250, -75, 100);
		dataList[6] = new Good("Medicine", 4, 1, 6, 650, -20, 10);
		dataList[7] = new Good("Machines", 4, 3, 5, 900, -30, 5);
		dataList[8] = new Good("Narcotics", 5, 0, 5, 3500, -125, 150);
		dataList[9] = new Good("Robots", 6, 4, 7, 5000, -150, 100);

		return dataList;
	}

}