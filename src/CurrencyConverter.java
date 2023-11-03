package src;

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

    public ResponseObject convert(RequestObject request) {
        CurrencyData sourceCurrencyData = currencyRepository.getCurrencyByCode(request.getSourceCurrencyCode());
        CurrencyData targetCurrencyData = currencyRepository.getCurrencyByCode(request.getTargetCurrencyCode());

        if (sourceCurrencyData == null || targetCurrencyData == null) {
            throw new IllegalArgumentException("Invalid currency codes");
        }

        double convertedAmount = (request.getAmount() * sourceCurrencyData.getExchangeRate() / sourceCurrencyData.getFactor()) /
                (targetCurrencyData.getExchangeRate() / targetCurrencyData.getFactor());

        return new ResponseObject(convertedAmount, request.getTargetCurrencyCode());
    }
}
