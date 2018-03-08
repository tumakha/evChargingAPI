package com.tantalumcorporation.evChargingAPI.importer;

import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

import com.tantalumcorporation.evChargingAPI.domain.Provider;
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

  private Map<Provider, DataImporter> importers;

  @PostConstruct
  public void init() {
    importers = of(nationalRegistryImporter).collect(toMap(DataImporter::getProvider, identity()));
  }

  public Optional<DataImporter> getDataImporter(Provider provider) {
    return ofNullable(importers.get(provider));
  }

}
