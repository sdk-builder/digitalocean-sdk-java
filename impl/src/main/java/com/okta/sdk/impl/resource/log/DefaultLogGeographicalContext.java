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


package com.okta.sdk.impl.resource.log;

import com.okta.sdk.impl.ds.InternalDataStore;
import com.okta.sdk.impl.resource.AbstractResource;
import com.okta.sdk.impl.resource.Property;
import com.okta.sdk.impl.resource.ResourceReference;
import com.okta.sdk.impl.resource.StringProperty;
import com.okta.sdk.resource.log.LogGeographicalContext;
import com.okta.sdk.resource.log.LogGeolocation;

import java.util.Map;


/**
 * LogGeographicalContext
 */
@javax.annotation.Generated(
        value = "com.okta.swagger.codegen.OktaJavaClientImplCodegen",
        date  = "2018-02-11T21:21:50.835-05:00")
public class DefaultLogGeographicalContext extends AbstractResource implements LogGeographicalContext {

    private final static StringProperty cityProperty = new StringProperty("city");
    private final static StringProperty countryProperty = new StringProperty("country");
    private final static ResourceReference<LogGeolocation> geolocationProperty = new ResourceReference("geolocation", LogGeolocation.class, false);
    private final static StringProperty postalCodeProperty = new StringProperty("postalCode");
    private final static StringProperty stateProperty = new StringProperty("state");

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(cityProperty, countryProperty, geolocationProperty, postalCodeProperty, stateProperty);

    public DefaultLogGeographicalContext(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultLogGeographicalContext(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }


    public String getCity() {
        return  getString(cityProperty);
    }

    public String getCountry() {
        return  getString(countryProperty);
    }

    public LogGeolocation getGeolocation() {
        return  getResourceProperty(geolocationProperty);
    }

    public String getPostalCode() {
        return  getString(postalCodeProperty);
    }

    public String getState() {
        return  getString(stateProperty);
    }

}