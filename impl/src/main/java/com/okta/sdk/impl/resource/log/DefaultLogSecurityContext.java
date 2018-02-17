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
import com.okta.sdk.impl.resource.BooleanProperty;
import com.okta.sdk.impl.resource.IntegerProperty;
import com.okta.sdk.impl.resource.Property;
import com.okta.sdk.impl.resource.StringProperty;
import com.okta.sdk.resource.log.LogSecurityContext;

import java.util.Map;


/**
 * LogSecurityContext
 */
@javax.annotation.Generated(
        value = "com.okta.swagger.codegen.OktaJavaClientImplCodegen",
        date  = "2018-02-11T21:21:50.835-05:00")
public class DefaultLogSecurityContext extends AbstractResource implements LogSecurityContext {

    private final static IntegerProperty asNumberProperty = new IntegerProperty("asNumber");
    private final static StringProperty asOrgProperty = new StringProperty("asOrg");
    private final static StringProperty domainProperty = new StringProperty("domain");
    private final static BooleanProperty isProxyProperty = new BooleanProperty("isProxy");
    private final static StringProperty ispProperty = new StringProperty("isp");

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(asNumberProperty, asOrgProperty, domainProperty, isProxyProperty, ispProperty);

    public DefaultLogSecurityContext(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultLogSecurityContext(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }


    public Integer getAsNumber() {
        return  getIntProperty(asNumberProperty);
    }

    public String getAsOrg() {
        return  getString(asOrgProperty);
    }

    public String getDomain() {
        return  getString(domainProperty);
    }

    public Boolean getIsProxy() {
        return  getBoolean(isProxyProperty);
    }

    public String getIsp() {
        return  getString(ispProperty);
    }

}