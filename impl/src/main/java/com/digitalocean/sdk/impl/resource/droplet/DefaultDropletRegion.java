package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.impl.ds.InternalDataStore;
import com.digitalocean.sdk.impl.resource.AbstractInstanceResource;
import com.digitalocean.sdk.impl.resource.BooleanProperty;
import com.digitalocean.sdk.impl.resource.ListProperty;
import com.digitalocean.sdk.impl.resource.Property;
import com.digitalocean.sdk.impl.resource.StringProperty;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

import java.util.List;
import java.util.Map;

public class DefaultDropletRegion extends AbstractInstanceResource<DropletRegion> implements DropletRegion {

    private final static StringProperty nameProperty = new StringProperty("name");
    private final static StringProperty slugProperty = new StringProperty("slug");
    private final static BooleanProperty availableProperty = new BooleanProperty("available");
    private final static ListProperty sizesProperty = new ListProperty("sizes");
    private final static ListProperty featuresProperty = new ListProperty("features");

    public DefaultDropletRegion(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultDropletRegion(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap();

    @Override
    public String getName() {
        return getString(nameProperty);
    }

    @Override
    public String getSlug() {
        return getString(slugProperty);
    }

    @Override
    public List<String> getSizes() {
        return getListProperty(sizesProperty);
    }

    @Override
    public List<String> getFeatures() {
        return getListProperty(featuresProperty);
    }

    @Override
    public Boolean isAvailable() {
        return getBoolean(availableProperty);
    }

    @Override
    public DropletRegion setName(String name) {
        setProperty(nameProperty, name);
        return this;
    }

    @Override
    public DropletRegion setSlug(String slug) {
        setProperty(slugProperty, slug);
        return this;
    }

    @Override
    public DropletRegion setSizes(List<String> sizes) {
        setProperty(sizesProperty, sizes);
        return this;
    }

    @Override
    public DropletRegion setFeatures(List<String> features) {
        setProperty(featuresProperty, features);
        return null;
    }

    @Override
    public DropletRegion setAvailable(Boolean available) {
        setProperty(availableProperty, available);
        return this;
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }
}
