package edu.neu.csye7374.stock;

import edu.neu.csye7374.Stock;

public class NikeStock extends Stock {

    public NikeStock() {
        super();
    }

    public NikeStock(String name, double price, String description) {
        super(name, price, description);
    }


    @Override
    public void calculatePrice() {
        double avg  = this.priceCalculationStrategy.calculatePrice(getBid());
        if(avg > this.price && getMetric() >= 0) {
            setMetric(getMetric() +1);
        }else if(avg > this.price && getMetric() < 0){
            setMetric(0);
        }else if(avg < this.price && getMetric() <= 0) {
            setMetric(getMetric()-1);
        }else if(avg < this.price && getMetric() > 0) {
            setMetric(0);
        }
        setPrice(avg);
    }
}
