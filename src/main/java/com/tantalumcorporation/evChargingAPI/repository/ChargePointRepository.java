package com.tantalumcorporation.evChargingAPI.repository;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yuriy Tumakha.
 */
@Component("chargePointRepository")
@Transactional
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {

}
