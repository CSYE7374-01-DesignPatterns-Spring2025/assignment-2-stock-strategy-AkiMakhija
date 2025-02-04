package edu.neu.csye7374;

import edu.neu.csye7374.interfaces.PriceCalculationStrategy;
import edu.neu.csye7374.interfaces.Tradable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock implements Tradable {

    private String ID;

    private String description;
    protected double price;

    private int metric;

    private List<Double> bids;

    protected PriceCalculationStrategy priceCalculationStrategy;


    public Stock() {
        this.ID = "";
        this.price = 0;
        this.description = "";
        this.bids = new ArrayList<>();
        this.metric = 0;
    }

    public Stock(String ID, double price, String description) {
        this.ID = ID;
        this.price = price;
        this.description = description;
        this.bids = new ArrayList<>();
        this.bids.add(price);
        this.metric = 0;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        if(bids.size() == 0) {
            bids.add(price);
        }
    }

    @Override
    public void setBid(double bid) {
        this.bids.add(bid);
        calculatePrice();
    }

    public void calculatePrice() {
        double avg = 0;
        for(double p : bids) {
            avg+= p;
        }
        avg = avg/bids.size();
        if(avg > this.price ) {
            setMetric(this.metric +1);
        }else if(avg < this.price) {
            setMetric(this.metric -1);
        }
        setPrice(avg);
    }

    public List<Double> getBid(){
        return this.bids;
    }

    @Override
    public int getMetric() {
        return this.metric;
    }

    public String getMetricValue() {
        if(this.getMetric() < -3) {
            return "--> Not a good stock, Not Recommended..!!";
        } else if (this.getMetric() >= -3 && this.getMetric() <= 3) {
            return " --> Good stock, But be cautious..!!";
        } else {
            return " --> Excellent stock, Highly Recommended..!!";
        }
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + ID + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", Metric Value=" + getMetricValue() +
                '}';
    }

    public void setDescription(String desc) {
        this.description = desc;
    }


    private void setPriceCalculationStrategy(PriceCalculationStrategy priceCalculationStrategy) {
        this.priceCalculationStrategy = priceCalculationStrategy;
    }


    public void tradeStockUsingStrategy(PriceCalculationStrategy priceCalculationStrategy, double bid) {
        this.setPriceCalculationStrategy(priceCalculationStrategy);
        this.bids.add(bid);
        calculatePrice();
    }
}
