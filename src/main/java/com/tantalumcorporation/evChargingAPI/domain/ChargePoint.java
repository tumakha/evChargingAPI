package com.tantalumcorporation.evChargingAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Yuriy Tumakha
 */
@Entity
@Table(name = "charge_point", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"provider", "deviceId"})
})
public class ChargePoint {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 32, nullable = false)
  private Provider provider;

  @Column(nullable = false)
  private String deviceId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Provider getProvider() {

    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ChargePoint{");
    sb.append("id=").append(id);
    sb.append(", provider=").append(provider);
    sb.append(", deviceId='").append(deviceId).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append('}');
    return sb.toString();
  }

}
