package com.tantalumcorporation.evChargingAPI.importer;

import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yuriy Tumakha
 */
@Component("nationalRegistryImporter")
public class NationalRegistryImporter implements DataImporter {

  @Autowired
  private ChargePointService chargePointService;

  @Override
  public void importFile(String url) {
    System.out.println("URL = " + url);
  }

}
