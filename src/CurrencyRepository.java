// This class store list of CurrencyData(Like exchange rate and smth like this)
package src;

import java.util.List;


interface CurrencyRepository {
    CurrencyDictionary getCurrencyByCode(String code);

    List<CurrencyDictionary> getAllCurrencies();

    List<CurrencyDictionary> getAllExchangeRates();
}
