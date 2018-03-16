package org.kylecorp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kylecorp.api.Rates;
import org.kylecorp.resource.ParkingResource;
import org.kylecorp.service.ParkingService;
import org.kylecorp.util.TimeUtil;
import org.kylecorp.util.exception.ParkingRuntimeException;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class ParkingResourceIntTest {
    /*

    private ParkingResource parkingResource = new ParkingResource();
    private Rates rates1;
    private Rates rates2;

    public ParkingResourceIntTest(){
        init();
    }

    public void init(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            String rates1Str = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("rates.json").toURI())));
            String rates2Str = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("rates2.json").toURI())));

            rates1 = objectMapper.readValue(rates1Str, Rates.class);
            rates2 = objectMapper.readValue(rates2Str, Rates.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testSuccessful(){
        String start = "2015-07-01T07:00:00Z";
        String end = "2015-07-01T12:00:00Z";

        Response response = parkingResource.getRate(start, end, rates1);

        assertNotNull(response);
        assertTrue(response.hasEntity());
        assertEquals(response.getEntity()), 200);
    }

    @Test
    public void testSuccessful2(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2015-07-04T07:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2015-07-04T12:00:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates1);

        assertTrue(rate.isPresent());
        assertEquals(rate.get().intValue(), 2000);
    }

    @Test
    public void testSuccessful3(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2018-03-15T09:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2018-03-15T20:00:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);

        assertTrue(rate.isPresent());
        assertEquals(rate.get().intValue(), 1500);
    }

    @Test
    public void testSuccessful4(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2018-03-14T01:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2018-03-14T04:50:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);

        assertTrue(rate.isPresent());
        assertEquals(rate.get().intValue(), 1000);
    }

    @Test
    public void testSuccessfulNotAvailable(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2015-07-04T07:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2015-07-04T20:00:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates1);

        assertFalse(rate.isPresent());
    }

    @Test
    public void testSuccessfulNotAvailable2(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2018-03-15T08:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2018-03-15T20:00:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);

        assertFalse(rate.isPresent());
    }

    @Test(expected = ParkingRuntimeException.class)
    public void testFailureDateRange(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2018-03-15T08:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("2017-03-15T20:00:00Z");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);
    }

    @Test(expected = ParkingRuntimeException.class)
    public void testFailureBadDate(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("2018-03-15T08:00:00Z");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("s");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);
    }

    @Test(expected = ParkingRuntimeException.class)
    public void testFailureBadDate2(){
        OffsetDateTime startDateTime = TimeUtil.parseDateTime("22131sasdasd");
        OffsetDateTime endDateTime = TimeUtil.parseDateTime("s123214");

        Optional<Integer> rate = parkingResource.getRate(startDateTime, endDateTime, rates2);
    }
    */

}
