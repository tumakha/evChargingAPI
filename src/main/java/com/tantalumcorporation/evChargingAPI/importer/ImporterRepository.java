package com.tantalumcorporation.evChargingAPI.importer;

import static com.tantalumcorporation.evChargingAPI.domain.Provider.NATIONAL_REGISTRY;
import static java.util.Optional.ofNullable;

import com.tantalumcorporation.evChargingAPI.domain.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yuriy Tumakha
 */
@Component("importerRepository")
public class ImporterRepository {

  @Autowired
  private NationalRegistryImporter nationalRegistryImporter;

  private Map<Provider, DataImporter> importers = new HashMap<>();

  @PostConstruct
  public void init() {
    importers.put(NATIONAL_REGISTRY, nationalRegistryImporter);
  }

  public Optional<DataImporter> getDataImporter(Provider provider) {
    return ofNullable(importers.get(provider));
  }

}
