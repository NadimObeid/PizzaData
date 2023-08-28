package com.nadimo.pizzaData;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import server.parser.Ingredient;
import server.parser.IngredientReport;
import server.parser.InventoryParser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootConfiguration
@SpringBootTest
class PizzaDataApplicationTests {

	@Test
	public void testReadFile(){
		InventoryParser data = new InventoryParser("/dummyinventory.csv");
		List<String> actual = data.readFile();
		String expected = "Cheese,Dairy,8.99,12.5,Italy,No, ,5,2023-12-25" ;
		assertEquals(expected,actual.get(0));
	}
	@Test
	public void testListOfIngredients(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		String expected = "2023-12-25" ;
		assertEquals(expected, actual.get(0).getExpDate().toString());
	}
	@Test
	public void testGetMostExpensiveIngredient(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		String expected = "Pepperoni";
		assertEquals(expected, actual.getMostExpensiveIngredient());
	}
	@Test
	public void testGetTotalPrice(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		double expected = 491.715;
		assertEquals(expected, actual.getTotalPrice(),0.0001);
	}
	@Test
	public void testGetClosestExpiryDate(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		String expected = "Pepperoni";
		assertEquals(expected, "Pepperoni");
	}
	@Test
	public void testGetPriceOfExpiredObjects(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		double expected = 0;
		assertEquals(expected, 10,0.0001);
	}
	@Test
	public void testGetLeast3Weights(){
		IngredientReport actual = new IngredientReport(new ArrayList<Ingredient>());
		String expected = "[Onions, Pineapple;Green Peppers, Olives]";
		assertEquals(expected, actual.getLeast3Weights().toString());
	}

}