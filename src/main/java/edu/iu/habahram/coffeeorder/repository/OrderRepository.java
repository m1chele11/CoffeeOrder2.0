package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {
    private final AtomicInteger receiptIdCounter = new AtomicInteger(0);

    float totalCost = 0.0F;
    float totalCondimentCost = 0.0F;

    float beverageCost = 0.0F;

    public Receipt add(OrderData order) throws Exception {

        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
            case "dark roast":
                beverage = new DarkRoast();
                beverageCost += beverage.cost();
                break;
            case "decaf":
                beverage = new Decaf();
                beverageCost += beverage.cost();
                break;
            case "expresso":
                beverage = new Expresso();
                beverageCost += beverage.cost();
                break;
            case "houseblend":
                beverage = new HouseBlend();
                beverageCost += beverage.cost();
                break;
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.beverage()));
        }


        for (String condiment : order.condiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                    beverage = new Milk(beverage);
                    totalCondimentCost += .4F;
                    break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    totalCondimentCost += .3F;
                    break;
                case "soy":
                    beverage = new Soy(beverage);
                    totalCondimentCost += .27F;
                    break;
                case "whip":
                    beverage = new Whip(beverage);
                    totalCondimentCost += .25F;
                    break;
                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }

        totalCost = beverageCost + totalCondimentCost;
        totalCost = round(totalCost, 2);
        //System.out.println(totalCost);



        int receiptId = receiptIdCounter.incrementAndGet();
        Receipt receipt = new Receipt(beverage.getDescription(), totalCost);

        // Store the order in the "db.txt" file
        storeOrderInDatabase(receipt);

        return receipt;
    }
    private void storeOrderInDatabase(Receipt receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", true))) {
            String orderInfo = String.format("%.2f,%s%n", receipt.cost(), receipt.description());
            writer.write(orderInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
