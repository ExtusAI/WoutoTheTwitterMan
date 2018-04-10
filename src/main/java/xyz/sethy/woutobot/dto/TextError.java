package xyz.sethy.woutobot.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seth on 31/08/2017.
 */
public class TextError {
    private final String id;
    private final String offset;
    private final String length;
    private final String bad;
    private final List<String> better;

    public TextError() {
        this.id = "";
        this.offset = "";
        this.length = "";
        this.bad = "";
        this.better = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getOffset() {
        return offset;
    }

    public String getLength() {
        return length;
    }

    public String getBad() {
        return bad;
    }

    public List<String> getBetter() {
        return better;
    }
}
