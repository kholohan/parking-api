package org.kylecorp.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.kylecorp.api.Rates;
import org.kylecorp.service.ParkingService;
import org.kylecorp.util.TimeUtil;
import org.kylecorp.util.exception.ParkingRuntimeException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.OffsetDateTime;
import java.util.Optional;

@Resource
@Api(value = "/parking", description = "Parking API")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/parking")
public class ParkingResource {


    private ParkingService parkingService;

    @Inject
    public ParkingResource(ParkingService parkingService) {
        this.parkingService = parkingService;
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
