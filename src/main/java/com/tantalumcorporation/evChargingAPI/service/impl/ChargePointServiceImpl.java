package com.tantalumcorporation.evChargingAPI.service.impl;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.repository.ChargePointRepository;
import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Yuriy Tumakha
 */
@Service("chargePointService")
public class ChargePointServiceImpl implements ChargePointService {

  @Autowired
  private ChargePointRepository chargePointRepository;

  public List<ChargePoint> findNearestPoints(Double latitude, Double longitude, Integer results) {
    return chargePointRepository.findNearestPoints(latitude, longitude,
        new PageRequest(0, results));
  }

  public ChargePoint createPoint(ChargePoint point) {
    return chargePointRepository.save(point);
  }

  public ChargePoint updatePoint(ChargePoint point) {
    return chargePointRepository.save(point);
  }

  @Override
  public ChargePoint findPoint(Long pointId) {
    return chargePointRepository.findOne(pointId);
  }

}
