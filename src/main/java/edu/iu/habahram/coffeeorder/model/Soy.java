package edu.iu.habahram.coffeeorder.model;

public class Soy extends CondimentDecorator{

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }
    @Override
    public float cost() {
        return 0.27F + beverage.cost();
    }
}
