package com.tantalumcorporation.evChargingAPI.repository;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yuriy Tumakha.
 */
@Component("chargePointRepository")
@Transactional
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {

  @Query("SELECT cp FROM ChargePoint cp ORDER BY " +
      " (6371 * acos(cos(radians(:latitude)) * cos(radians(cp.latitude)) * "
      + " cos(radians(cp.longitude) - radians(:longitude)) "
      + " + sin(radians(:latitude)) * sin(radians(cp.latitude))))"
  )
  List<ChargePoint> findNearestPoints(
      @Param("latitude") Double latitude,
      @Param("longitude") Double longitude,
      Pageable pageable
  );

}
