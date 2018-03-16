package org.kylecorp.util;

import org.kylecorp.util.exception.ParkingRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtil {
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public static OffsetDateTime parseDateTime(String dateTime){
        OffsetDateTime offsetDateTime;

        try{
            offsetDateTime = OffsetDateTime.parse(dateTime);
        }
        catch(DateTimeParseException e){
            throw new ParkingRuntimeException("Unable to parse date", e);
        }

        return offsetDateTime;
    }

    public static Integer formatTime24Hour(OffsetDateTime dateTime) {
        String timeStr = null;
        try {
            timeStr = TIME_FORMATTER.format(dateTime);
        } catch (DateTimeException e) {
            throw new ParkingRuntimeException("Unable to parse 24 hour time from " + dateTime);
        }
        Integer timeInt = Integer.parseInt(timeStr);
        return timeInt;

    }
}
