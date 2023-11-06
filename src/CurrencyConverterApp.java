package src;
import java.util.List;


public class CurrencyConverterApp {
    public void run() {
        CurrencyRepository currencyRepository = new Scraper();
        CurrencyConverter currencyConverter = CurrencyConverter.getInstance(currencyRepository);
        Menu_CMD inputReader = new Menu_CMD();
        UI userInterface = new Menu_UI(inputReader, currencyRepository);

        while (true) {
            userInterface.displayMenu();
            String choice = inputReader.readInput("Chose option: ");
            switch (choice) {
                case "1":
                    boolean validAmount = false;
                    boolean validSourceCurrency = false;
                    boolean validTargetCurrency = false;
                    DTO_Request userRequest = null;

                    do {
                        try {
                            double amount = Double.parseDouble(inputReader.readInput("Enter the amount: "));
                            if (amount < 0) {
                                throw new IllegalArgumentException("The amount cannot be negative.");
                            }
                            String sourceCurrencyCode = inputReader.readInput("Enter the source currency code: ");
                            if (currencyRepository.getCurrencyByCode(sourceCurrencyCode) == null) {
                                throw new IllegalArgumentException("Not valid source currency code.");
                            }
                            String targetCurrencyCode = inputReader.readInput("Enter the target currency code: ");
                            if (currencyRepository.getCurrencyByCode(targetCurrencyCode) == null) {
                                throw new IllegalArgumentException("Not valid target currency code.");
                            }
                            userRequest = new DTO_Request(amount, sourceCurrencyCode, targetCurrencyCode);
                            validAmount = true;
                            validSourceCurrency = true;
                            validTargetCurrency = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage() + " Try again.");
                        }
                    } while (!validAmount || !validSourceCurrency || !validTargetCurrency);

                    DTO_Response response = currencyConverter.convert(userRequest);
                    userInterface.displayResult(response);
                    break;
                case "2":
                    List<CurrencyDictionary> currencies = currencyRepository.getAllCurrencies();
                    System.out.println("Available currencies:");
                    for (CurrencyDictionary currency : currencies) {
                        System.out.println(currency.getCurrencyCode());
                    }
                    break;
                case "3":
                    try {
                        String newCurrencyCode = inputReader.readInput("Enter the new currency code to add: ");
                        double exchangeRate = Double.parseDouble(inputReader.readInput("Enter the exchange rate: "));
                        double factor = Double.parseDouble(inputReader.readInput("Enter the factor: "));
                        currencyRepository.addCurrencyByCode(newCurrencyCode, exchangeRate, factor);
                        System.out.println("Currency with code " + newCurrencyCode + " added successfully with exchange rate " + exchangeRate + " and factor " + factor + ".");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter valid numbers for exchange rate and factor.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Not valid option. Try again.");
                    break;
            }
        }
    }
}
