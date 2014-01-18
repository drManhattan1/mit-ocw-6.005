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
        
        System.out.println("\nAnswers to questions:");
        System.out.println("1.  " + computePrincipal(1000.00,0.05,4));
        System.out.println("2.  " + computeInterestRate(525.00,500.00,3));
        System.out.println("3.  " + computeNumOfYears(150.00,100.00,0.044));
    }
    
    static double computeFinalValue(double principal,
                                    double interestRate,
                                    int numOfYears) {
      return principal * Math.pow((1 + interestRate), numOfYears);
    }
    
    static double computePrincipal(double finalValue,
                                   double interestRate,
                                   int numOfYears) {
        return finalValue / Math.pow(1 + interestRate, numOfYears);
    }

    static double computeInterestRate(double finalValue,
                                      double principal,
                                      int numOfYears) {
        return Math.pow(10,Math.log(finalValue/principal) / numOfYears) - 1;
    }

    static double computeNumOfYears(double finalValue,
                                    double principal,
                                    double interestRate) {
        return Math.log(finalValue/principal) / Math.log(1 + interestRate);
    }

}
