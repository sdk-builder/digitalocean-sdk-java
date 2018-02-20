package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.impl.ds.InternalDataStore;
import com.digitalocean.sdk.impl.resource.Property;
import com.digitalocean.sdk.impl.resource.StringProperty;

import java.util.Map;

public class InternalDroplet extends DefaultDroplet {

    private final static StringProperty regionProperty = new StringProperty("region");

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(
            idProperty, nameProperty, memoryProperty, vcpusProperty, diskProperty, lockedProperty,
            statusProperty, sizeProperty, imageProperty, dropletRegionProperty, regionProperty
    );

    public InternalDroplet(InternalDataStore dataStore) {
        super(dataStore);
    }

    public InternalDroplet(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    public String getRegion() {
        return getString(regionProperty);
    }

    public InternalDroplet setRegion(String region) {
        setProperty(regionProperty, region);
        return this;
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }

}
