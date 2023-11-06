package src;
// this class converting our data
public class CurrencyConverter {
    private CurrencyRepository currencyRepository;

    private static CurrencyConverter instance;

    private CurrencyConverter(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public static CurrencyConverter getInstance(CurrencyRepository currencyRepository) {
        if (instance == null) {
            instance = new CurrencyConverter(currencyRepository);
        }
        return instance;
    }

    public DTO_Response convert(DTO_Request request) {
        CurrencyDictionary sourceCurrencyData = currencyRepository.getCurrencyByCode(request.getSourceCurrencyCode());
        CurrencyDictionary targetCurrencyData = currencyRepository.getCurrencyByCode(request.getTargetCurrencyCode());

        if (sourceCurrencyData == null || targetCurrencyData == null) {
            throw new IllegalArgumentException("Invalid currency codes");
        }

        double convertedAmount = (request.getAmount() * sourceCurrencyData.getExchangeRate() / sourceCurrencyData.getFactor()) /
                (targetCurrencyData.getExchangeRate() / targetCurrencyData.getFactor());

        return new DTO_Response(convertedAmount, request.getTargetCurrencyCode());
    }
}
