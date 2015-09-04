package io.ckl.challenge.max.util;

import com.bluelinelabs.logansquare.typeconverters.DateTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * Used to parse the Json's Date field to java.util.Date object
 *
 * Created by Max Jr on 03/09/2015.
 */
public class JsonDateConverter extends DateTypeConverter {
    private DateFormat dateFormat;

    public JsonDateConverter() {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

}
