package uz.pdp.testmaster.bot.factory;


import uz.pdp.testmaster.bot.commands.SaveFullNameEndShareContact;
import uz.pdp.testmaster.bot.commands.SelectOption;
import uz.pdp.testmaster.bot.commands.ShareExam;
import uz.pdp.testmaster.bot.commands.WelcomeMessageAndSelectOption;
import uz.pdp.testmaster.bot.entity.State;

public class CommandFactory {
    public static Command getCommand(State state) {
        return switch (state) {
            case SHARE_CONTACT -> new SelectOption();
            case SHARE_EXAM -> new ShareExam();
            case START -> new WelcomeMessageAndSelectOption();
            case SELECT_PRODUCT -> null;
            case SELECT_CATEGORY -> null;
            case SELECT_PRODUCT_AND_INFO -> null;
            case BACK -> null;
            case SAVE_FULLNAME -> new SaveFullNameEndShareContact();
            case CONTINUE -> null;
        };
    }
}
