package com.digitalocean.sdk.resource.droplet;

import com.digitalocean.sdk.resource.Resource;

import java.util.List;

public interface DropletRegion extends Resource {

    String getName();
    String getSlug();
    List<String> getSizes();
    List<String> getFeatures();
    Boolean isAvailable();

    DropletRegion setName(String name);
    DropletRegion setSlug(String slug);
    DropletRegion setSizes(List<String> sizes);
    DropletRegion setFeatures(List<String> setFeatures);
    DropletRegion setAvailable(Boolean available);
}
