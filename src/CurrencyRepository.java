// This class store list of CurrencyData(Like exchange rate and smth like this)
package src;

import java.util.List;


interface CurrencyRepository {
    CurrencyDictionary getCurrencyByCode(String code);
    CurrencyDictionary addCurrencyByCode(String code, double exchangeRate, double factor);
    List<CurrencyDictionary> getAllCurrencies();

    List<CurrencyDictionary> getAllExchangeRates();
}
