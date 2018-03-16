package org.kylecorp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.hk2.testing.junit.HK2Runner;
import org.kylecorp.api.Rates;
import org.kylecorp.resource.ParkingResource;
import org.kylecorp.service.ParkingService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceUnitTest extends HK2Runner {

    @Inject
    ParkingResource parkingResource;

    @Mock //Doesn't appear to be working
    ParkingService parkingService;// = mock(ParkingService.class);


    @Test
    public void testSuccessful() {
        String start = "2018-03-14T01:00:00Z";
        String end = "2018-03-14T04:50:00Z";
        Rates rates = new Rates();
/*
        Mockito.when(parkingService.getRate(ArgumentMatchers.any(OffsetDateTime.class),
                ArgumentMatchers.any(OffsetDateTime.class),
                ArgumentMatchers.any(Rates.class))).thenReturn(Optional.of(1500));

        Response response = parkingResource.getRate(start, end, rates);
        String val = (String) response.getEntity();
        */
        //Integer intVal = Integer.valueOf(val);

        //assertEquals(intVal.intValue(), 1500);

    }
}
