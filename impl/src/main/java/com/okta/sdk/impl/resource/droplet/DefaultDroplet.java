package com.okta.sdk.impl.resource.droplet;

import com.okta.sdk.impl.ds.InternalDataStore;
import com.okta.sdk.impl.resource.AbstractInstanceResource;
import com.okta.sdk.impl.resource.BooleanProperty;
import com.okta.sdk.impl.resource.IntegerProperty;
import com.okta.sdk.impl.resource.LongProperty;
import com.okta.sdk.impl.resource.Property;
import com.okta.sdk.impl.resource.StringProperty;
import com.okta.sdk.resource.Resource;
import com.okta.sdk.resource.droplet.Droplet;

import java.util.Map;

public class DefaultDroplet extends AbstractInstanceResource<Droplet> implements Droplet {

    private final static LongProperty idProperty = new LongProperty("id");
    private final static StringProperty nameProperty = new StringProperty("name");
    private final static LongProperty memoryProperty = new LongProperty("memory");
    private final static IntegerProperty vcpusProperty = new IntegerProperty("vcpus");
    private final static IntegerProperty diskProperty = new IntegerProperty("disk");
    private final static BooleanProperty lockedProperty = new BooleanProperty("locked");
    private final static StringProperty statusProperty = new StringProperty("status");

    private final static Map<String, Property> PROPERTY_DESCRIPTORS = createPropertyDescriptorMap(
        idProperty, nameProperty, memoryProperty, vcpusProperty, diskProperty, lockedProperty, statusProperty
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
    public Class<? extends Resource> getResourceClass() {
        return Droplet.class;
    }

    @Override
    public Map<String, Property> getPropertyDescriptors() {
        return PROPERTY_DESCRIPTORS;
    }
}
