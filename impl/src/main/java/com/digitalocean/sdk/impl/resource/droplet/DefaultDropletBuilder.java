package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletBuilder;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

import java.util.ArrayList;
import java.util.List;

public class DefaultDropletBuilder implements DropletBuilder {

    private String name;
    private String size;
    private String image;
    private String regionSlug;
    private List<String> tags = new ArrayList();

    @Override
    public DropletBuilder setRegionSlug(String regionSlug) {
        this.regionSlug = regionSlug;
        return this;
    }

    @Override
    public DropletBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public DropletBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    @Override
    public DropletBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public DropletBuilder addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    @Override
    public Droplet build(Client client) {
        DropletRegion dr = client.instantiate(DropletRegion.class);
        dr.setSlug(this.regionSlug);

        return client.instantiate(Droplet.class)
            .setName(this.name)
            .setDropletRegion(dr)
            .setSize(this.size)
            .setImage(this.image)
            .setTags(tags);
    }

    @Override
    public Droplet buildAndCreate(Client client) {

        Droplet droplet = build(client);
        return client.createDroplet(droplet);
    }
}
