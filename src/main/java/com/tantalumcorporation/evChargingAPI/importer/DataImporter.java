package com.tantalumcorporation.evChargingAPI.importer;

import com.tantalumcorporation.evChargingAPI.domain.Provider;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Yuriy Tumakha
 */
public interface DataImporter {

  Provider getProvider();

  void importFile(String url) throws IOException, URISyntaxException;

}
