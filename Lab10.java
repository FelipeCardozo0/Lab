public class Lab10 {
    public static void main(String[] args) {
        System.out.println(clearEmojis("LOL :-D"));
        System.out.println(clearEmojis("Smile   :-)"));
        System.out.println(clearEmojis("blah :P teehee :-)"));
        System.out.println(isValidEmail("user_123@gmail.com"));
        System.out.println(isValidEmail("user123alpha@gmail.com"));
        System.out.println(isValidEmail("user@domain.xyz"));
        System.out.println(isValidEmail("123user@domain.com"));
        System.out.println(isValidEmail("user@domaintoolong.com"));

    }
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z][\\w]{0,9}@[a-zA-Z][a-zA-Z0-9]{0,11}\\.[a-z]{1,3}$";

        return email.matches(regex);
    }

    public static String clearEmojis(String sms) {
        String regex = "(?<=\\s)(:-\\)|:-D|:-P|:\\)|:D|:P)";

        String result = sms.replaceAll(regex, ".");

        return result.replaceAll("^\\s+", "");
    
        }



    }


