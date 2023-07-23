package com.nadimo.pizzaData;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import parser.IngredientReport;
import parser.InventoryParser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PizzaDataApplicationTests {

	@Test
	public void testReadFile(){
		InventoryParser data = new InventoryParser("/dummyinventory.csv");
		List<String> actual = data.readFile();
		String expected = "Cheese,Classic,8.99,12.5,Italy,No,,2-8°C,25 Dec 2023" ;
		assertEquals(expected,actual.get(0));
	}
	@Test
	public void testListOfIngredients(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		String expected = "2023-12-25" ;
		assertEquals(expected, actual.get(0).getExpDate().toString());
	}
	@Test
	public void testGetMostExpensiveIngredient(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		String expected = "Shrimp;Calamari;Mussels";
		assertEquals(expected, actual.getMostExpensiveIngredient());
	}
	@Test
	public void testGetTotalPrice(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		double expected = 1656.61;
		assertEquals(expected, actual.getTotalPrice(),0.0001);
	}
	@Test
	public void testGetClosestExpiryDate(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		String expected = "Pineapple";
		assertEquals(expected, actual.getClosestExpiryDate());
	}
	@Test
	public void testGetPriceOfExpiredObjects(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		double expected = 550.585;
		assertEquals(expected, actual.getPriceOfExpiredObjects(),0.0001);
	}
	@Test
	public void testGetLeast3Weights(){
		IngredientReport actual = new IngredientReport("/dummyinventory.csv");
		String expected = "[Gluten-Free, Cheese;Eggs;Wheat, Mushrooms]";
		assertEquals(expected, Arrays.toString(actual.getLeast3Weights()));
	}


}
