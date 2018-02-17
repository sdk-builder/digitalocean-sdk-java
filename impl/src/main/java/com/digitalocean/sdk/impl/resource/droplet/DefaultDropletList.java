package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.impl.ds.InternalDataStore;
import com.digitalocean.sdk.impl.resource.AbstractCollectionResource;
import com.digitalocean.sdk.impl.resource.ArrayProperty;
import com.digitalocean.sdk.impl.resource.Property;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletList;

import java.util.Map;

public class DefaultDropletList extends AbstractCollectionResource<Droplet> implements DropletList {

    private static final ArrayProperty<Droplet> DROPLETS =
        new ArrayProperty(AbstractCollectionResource.ITEMS_PROPERTY_NAME, Droplet.class);
    private static final Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(DROPLETS);

    public DefaultDropletList(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultDropletList(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    public DefaultDropletList(InternalDataStore dataStore, Map<String, Object> properties, Map<String, Object> queryParams) {
        super(dataStore, properties, queryParams);
    }

    @Override
    protected Class<Droplet> getItemType() {
        return Droplet.class;
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }
}
