package com.tantalumcorporation.evChargingAPI.service;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import java.util.List;

/**
 * @author Yuriy Tumakha
 */
public interface ChargePointService {

  List<ChargePoint> findNearestPoints(Double latitude, Double longitude, Integer results);

}
