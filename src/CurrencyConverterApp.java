package src;
import java.util.List;

public class CurrencyConverterApp {
    public void run() {
        CurrencyRepository currencyRepository = new Scraper();
        CurrencyConverter currencyConverter = CurrencyConverter.getInstance(currencyRepository);
        InputReader inputReader = new CMDInput();
        UI userInterface = new CMD_UI(inputReader, currencyRepository);

        while (true) {
            userInterface.displayMenu();
            String choice = inputReader.readInput("Chose option: ");
            switch (choice) {
                case "1":
                    boolean validAmount = false;
                    boolean validSourceCurrency = false;
                    boolean validTargetCurrency = false;
                    RequestObject userRequest = null;

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
                            userRequest = new RequestObject(amount, sourceCurrencyCode, targetCurrencyCode);
                            validAmount = true;
                            validSourceCurrency = true;
                            validTargetCurrency = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage() + " Try again.");
                        }
                    } while (!validAmount || !validSourceCurrency || !validTargetCurrency);

                    ResponseObject response = currencyConverter.convert(userRequest);
                    userInterface.displayResult(response);
                    break;
                case "2":
                    List<CurrencyData> currencies = currencyRepository.getAllCurrencies();
                    System.out.println("Available currencies:");
                    for (CurrencyData currency : currencies) {
                        System.out.println(currency.getCurrencyCode());
                    }
                    break;
                case "3":
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Not valid option. Try again.");
                    break;
            }
        }
    }
}
