// This class have store our data 
package src;
public class CurrencyDictionary {
    private String currencyCode;
    private double exchangeRate;
    private double factor;

    public CurrencyDictionary(String currencyCode, double exchangeRate, double factor) {
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
