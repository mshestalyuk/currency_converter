package src;
 public class Currency {
    private String code;
    private double exchangeRate;

    public Currency(String code, double exchangeRate) {
         this.code = code;
         this.exchangeRate = exchangeRate;
            }

    public String getCode() {
        return code;
        }

    public double getExchangeRate() {
        return exchangeRate;
        }
}