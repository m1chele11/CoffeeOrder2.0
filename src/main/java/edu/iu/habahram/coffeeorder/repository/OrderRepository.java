package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {
    private final AtomicInteger receiptIdCounter = new AtomicInteger(0);

    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
            case "dark roast":
                beverage = new DarkRoast();
                break;
            case "decaf":
                beverage = new Decaf();
                break;
            case "expresso":
                beverage = new Expresso();
                break;
            case "house blend":
                beverage = new HouseBlend();
                break;
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.beverage()));
        }
        for (String condiment : order.condiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                    beverage = new Milk(beverage);
                    break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    break;
                case "soy":
                    beverage = new Soy(beverage);
                    break;
                case "whip":
                    beverage = new Whip(beverage);
                    break;
                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }

        int receiptId = receiptIdCounter.incrementAndGet();
        Receipt receipt = new Receipt(beverage.getDescription(), beverage.cost(), receiptId);

        // Store the order in the "db.txt" file
        storeOrderInDatabase(receipt);

        return receipt;
    }
    private void storeOrderInDatabase(Receipt receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", true))) {
            String orderInfo = String.format("%d,%.2f,%s%n", receipt.id(), receipt.cost(), receipt.description());
            writer.write(orderInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
