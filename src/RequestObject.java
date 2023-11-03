package src;
public class RequestObject {
    private double amount;
    private String sourceCurrencyCode;
    private String targetCurrencyCode;

    public RequestObject(double amount, String sourceCurrencyCode, String targetCurrencyCode) {
        this.amount = amount;
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }
}
