package com.tantalumcorporation.evChargingAPI.service.impl;

import static com.tantalumcorporation.evChargingAPI.domain.Provider.NATIONAL_REGISTRY;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Yuriy Tumakha
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargePointServiceImplTest {

  private static final double DELTA = 0.00001;
  private static final double LATITUDE = 51.0;
  private static final Long POINT_ID = 11L;
  private static final String POINT_NAME = "New Point Name";
  private static boolean initialized;

  @Autowired
  private ChargePointService chargePointService;

  @Before
  public void setup() {
    if (!initialized) {
      range(1, 30).mapToObj(this::generatePoint).forEach(chargePointService::createPoint);
      initialized = true;
    }
  }

  private ChargePoint generatePoint(Integer num) {
    ChargePoint chargePoint = new ChargePoint();
    chargePoint.setProvider(NATIONAL_REGISTRY);
    chargePoint.setDeviceId(NATIONAL_REGISTRY.name() + num);
    chargePoint.setName("Point" + num);
    chargePoint.setLatitude(LATITUDE);
    chargePoint.setLongitude((double) num / 100);
    return chargePoint;
  }

  @Test
  public void testFindNearestPoints() {
    int size = 6;
    List<ChargePoint> points = chargePointService.findNearestPoints(LATITUDE, 0.222, size);
    assertEquals("Points list has wrong size.", size, points.size());
    assertEquals("Wrong Longitude", 0.22, points.get(0).getLongitude(), DELTA);
    assertEquals("Wrong Longitude", 0.23, points.get(1).getLongitude(), DELTA);
    assertEquals("Wrong Longitude", 0.21, points.get(2).getLongitude(), DELTA);
    assertEquals("Wrong Longitude", 0.24, points.get(3).getLongitude(), DELTA);
    assertEquals("Wrong Longitude", 0.20, points.get(4).getLongitude(), DELTA);
    assertEquals("Wrong Longitude", 0.25, points.get(5).getLongitude(), DELTA);
  }

  @Test
  public void testUpdatePoint() {
    ChargePoint dbChargePoint = chargePointService.findPoint(POINT_ID);
    Double latitude = dbChargePoint.getLatitude();
    Double longitude = dbChargePoint.getLongitude();

    // update point name
    dbChargePoint.setName(POINT_NAME);
    chargePointService.updatePoint(dbChargePoint);

    ChargePoint point = chargePointService.findPoint(POINT_ID);
    assertEquals("Wrong Id", POINT_ID, point.getId());
    assertEquals("Wrong Name", POINT_NAME, point.getName());
    assertEquals("Wrong Latitude", latitude, point.getLatitude());
    assertEquals("Wrong Longitude", longitude, point.getLongitude());
  }

}
