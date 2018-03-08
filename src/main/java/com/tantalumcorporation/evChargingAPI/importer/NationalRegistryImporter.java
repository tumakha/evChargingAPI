package com.tantalumcorporation.evChargingAPI.importer;

import static com.tantalumcorporation.evChargingAPI.domain.Provider.NATIONAL_REGISTRY;
import static java.lang.String.format;
import static java.util.Arrays.asList;

import com.tantalumcorporation.evChargingAPI.domain.ChargePoint;
import com.tantalumcorporation.evChargingAPI.domain.Provider;
import com.tantalumcorporation.evChargingAPI.service.ChargePointService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yuriy Tumakha
 */
@Component("nationalRegistryImporter")
public class NationalRegistryImporter implements DataImporter {

  private static final Log LOG = LogFactory.getLog(NationalRegistryImporter.class);
  private static final String CSV_SEPARATOR = ",";
  private static final String CHARGE_DEVICE_ID = "chargeDeviceID";
  private static final String NAME = "name";
  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";

  @Autowired
  private ChargePointService chargePointService;

  @Override
  public Provider getProvider() {
    return NATIONAL_REGISTRY;
  }

  @Override
  public void importFile(String url) throws IOException {
    try (Scanner scanner = new Scanner(new URL(url).openStream())) {
      String firstLine = scanner.nextLine();
      List<String> headers = asList(firstLine.split(CSV_SEPARATOR));
      int idPos = getColumnPosition(CHARGE_DEVICE_ID, headers);
      int namePos = getColumnPosition(NAME, headers);
      int latPos = getColumnPosition(LATITUDE, headers);
      int lonPos = getColumnPosition(LONGITUDE, headers);

      ChargePoint lastChargePoint = null;
      while (scanner.hasNextLine()) {
        try {
          String[] columns = scanner.nextLine().split(CSV_SEPARATOR);
          lastChargePoint = buildPoint(columns[idPos], columns[namePos], columns[latPos], columns[lonPos]);
          ChargePoint existingPoint = chargePointService
              .findByProviderAndDeviceId(getProvider(), lastChargePoint.getDeviceId());
          if (existingPoint == null) {
            chargePointService.createPoint(lastChargePoint);
          }
        } catch (Exception e) {
          LOG.error("Import Exception. Last " + lastChargePoint, e);
        }
      }
    }

  }

  private ChargePoint buildPoint(String deviceId, String name, String latitude, String longitude) {
    ChargePoint chargePoint = new ChargePoint();
    chargePoint.setProvider(getProvider());
    chargePoint.setDeviceId(deviceId.trim());
    chargePoint.setName(name.trim());
    chargePoint.setLatitude(Double.valueOf(latitude.trim()));
    chargePoint.setLongitude(Double.valueOf(longitude.trim()));
    return chargePoint;
  }

  private int getColumnPosition(String name, List<String> headers) {
    int pos = headers.indexOf(name);
    if (pos == -1) {
      throw new IllegalArgumentException(format("Column '%s' not found ind CSV file.", name));
    }
    return pos;
  }

}
