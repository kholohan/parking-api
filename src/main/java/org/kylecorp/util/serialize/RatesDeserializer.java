package org.kylecorp.util.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.google.common.collect.Range;
import io.swagger.util.Json;
import org.kylecorp.api.RateInfo;
import org.kylecorp.api.Rates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Iterator;

public class RatesDeserializer extends StdDeserializer<Rates>  {

    private static Logger logger = LoggerFactory.getLogger(RatesDeserializer.class);

    public RatesDeserializer(){
        this(null);
    }

    public RatesDeserializer(Class<?> vc) {
        super(vc);
    }

    private DayOfWeek getDayOfWeek(String day) {
        DayOfWeek dayOfWeek = null;

        switch (day) {
            case "mon":
                dayOfWeek = DayOfWeek.MONDAY;
                break;
            case "tues":
                dayOfWeek = DayOfWeek.TUESDAY;
                break;
            case "wed":
                dayOfWeek = DayOfWeek.WEDNESDAY;
                break;
            case "thurs":
                dayOfWeek = DayOfWeek.THURSDAY;
                break;
            case "fri":
                dayOfWeek = DayOfWeek.FRIDAY;
                break;
            case "sat":
                dayOfWeek = DayOfWeek.SATURDAY;
                break;
            case "sun":
                dayOfWeek = DayOfWeek.SUNDAY;
                break;
        }
        return dayOfWeek;
    }

    private Rates deserializeXml(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Rates rates = new Rates();

        for (; jp.getCurrentToken() != JsonToken.END_OBJECT; jp.nextToken()) {

            System.out.println(jp.getCurrentToken().asString());
        }
        return null;
    }

    private Rates deserializeJson(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException{
        Rates rates = new Rates();

        HashMap<DayOfWeek, RateInfo> map = new HashMap<DayOfWeek, RateInfo>();
        JsonNode node = jp.getCodec().readTree(jp);

        JsonNode ratesNode = node.get("rates");
        Iterator<JsonNode> rateIterator = ratesNode.elements();

        while (rateIterator.hasNext()) {
            JsonNode rateNode = rateIterator.next();
            String days = rateNode.get("days").textValue();
            String times = rateNode.get("times").textValue();
            Integer rate = rateNode.get("price").intValue();

            String[] daysArray = days.split(",");
            String[] timesArray = times.split("-");

            for (String day : daysArray) {
                DayOfWeek dayOfWeek = getDayOfWeek(day); //handle case where unknown
                String start = timesArray[0];
                String end = timesArray[1];
                Integer startInt = Integer.valueOf(start);
                Integer endInt = Integer.valueOf(end);

                Range range = Range.closedOpen(startInt, endInt); //(a..b]	{x | a < x <= b}

                RateInfo rateInfo = map.get(dayOfWeek);
                if (rateInfo == null) { //Create new entry if null
                    rateInfo = new RateInfo();
                }

                rateInfo.addRangeRate(range, rate);
                map.putIfAbsent(dayOfWeek, rateInfo);

            }

            rates.setRates(map);
        }
        return rates;
    }

    @Override
    public Rates deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Rates rates = new Rates();

        //Handle JSON and XML, kind of hacky
        if(jp instanceof FromXmlParser){//XML
            rates = deserializeXml(jp, ctxt);
        }
        else//JSON
        {
            rates = deserializeJson(jp, ctxt);
        }

        return rates;
    }
}
