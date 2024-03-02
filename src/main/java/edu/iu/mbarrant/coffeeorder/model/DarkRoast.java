package edu.iu.mbarrant.coffeeorder.model;

import java.util.ArrayList;
import java.util.List;

public class DarkRoast extends Beverage {

    private List<CondimentDecorator> condiments = new ArrayList<>();

    public void addCondiment(CondimentDecorator condiment) {
        condiments.add(condiment);
    }

    @Override
    public float cost() {
        float totalCost = 1.99F; // Base cost of DarkRoast
        for (CondimentDecorator condiment : condiments) {
            totalCost += condiment.cost();
        }
        return totalCost;
    }

    @Override
    public String getDescription() {
        return "Dark Roast";
    }
}
