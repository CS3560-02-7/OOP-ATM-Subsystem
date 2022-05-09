import java.util.regex.Matcher;
import java.util.regex.Pattern;
//let
public class Validator {

    public static boolean isValidPassword(String pin) {

        if (pin.length() == 4) {
            //Compiles the given regular expression into a pattern
            Pattern digit = Pattern.compile("[0-9]");
            //Creates a matcher that will match the given input against this pattern.
            Matcher containsDigit = digit.matcher(pin);
        } else
            return false;
        return false;
    }

    //method reformats a float into a string with currency format ie $1.00
    public static String formatCurrency(Float number) {
        // %.2f -- % is treated as a symbol and .2 only the first two digits after a point
        return "$" + String.format("%.2f", number);
    }

    public static String toCleanNumber(String number) {
        int numberInt = (int) (Float.parseFloat(number) - 0);
        return String.valueOf(numberInt);
    }
}


