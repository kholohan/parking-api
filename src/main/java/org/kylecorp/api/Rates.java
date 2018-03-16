package org.kylecorp.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.kylecorp.util.serialize.RatesDeserializer;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Optional;

@JsonDeserialize(using = RatesDeserializer.class)
public class Rates {

    HashMap<DayOfWeek, RateInfo> rates = new HashMap<DayOfWeek, RateInfo>();

    public HashMap<DayOfWeek, RateInfo> getRates() {
        return rates;
    }

    public void setRates(HashMap<DayOfWeek, RateInfo> rates) {
        this.rates = rates;
    }

    public Optional<Integer> getRate(DayOfWeek dayOfWeek, Integer start, Integer stop) {
        RateInfo rateInfo = rates.get(dayOfWeek);

        if (rateInfo != null) {
            return rateInfo.getRate(start, stop);
        } else {
            return Optional.empty();
        }

    }

}
