package com.okta.sdk.impl.resource;

public class LongProperty extends NonStringProperty<Long> {

    public LongProperty(String name) {
        super(name, Long.class);
    }
}
