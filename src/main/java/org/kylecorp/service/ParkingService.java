package org.kylecorp.service;

import org.kylecorp.api.Rates;
import org.kylecorp.util.TimeUtil;
import org.kylecorp.util.exception.ParkingRuntimeException;
import org.kylecorp.util.serialize.RatesDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.Optional;

public class ParkingService {

    private static Logger logger = LoggerFactory.getLogger(ParkingService.class);

    public ParkingService() {}

    private void validate(OffsetDateTime start, OffsetDateTime end) {
        if (!start.isBefore(end)) {
            //ERROR start is not before end
            throw new ParkingRuntimeException("Start date " + start + " is not before " + end);
        }

        if (start.getDayOfYear() != end.getDayOfYear() && start.getYear() != end.getDayOfYear()) {
            //ERROR range is not for the same day
            throw new ParkingRuntimeException("Start date " + start + " and end date " + end + " do not fall within same day");
        }
    }

    public Optional<Integer> getRate(OffsetDateTime start, OffsetDateTime end, Rates rates) {
        //Validate that range is in same day,  and start < end
        validate(start, end);

        //Retrieve 24 hour hhMM from OffsetDateTime
        Integer startInt = TimeUtil.formatTime24Hour(start);
        Integer endInt = TimeUtil.formatTime24Hour(end);

        DayOfWeek dayOfWeek = start.getDayOfWeek();
        Optional<Integer> rateOp = rates.getRate(dayOfWeek, startInt, endInt);
        if(!rateOp.isPresent()){
            logger.warn("No rate could be found for " + start + " and " + end);
        }

        return rateOp;
    }


}
