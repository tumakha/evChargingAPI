package com.tantalumcorporation.evChargingAPI.rest.controller;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.importer.ImporterRepository;
import com.tantalumcorporation.evChargingAPI.rest.model.ChargePointChanges;
import com.tantalumcorporation.evChargingAPI.rest.model.StatusResponse;
import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tantalumcorporation.evChargingAPI.rest.model.ImportParams;

/**
 * @author Yuriy Tumakha
 */
@RestController
@RequestMapping(value = "/charge_points", produces = {APPLICATION_JSON_VALUE})
public class ChargePointController {

  private static final Log LOG = LogFactory.getLog(ChargePointController.class);

  @Autowired
  private ChargePointService chargePointService;

  @Autowired
  private ImporterRepository importerRepository;

  @RequestMapping(method = GET)
  public List<ChargePoint> findPoints(
      @RequestParam("latitude") Double latitude,
      @RequestParam("longitude") Double longitude,
      @RequestParam("results") Integer results) {
    return chargePointService.findNearestPoints(latitude, longitude, results);
  }

  @RequestMapping(value = "/{pointId}", method = GET)
  public ResponseEntity<ChargePoint> getPoint(@PathVariable("pointId") Long pointId) {
    return ofNullable(chargePointService.findPoint(pointId))
        .map(point -> new ResponseEntity<>(point, OK))
        .orElse(new ResponseEntity<>(NOT_FOUND));
  }

  @RequestMapping(value = "/{pointId}", method = PATCH)
  public ResponseEntity<ChargePoint> updatePoint(@PathVariable("pointId") Long pointId,
      @RequestBody ChargePointChanges chargePointChanges) {
    ChargePoint point = chargePointService.findPoint(pointId);
    if (point == null) {
      return new ResponseEntity<>(NOT_FOUND);
    } else {
      BeanUtils.copyProperties(chargePointChanges, point);
      ChargePoint updatedPoint = chargePointService.updatePoint(point);
      return new ResponseEntity<>(updatedPoint, OK);
    }
  }

  @RequestMapping(value = "/import", method = POST)
  public ResponseEntity<StatusResponse> importPoints(@RequestBody ImportParams importParams) {
      try {
        return importerRepository.getDataImporter(importParams.getProvider())
            .map(importer -> {
              importer.importFile(importParams.getFile());
              return getStatusResponse("Ok", OK);
            })
            .orElse(getStatusResponse("Unknown Provider", BAD_REQUEST));

      } catch (Exception e) {
        LOG.error("Import failed", e);
        return getStatusResponse("Import failed", INTERNAL_SERVER_ERROR);
      }
  }

  private ResponseEntity<StatusResponse> getStatusResponse(String message, HttpStatus httpStatus) {
    return new ResponseEntity<>(new StatusResponse(message), httpStatus);
  }

}
