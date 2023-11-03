// This class store list of CurrencyData(Like exchange rate and smth like this)
package src;

import java.util.List;


interface CurrencyRepository {
    CurrencyData getCurrencyByCode(String code);

    List<CurrencyData> getAllCurrencies();

    List<CurrencyData> getAllExchangeRates();
}
