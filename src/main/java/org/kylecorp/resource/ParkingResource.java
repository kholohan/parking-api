package org.kylecorp.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.kylecorp.api.Rates;
import org.kylecorp.service.ParkingService;
import org.kylecorp.util.TimeUtil;
import org.kylecorp.util.exception.ParkingRuntimeException;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Time;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Resource
@ManagedBean
@Api(value = "/parking", description = "Parking API")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/parking")
public class ParkingResource {

    @Inject
    private ParkingService parkingService;

    public ParkingResource() {
    }

    @ApiOperation(value = "Gets the parking rate for a given range of time", response = Response.class)
    @ApiResponse(code = 200, message = "Successful operation")
    @PUT
    @Path("/rate")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getRate(@ApiParam("Start date/time ISO 8601") @QueryParam("start") String start, @ApiParam("End date/time ISO 8601") @QueryParam("end") String end, Rates rates) throws ParkingRuntimeException {
        OffsetDateTime startDateTime = TimeUtil.parseDateTime(start);
        OffsetDateTime endDateTime = TimeUtil.parseDateTime(end);

         Optional<Integer> rateOp = parkingService.getRate(startDateTime, endDateTime, rates);

        if (rateOp.isPresent()) {
            return Response.ok().entity(rateOp.get()).build();
        }

        return Response.ok().entity("unavailable").build();
    }
}
