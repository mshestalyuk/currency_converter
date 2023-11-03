// This class have store our data 
package src;
public class CurrencyData {
    private String currencyCode;
    private double exchangeRate;
    private double factor;

    public CurrencyData(String currencyCode, double exchangeRate, double factor) {
        this.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
        this.factor = factor;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double getFactor() {
        return factor;
    }
}
