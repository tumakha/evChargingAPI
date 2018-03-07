package com.tantalumcorporation.evChargingAPI.service.impl;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.repository.ChargePointRepository;
import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yuriy Tumakha
 */
public class ChargePointServiceImpl implements ChargePointService {

  @Autowired
  private ChargePointRepository chargePointRepository;

  public List<ChargePoint> findNearestPoints(Double latitude, Double longitude, Integer results) {
    return chargePointRepository.findAll();
  }

  public void createPoint(ChargePoint point) {
    chargePointRepository.save(point);
  }

  public void createPoints(List<ChargePoint> points) {
    chargePointRepository.save(points);
  }

  public void updatePoint(ChargePoint point) {
    chargePointRepository.save(point);
  }

}
