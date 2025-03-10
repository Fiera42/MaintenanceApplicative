package trivia.game;

import java.util.LinkedList;

public enum QuestionTheme {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    GEOGRAPHY("Geography");

    private final String text;

    QuestionTheme(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public LinkedList<String> generateThemeQuestions() {
        var themeQuestions = new LinkedList<String>();

        for (int i = 0; i < 50; i++) {
            themeQuestions.add(this.text + " Question " + i);
        }

        return themeQuestions;
    }
}
