package com.tantalumcorporation.evChargingAPI.service;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.domain.Provider;
import java.util.List;

/**
 * @author Yuriy Tumakha
 */
public interface ChargePointService {

  List<ChargePoint> findNearestPoints(Double latitude, Double longitude, Integer results);

  ChargePoint createPoint(ChargePoint point);

  ChargePoint updatePoint(ChargePoint point);

  ChargePoint findPoint(Long pointId);

  ChargePoint findByProviderAndDeviceId(Provider provider, String deviceId);

}
