package xyz.sethy.woutobot.processor;

import xyz.sethy.woutobot.dto.OptimizedString;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Seth on 02/09/2017.
 */
public class TextProcessor {
    private final int[] rating;
    private final AtomicBoolean locked;
    private final OptimizedString text;

    public TextProcessor(final String text) {
        this.rating = new int[0];
        this.locked = new AtomicBoolean(true);
        this.text = OptimizedString.valueOf(text);
        this.locked.set(false);
    }

    public void process() {
        char[] words = this.text.toChars();

        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
            char word = words[i];
            if (i == 0) {

            }
        }
    }
}
