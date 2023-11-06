// This is the scraper that parse XML file and saves data to class CurrencyRepository, data is saved in CurrencyData type

package src;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scraper implements CurrencyRepository {
    private Map<String, CurrencyDictionary> currencies = new HashMap<>();

    public Scraper() {

        try {
            URL url = new URL("https://www.nbp.pl/kursy/xml/lasta.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            NodeList currencyNodes = document.getElementsByTagName("pozycja");
            for (int i = 0; i < currencyNodes.getLength(); i++) {
                Element currencyElement = (Element) currencyNodes.item(i);
                String code = currencyElement.getElementsByTagName("kod_waluty").item(0).getTextContent();
                double exchangeRate = Double.parseDouble(currencyElement.getElementsByTagName("kurs_sredni").item(0).getTextContent().replace(",", "."));
                double factor = Double.parseDouble(currencyElement.getElementsByTagName("przelicznik").item(0).getTextContent().replace(",", "."));
                currencies.put(code, new CurrencyDictionary(code, exchangeRate, factor));

            }
        } catch (Exception err) {
            err.printStackTrace();

        }

    }

    // this is getter that returns currency By Code, overrided from CurrencyRepository getter 
    @Override
    public CurrencyDictionary getCurrencyByCode(String code) {
        if (currencies.containsKey(code)) {
            return currencies.get(code);
        } else {
            throw new IllegalArgumentException("Currency with code " + code + " not found.");
        }
    }
    
    @Override
    public CurrencyDictionary addCurrencyByCode(String code, double exchangeRate, double factor) {
        if (!currencies.containsKey(code)) {
            CurrencyDictionary newCurrency = new CurrencyDictionary(code, exchangeRate, factor);
            currencies.put(code, newCurrency);
            return newCurrency;
        } else {
            throw new IllegalArgumentException("Currency with code " + code + " already exists.");
        }
    }
    
    @Override
    public List<CurrencyDictionary> getAllCurrencies() {
        return new ArrayList<>(currencies.values());
    }

    @Override
    public List<CurrencyDictionary> getAllExchangeRates() {
        return new ArrayList<>(currencies.values());
    }
}
