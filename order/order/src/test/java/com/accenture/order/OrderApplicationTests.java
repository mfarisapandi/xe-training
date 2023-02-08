package com.accenture.order;

import com.accenture.order.Entity.Order;
import com.accenture.order.Service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.web.client.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderApplicationTests {
	@Autowired
	OrderService orderService;

	RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddOrderWithInvalidWizardID(){
		Order order = new Order();
		order.setWizardID(100);
		order.setMagicWandID(3);

		String actualMessage = orderService.addOrder(order);
		String expectedMessage = "Invalid Wizard ID";

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testAddOrderWithInvalidMagicWandID() {
		Order order = new Order();
		order.setWizardID(2);
		order.setMagicWandID(100);

		String actualMessage = orderService.addOrder(order);
		String expectedMessage = "Invalid Magic Wand ID";

		assertEquals(expectedMessage, actualMessage);
	}
	@Test
	public void testAddOrderWithOutOfStockWand() {
		Order order = new Order();
		order.setWizardID(2);
		order.setMagicWandID(4);
		order.setMagicWandName("RS");

		String actualMessage = orderService.addOrder(order);
		String expectedMessage = "Magic Wand ID: " + order.getMagicWandID() + "\nName: " + order.getMagicWandName() + "\nis out of stock";

		assertEquals(expectedMessage, actualMessage);
	}
	@Test
	public void testAddOrderWithWizardNotActive() {
		Order order = new Order();
		order.setWizardID(3);
		order.setMagicWandID(3);

		String actualMessage = orderService.addOrder(order);
		String expectedMessage = "Wizard with ID: 3 is not active";

		assertEquals(expectedMessage, actualMessage);
	}
	@Test
	public void testAddOrderWithWizardAgeExceedsLimit() {
		Order order = new Order();
		order.setWizardID(4);
		order.setMagicWandID(3);

		String actualMessage = orderService.addOrder(order);
		String expectedMessage = order.getWizardName() + " age is: " + order.getWizardAge() + ". The age is exceeds the age limit." +
				"\nAge limit: 20";

		assertEquals(expectedMessage , actualMessage);
	}

}
