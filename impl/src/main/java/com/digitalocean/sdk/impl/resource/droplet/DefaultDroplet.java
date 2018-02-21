package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.impl.ds.InternalDataStore;
import com.digitalocean.sdk.impl.resource.BooleanProperty;
import com.digitalocean.sdk.impl.resource.IntegerProperty;
import com.digitalocean.sdk.impl.resource.ListProperty;
import com.digitalocean.sdk.impl.resource.LongProperty;
import com.digitalocean.sdk.impl.resource.Property;
import com.digitalocean.sdk.impl.resource.ResourceReference;
import com.digitalocean.sdk.impl.resource.StringProperty;
import com.digitalocean.sdk.resource.Resource;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.impl.resource.AbstractInstanceResource;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

import java.util.List;
import java.util.Map;

public class DefaultDroplet extends AbstractInstanceResource<Droplet> implements Droplet {

    final static LongProperty idProperty = new LongProperty("id");
    final static StringProperty nameProperty = new StringProperty("name");
    final static LongProperty memoryProperty = new LongProperty("memory");
    final static IntegerProperty vcpusProperty = new IntegerProperty("vcpus");
    final static IntegerProperty diskProperty = new IntegerProperty("disk");
    final static BooleanProperty lockedProperty = new BooleanProperty("locked");
    final static StringProperty statusProperty = new StringProperty("status");

    final static StringProperty sizeProperty = new StringProperty("size");
    final static StringProperty imageProperty = new StringProperty("image");

    final static ListProperty tagsProperty = new ListProperty("tags");

    final static ResourceReference<DropletRegion> dropletRegionProperty =
        new ResourceReference("region", DropletRegion.class, false);

    final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(
        idProperty, nameProperty, memoryProperty, vcpusProperty, diskProperty, lockedProperty,
        statusProperty, sizeProperty, imageProperty, dropletRegionProperty
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
    public String getSize() {
        return getString(sizeProperty);
    }

    @Override
    public String getImage() {
       return getString(imageProperty);
    }

    @Override
    public List<String> getTags() {
        return getListProperty(tagsProperty);
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
    public Droplet setTags(List<String> tags) {
        setProperty(tagsProperty, tags);
        return this;
    }

    @Override
    public Droplet setDropletRegion(DropletRegion dropletRegion) {
        setProperty(dropletRegionProperty, dropletRegion);
        return this;
    }

    @Override
    public void delete() {
        getDataStore().delete(this);
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
