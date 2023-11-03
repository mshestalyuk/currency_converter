package src;
public class ResponseObject {
    private double convertedAmount;
    private String targetCurrencyCode;

    public ResponseObject(double convertedAmount, String targetCurrencyCode) {
        this.convertedAmount = convertedAmount;
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }
}
