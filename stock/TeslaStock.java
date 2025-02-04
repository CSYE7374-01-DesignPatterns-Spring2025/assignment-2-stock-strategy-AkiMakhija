package edu.neu.csye7374.stock;

import edu.neu.csye7374.Stock;

public class TeslaStock extends Stock {



    public TeslaStock() {
        super();
    }

    public TeslaStock(String name, double price, String description) {
        super(name, price, description);
    }


    @Override
    public void calculatePrice() {
        double avg  = this.priceCalculationStrategy.calculatePrice(getBid());
        if(avg > this.price && getMetric() >= 0) {
            setMetric(getMetric() +2);
        }else if(avg > this.price && getMetric() < 0){
            setMetric(0);
        }else if(avg < this.price && getMetric() <= 0) {
            setMetric(getMetric()-2);
        }else if(avg < this.price && getMetric() > 0) {
            setMetric(0);
        }
        setPrice(avg);
    }
}
