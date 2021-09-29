package com.pawbla.project.home.parsers;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractParser implements ParserInterface {

    private Map<String, String> parsed;

    public AbstractParser() {
        parsed = new HashMap<String, String>();
    }

    protected void addParsed(Values key, String value) {
        parsed.put(key.getValue(), value);
    }

    @Override
    public Map<String, String> getParsed() {
        return parsed;
    }

    public String getRoundedDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(value).toString();
    }
}
