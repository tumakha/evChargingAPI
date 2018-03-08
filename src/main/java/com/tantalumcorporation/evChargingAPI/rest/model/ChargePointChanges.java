package com.tantalumcorporation.evChargingAPI.rest.model;

import org.hibernate.validator.constraints.Range;

/**
 * @author Yuriy Tumakha
 */
public class ChargePointChanges {

  private String name;

  @Range(min = -90, max = 90)
  private Double latitude;

  @Range(min = -180, max = 180)
  private Double longitude;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

}
