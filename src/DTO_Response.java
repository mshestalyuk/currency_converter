package src;
public class DTO_Response {
    private double convertedAmount;
    private String targetCurrencyCode;

    public DTO_Response(double convertedAmount, String targetCurrencyCode) {
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
