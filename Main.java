//Imports
import java.util.Scanner;
import java.io.File;

class Main {
  //Vars
  String binaryAnswer = "";
  int decimalAnswer = 0;
  int exponent = 0;
  //Colored Text
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  
  public Main() {
    //Getting User Input
    System.out.print("\nPlease enter \"file\" or \"input\" to use the console: " + ANSI_PURPLE);
    Scanner scanner1 = new Scanner(System.in);
    String input = scanner1.nextLine();
    String numberInput = "";

    //Authenticating Input
    if (input.equals("file")) { //input from file
      try {   //file exists
        System.out.print(ANSI_WHITE + "Enter your file name: " + ANSI_PURPLE);
        input = scanner1.nextLine();
        Scanner fileScanner = new Scanner(new File(input));
        numberInput = fileScanner.nextLine();
        authenticateValue(Double.parseDouble(numberInput));
      } catch (Exception ex) {    //file doesn't exist
        System.out.println(ANSI_RED + "Error: File not found.");
        scanner1.close();
        System.exit(1); //1 indicates that there was an error when exiting
      }
    } else if (input.equals("input")) {     //input from console
      System.out.print(ANSI_WHITE + "Enter number: " + ANSI_PURPLE);
      numberInput = scanner1.nextLine();
      authenticateValue(Double.parseDouble(numberInput));
    } else { //false user input
      System.out.println(ANSI_RED + "Error: Unknown response.");
      System.exit(1);
    }
    
    //Asking which conversion to make
    System.out.print(ANSI_WHITE + "Enter \"dtb\" for translating decimal to binary or \"btd\" for binary to decimal: " + ANSI_PURPLE);
    Scanner whichConversion = new Scanner(System.in);
    input = whichConversion.nextLine();
    
    //Analyzing User Response
    if (input.equals("dtb")) {                              //decimal to binary
      int number = Integer.parseInt(numberInput);
      //checks that decimal value isn't too big
      if (number > 65535) {
        System.out.println(ANSI_RED + "Error: No numbers greater than 65,535.");
        System.exit(1);
      }
      //Convert to Binary
      while (number > 0) {
        if (number % 2 == 1) {
          binaryAnswer = "1" + binaryAnswer;
          number = number/2;
        } else {
          binaryAnswer = "0" + binaryAnswer;
          number = number/2; 
        }
      }
      System.out.println(ANSI_WHITE + "\nBinary Value: " + binaryAnswer);
    } else if (input.equals("btd")) {                         //binary to decimal
      //converts binary value to number one by one & right to left
      for (int position = numberInput.length() -1; position >= 0; position--) {
        if (numberInput.charAt(position) == '1') {
          decimalAnswer += (int)(Math.pow(2, exponent));
        } else if (numberInput.charAt(position) != '1' && numberInput.charAt(position) != '0') { //binary value was not entered
          System.out.println(ANSI_RED + "Error: Please only enter 1's and 0's.");
          System.exit(1);
        }
        exponent++;
      }
      System.out.println(ANSI_WHITE + "\nDecimal Value: " + decimalAnswer);
    } else {    //false user input
      System.out.println(ANSI_RED + "Error: Unknown response.");
      System.exit(1);
    }

    scanner1.close();
  }
  
  public static void main(String[] args) {
    new Main();
  }

  //Makes sure number is a positive integer
  public static void authenticateValue(double value) {
    if (value < 0) {
      System.out.println(ANSI_RED + "Error: Please enter positive values only.");
      System.exit(1);
    } else if (value - Math.floor(value) != 0) {
      System.out.println(ANSI_RED + "Error: Please enter integers only.");
      System.exit(1);
    }
  }
  
}