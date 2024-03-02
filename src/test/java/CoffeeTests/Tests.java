package CoffeeTests;

import edu.iu.habahram.coffeeorder.model.*;
import edu.iu.habahram.coffeeorder.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testAddDarkRoastWithoutCondiments() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Dark Roast", List.of());
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Dark Roast", receipt.description());
        assertEquals(1.99F, receipt.cost());
    }

    @Test
    public void testAddDarkRoastWithMilk() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Dark Roast", List.of("Milk"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Dark Roast, Milk", receipt.description());
        System.out.println(receipt.cost());
        assertEquals(2.39F, receipt.cost());
    }

    @Test
    public void testAddDarkRoastWithMilkAndMocha() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Dark Roast", List.of("Milk", "Mocha"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Dark Roast, Milk, Mocha", receipt.description());
        assertEquals(2.69F, receipt.cost());
    }

    @Test
    public void testAddDarkRoastWithMilkAndMochaAndSoy() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Dark Roast", List.of("Milk", "Mocha", "Soy"));
        Receipt receipt = orderRepository.add(orderData);
        System.out.println(receipt);
        System.out.println(receipt.cost());
        assertEquals(2.96F, round(receipt.cost(), 2)); //round to 2 decimal places
        assertEquals("Dark Roast, Milk, Mocha, Soy", receipt.description());
    }


    //test case #5
    @Test
    public void testAddDarkRoastWithMilkAndMochaAndSoyAndWhip() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Dark Roast", List.of("Milk", "Mocha", "Soy", "Whip"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Dark Roast, Milk, Mocha, Soy, Whip", receipt.description());
        assertEquals(3.21F, round(receipt.cost(), 2)); //round to 2 decimal places
    }

    @Test
    public void testHouseBlendWithMilkAndMochaAndSoyAndWhip() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("HouseBlend", List.of("Milk", "Mocha", "Soy", "Whip"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("House Blend, Milk, Mocha, Soy, Whip", receipt.description());
        assertEquals(2.87F, round(receipt.cost(), 2)); //round to 2 decimal places
    }

    @Test
    public void testHouseBlendWithMilkAndMochaAndSoy() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("HouseBlend", List.of("Milk", "Mocha", "Soy"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("House Blend, Milk, Mocha, Soy", receipt.description());
        assertEquals(2.62F, round(receipt.cost(), 2)); //round to 2 decimal places
    }

    @Test
    public void testExpressoWithMilkAndMochaAndSoyAndWhip() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Expresso", List.of("Milk", "Mocha", "Soy", "Whip"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Expresso, Milk, Mocha, Soy, Whip", receipt.description());
        assertEquals(2.56F, round(receipt.cost(), 2)); //round to 2 decimal places
    }

    @Test
    public void testDecafWithMilkAndMochaAndSoyAndWhip() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Decaf", List.of("Milk", "Mocha", "Soy", "Whip"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Decaf, Milk, Mocha, Soy, Whip", receipt.description());
        assertEquals(2.5F, round(receipt.cost(), 2)); //round to 2 decimal places
    }

    //test case #10
    @Test
    public void testAddHouseBlendWithoutCondiments() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("HouseBlend", List.of());
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("House Blend", receipt.description());
        assertEquals(1.65F, receipt.cost());
    }

    @Test
    public void testAddExpressoWithoutCondiments() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Expresso", List.of());
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Expresso", receipt.description());
        assertEquals(1.34F, receipt.cost());
    }

    @Test
    public void testAddDecafWithoutCondiments() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Decaf", List.of());
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Decaf", receipt.description());
        assertEquals(1.28F, receipt.cost());
    }

    @Test
    public void testAddExpressoWithMilk() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Expresso", List.of("Milk"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Expresso, Milk", receipt.description());
        assertEquals(1.74F, receipt.cost());
    }

    @Test
    public void testAddExpressoWithMilkAndMocha() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Expresso", List.of("Milk", "Mocha"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Expresso, Milk, Mocha", receipt.description());
        assertEquals(2.04F, receipt.cost());
    }

    //test case #15
    @Test
    public void testAddExpressoWithMilkAndMochaAndSoy() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("Expresso", List.of("Milk", "Mocha", "Soy"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("Expresso, Milk, Mocha, Soy", receipt.description());
        assertEquals(2.31F, receipt.cost());
    }

    @Test
    public void testAddHouseBlendWithMilk() throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        OrderData orderData = new OrderData("HouseBlend", List.of("Milk"));
        Receipt receipt = orderRepository.add(orderData);
        assertEquals("House Blend, Milk", receipt.description());
        assertEquals(2.05F, receipt.cost());
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }


}
