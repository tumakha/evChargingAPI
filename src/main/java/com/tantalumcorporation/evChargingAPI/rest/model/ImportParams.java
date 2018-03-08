package com.tantalumcorporation.evChargingAPI.rest.model;

import com.tantalumcorporation.evChargingAPI.domain.Provider;

/**
 * @author Yuriy Tumakha
 */
public class ImportParams {

  private Provider provider;

  private String file;

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

}
