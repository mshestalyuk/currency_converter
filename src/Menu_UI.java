package src;


public class Menu_UI implements UI {
    private InputReader inputReader;
    private CurrencyRepository currencyRepository;

    public Menu_UI(InputReader inputReader, CurrencyRepository currencyRepository) {
        this.inputReader = inputReader;
        this.currencyRepository = currencyRepository;
    }


    @Override
    public DTO_Request getUserInput() {
        double amount = getValidAmount();
        String sourceCurrencyCode = getValidCurrencyCode("Enter the source currency code: ");
        String targetCurrencyCode = getValidCurrencyCode("Enter the target currency code: ");
        return new DTO_Request(amount, sourceCurrencyCode, targetCurrencyCode);
    }

    private double getValidAmount() {
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(inputReader.readInput("Enter the amount: "));
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }

    private String getValidCurrencyCode(String prompt) {
        while (true) {
            String code = inputReader.readInput(prompt);
            if (currencyRepository.getCurrencyByCode(code) != null) {
                return code;
            } else {
                System.out.println("Invalid currency code. Please enter a valid code.");
            }
        }
    }

    @Override
    public void displayResult(DTO_Response response) {
        System.out.println(response.getConvertedAmount() + " " + response.getTargetCurrencyCode());
    }

    @Override
    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Exchange");
        System.out.println("2. List of available currency");
        System.out.println("3. Exit");
    }
}
