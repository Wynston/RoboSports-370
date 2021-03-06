package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    /**
     * A regular expression {@link String} that will trigger this word.
     */
    private String trigger;
    /**
     * The {@link ForthExecuter} that is execute when this word is triggered.
     */
    private ForthExecuter executer;
    /**
     * An array of matches to the regular expression after it is triggered.
     */
    private String[] match;

    /**
     * Creates a new {@link Word}
     *
     * @param trigger  A regular expression {@link String} that will trigger this word
     * @param executer the {@link ForthExecuter} that will execute this word when it is triggered.
     */
    public Word(final String trigger, final ForthExecuter executer) {
        this.trigger = trigger;
        this.executer = executer;
    }

    /**
     * Checks if a {@link String} will trigger this word
     *
     * @param string The {@link String} to check
     * @return {@code true} if the string is a trigger,
     * {@code false} otherwise.
     */
    public boolean isTrigger(final String string) {
        match = null;

        Matcher m = Pattern.compile("^" + trigger + "( |$)").matcher(string);

        if (m.find()) {
            match = new String[m.groupCount() + 1];
            for (int i = 0; i < m.groupCount() + 1; i++)
                match[i] = m.group(i);
            return true;
        }
        return false;
    }

    public String getTrigger() {
        return this.trigger;
    }

    /**
     * Executes this words {@link ForthExecuter}
     */
    public void execute() {
        executer.executeForth(match);
    }
}

