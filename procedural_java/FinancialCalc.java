class FinancialCalc {

    public static void main(String[] args) {
        double principal = 1000.00; // $1000 initial investment
        double interestRate = 0.035; // 3.5% interest rate
        int numOfYears = 7; // investment length is 7 years

        double finalValue = 0.0;
        finalValue = principal * Math.pow((1 + interestRate), numOfYears);

        System.out.println("Investing $" + principal
                + " at an interest rate of " + (interestRate * 100) + "%"
                + " for " + numOfYears + " years"
                + " will have a final worth of $" + finalValue);
    }
    
    static double computeFinalValue(double principal,
                                    double interestRate,
                                    int numOfYears) {
      /*REPLACE WITH YOUR CODE*/
      return 0.0;
    }
    
    static double computePrincipal(double finalValue,
                                   double interestRate,
                                   int numOfYears) {
        /*REPLACE WITH YOUR CODE*/
        return 0.0;
    }

    static double computeInterestRate(double finalValue,
                                      double principal,
                                      int numOfYears) {
        /*REPLACE WITH YOUR CODE*/
        return 0.0;
    }

    static double computeNumOfYears(double finalValue,
                                    double principal,
                                    double interestRate) {
        /*REPLACE WITH YOUR CODE*/
        return 0.0;
    }

}
