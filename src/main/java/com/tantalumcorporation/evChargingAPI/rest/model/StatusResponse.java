package com.tantalumcorporation.evChargingAPI.rest.model;

/**
 * @author Yuriy Tumakha
 */
public class StatusResponse {

  private String status;

  public StatusResponse() {

  }

  public StatusResponse(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
