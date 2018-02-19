package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.impl.ds.InternalDataStore;
import com.digitalocean.sdk.impl.resource.BooleanProperty;
import com.digitalocean.sdk.impl.resource.IntegerProperty;
import com.digitalocean.sdk.impl.resource.LongProperty;
import com.digitalocean.sdk.impl.resource.Property;
import com.digitalocean.sdk.impl.resource.ResourceReference;
import com.digitalocean.sdk.impl.resource.StringProperty;
import com.digitalocean.sdk.resource.Resource;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.impl.resource.AbstractInstanceResource;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

import java.util.Map;

public class DefaultDroplet extends AbstractInstanceResource<Droplet> implements Droplet {

    private final static LongProperty idProperty = new LongProperty("id");
    private final static StringProperty nameProperty = new StringProperty("name");
    private final static LongProperty memoryProperty = new LongProperty("memory");
    private final static IntegerProperty vcpusProperty = new IntegerProperty("vcpus");
    private final static IntegerProperty diskProperty = new IntegerProperty("disk");
    private final static BooleanProperty lockedProperty = new BooleanProperty("locked");
    private final static StringProperty statusProperty = new StringProperty("status");

    private final static StringProperty regionProperty = new StringProperty("region");
    private final static StringProperty sizeProperty = new StringProperty("size");
    private final static StringProperty imageProperty = new StringProperty("image");

    private final static ResourceReference<DropletRegion> dropletRegionProperty =
        new ResourceReference("region", DropletRegion.class, false);

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(
        idProperty, nameProperty, memoryProperty, vcpusProperty, diskProperty, lockedProperty, statusProperty,
        regionProperty, sizeProperty, imageProperty, dropletRegionProperty
    );

    public DefaultDroplet(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultDroplet(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }


    @Override
    public Long getId() {
        return getLongProperty(idProperty);
    }

    @Override
    public String getName() {
        return getString(nameProperty);
    }

    @Override
    public Long getMemory() {
        return getLongProperty(memoryProperty);
    }

    @Override
    public Integer getVcpus() {
        return getIntProperty(vcpusProperty);
    }

    @Override
    public Integer getDisk() {
        return getIntProperty(diskProperty);
    }

    @Override
    public Boolean getLocked() {
        return getBoolean(lockedProperty);
    }

    @Override
    public String getStatus() {
        return getString(statusProperty);
    }

    @Override
    public String getRegion() {
        return getString(regionProperty);
    }

    @Override
    public String getSize() {
        return getString(sizeProperty);
    }

    @Override
    public String getImage() {
       return getString(imageProperty);
    }

    @Override
    public DropletRegion getDropletRegion() {
        return getResourceProperty(dropletRegionProperty);
    }

    @Override
    public Droplet setId(Long id) {
        setProperty(idProperty, id);
        return this;
    }

    @Override
    public Droplet setName(String name) {
        setProperty(nameProperty, name);
        return this;
    }

    @Override
    public Droplet setMemory(Long memory) {
        setProperty(memoryProperty, memory);
        return this;
    }

    @Override
    public Droplet setVcpus(Integer vcpus) {
        setProperty(vcpusProperty, vcpus);
        return this;
    }

    @Override
    public Droplet setDisk(Integer disk) {
        setProperty(diskProperty, disk);
        return this;
    }

    @Override
    public Droplet setLocked(Boolean locked) {
        setProperty(lockedProperty, locked);
        return this;
    }

    @Override
    public Droplet setStatus(String status) {
        setProperty(statusProperty, status);
        return this;
    }

    @Override
    public Droplet setRegion(String region) {
        setProperty(regionProperty, region);
        return this;
    }

    @Override
    public Droplet setSize(String size) {
        setProperty(sizeProperty, size);
        return this;
    }

    @Override
    public Droplet setImage(String image) {
        setProperty(imageProperty, image);
        return this;
    }

    @Override
    public Droplet setDropletRegion(DropletRegion dropletRegion) {
        setProperty(dropletRegionProperty, dropletRegion);
        return this;
    }

    @Override
    public Class<? extends Resource> getResourceClass() {
        return Droplet.class;
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }
}
