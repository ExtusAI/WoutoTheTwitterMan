package xyz.sethy.woutobot.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seth on 31/08/2017.
 */
public class TextResult {
    private final String result;
    private final List<TextError> errors;

    public TextResult() {
        this.result = "";
        this.errors = new ArrayList<>();
    }

    public String getResult() {
        return result;
    }

    public List<TextError> getErrors() {
        return errors;
    }
}
