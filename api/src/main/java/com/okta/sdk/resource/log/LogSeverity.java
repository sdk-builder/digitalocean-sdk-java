/*
 * Okta API
 * Allows customers to easily access the Okta API
 *
 * OpenAPI spec version: 0.13.0
 * Contact: devex-public@okta.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.okta.sdk.resource.log;




/**
 * Enum LogSeverity
 */
public enum LogSeverity {

  
  DEBUG("DEBUG"),
  
  INFO("INFO"),
  
  WARN("WARN"),
  
  ERROR("ERROR");

  private String value;

  LogSeverity(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
