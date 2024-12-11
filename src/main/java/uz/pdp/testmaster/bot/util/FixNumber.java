package uz.pdp.testmaster.bot.util;

public class FixNumber {

    public static String fix(String phone) {
        if (phone.startsWith("+")) {
            return phone;
        } else {
            return "+" + phone;
        }
    }
}
