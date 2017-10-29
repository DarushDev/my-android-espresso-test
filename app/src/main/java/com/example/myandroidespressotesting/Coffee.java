package com.example.myandroidespressotesting;

/**
 * Created by Lenovo Desktop on 10/29/2017.
 */

class Coffee {

    String coffeeName;
    String coffeeDesc;

    public Coffee(String coffeeName, String coffeeDesc) {
        this.coffeeDesc = coffeeDesc;
        this.coffeeName = coffeeName;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeeDesc() {
        return coffeeDesc;
    }

    public void setCoffeeDesc(String coffeeDesc) {
        this.coffeeDesc = coffeeDesc;
    }
}
