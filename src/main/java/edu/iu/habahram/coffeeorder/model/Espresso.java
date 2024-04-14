package edu.iu.habahram.coffeeorder.model;

public class Espresso extends Beverage{

        @Override
        public String getDescription() {
            return "Expresso";
        }
        @Override
        public float cost() {
            return 1.34F;
        }
}
