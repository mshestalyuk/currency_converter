package src;
// this class converting our data
public class CurrencyConverter {
    private CurrencyRepository currencyRepo;

    private static CurrencyConverter instance;

    private CurrencyConverter(CurrencyRepository currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public static CurrencyConverter getInstance(CurrencyRepository currencyRepo) {
        if (instance == null) {
            instance = new CurrencyConverter(currencyRepo);
        }
        return instance;
    }

    public DTO_Response convert(DTO_Request request) {
        CurrencyDictionary sourceCurrencyData = currencyRepo.getCurrencyByCode(request.getSourceCurrencyCode());
        CurrencyDictionary targetCurrencyData = currencyRepo.getCurrencyByCode(request.getTargetCurrencyCode());

        if (sourceCurrencyData == null || targetCurrencyData == null) {
            throw new IllegalArgumentException("Invalid currency codes");
        }

        double convertedAmount = (request.getAmount() * sourceCurrencyData.getExchangeRate() / sourceCurrencyData.getFactor()) /
                (targetCurrencyData.getExchangeRate() / targetCurrencyData.getFactor());

        return new DTO_Response(convertedAmount, request.getTargetCurrencyCode());
    }
}
