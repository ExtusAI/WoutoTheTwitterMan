package xyz.sethy.woutobot.dto;

/**
 * Created by Seth on 02/09/2017.
 */
public final class OptimizedString {

    private static final OptimizedString EMPTY = new OptimizedString(new char[]{});

    private char[] values;

    private OptimizedString(final char[] values1) {
        values = values1;
    }

    @Override
    public String toString() {
        return new String(values);
    }

    public char[] toChars() {
        return values;
    }

    public boolean equals(final OptimizedString string) {
        boolean equals = true;
        char[] compareValues = string.toChars();
        for(char c : values) {
            for(char c1 : compareValues) {
                if(c != c1)
                    equals = false;
            }
        }
        return equals;
    }

    public static OptimizedString valueOf(final String string) {
        char[] values = new char[string.length()];
        for (int i = 0, wordsLength = string.length(); i < wordsLength; i++) {
            values[i] = string.charAt(i);
        }

        return new OptimizedString(values);
    }

    public static OptimizedString valueOf(final char[] chars) {
        return new OptimizedString(chars);
    }

    public static OptimizedString valueOf(final Number number) {
        String string = number.toString();
        char[] values = new char[string.length()];
        for (int i = 0, wordsLength = string.length(); i < wordsLength; i++) {
            values[i] = string.charAt(i);
        }

        return new OptimizedString(values);
    }
}
