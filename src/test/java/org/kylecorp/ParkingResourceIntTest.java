package org.kylecorp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;
import org.kylecorp.api.Rates;
import org.kylecorp.resource.ParkingResource;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class ParkingResourceIntTest extends HK2Runner {

    @Inject
    private ParkingResource parkingResource;

    private Rates rates1;
    private Rates rates2;

    public ParkingResourceIntTest() {
        init();
    }

    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String rates1Str = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("rates.json").toURI())));
            String rates2Str = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("rates2.json").toURI())));

            rates1 = objectMapper.readValue(rates1Str, Rates.class);
            rates2 = objectMapper.readValue(rates2Str, Rates.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSuccessful() {
        String start = "2015-07-01T07:00:00Z";
        String end = "2015-07-01T12:00:00Z";

        Response response = parkingResource.getRate(start, end, rates1);

        assertNotNull(response);
        assertTrue(response.hasEntity());
        Integer val = (Integer) response.getEntity();
        assertEquals(val.intValue(), 1500);
    }


    @Test
    public void testSuccessful2() {
        String start = "2015-07-04T07:00:00Z";
        String end = "2015-07-04T12:00:00Z";

        Response response = parkingResource.getRate(start, end, rates1);

        assertNotNull(response);
        assertTrue(response.hasEntity());
        Integer val = (Integer) response.getEntity();
        assertEquals(val.intValue(), 2000);
    }

    @Test
    public void testSuccessful3() {
        String start = "2018-03-15T09:00:00Z";
        String end = "2018-03-15T20:00:00Z";

        Response response = parkingResource.getRate(start, end, rates1);

        assertNotNull(response);
        assertTrue(response.hasEntity());
        String val = (String) response.getEntity();
        assertEquals(val, "unavailable");
    }


}
