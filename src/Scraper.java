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
    private Map<String, CurrencyData> currencies = new HashMap<>();

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
                currencies.put(code, new CurrencyData(code, exchangeRate, factor));

            }
        } catch (Exception err) {
            err.printStackTrace();

        }

    }

    @Override
    public CurrencyData getCurrencyByCode(String code) {
        if (currencies.containsKey(code)) {
            return currencies.get(code);
        } else {
            throw new IllegalArgumentException("Currency with code " + code + " not found.");
        }
    }

    @Override
    public List<CurrencyData> getAllCurrencies() {
        return new ArrayList<>(currencies.values());
    }

    @Override
    public List<CurrencyData> getAllExchangeRates() {
        return new ArrayList<>(currencies.values());
    }
}
