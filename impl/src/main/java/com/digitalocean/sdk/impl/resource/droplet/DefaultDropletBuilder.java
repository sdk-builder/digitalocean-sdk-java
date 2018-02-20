package com.digitalocean.sdk.impl.resource.droplet;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletBuilder;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

public class DefaultDropletBuilder implements DropletBuilder {

    private String name;
    private String size;
    private String image;
    private String regionSlug;

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
    public Droplet buildAndCreate(Client client) {

        DropletRegion dr = client.instantiate(DropletRegion.class);
        dr.setSlug(this.regionSlug);

        Droplet droplet = client.instantiate(Droplet.class)
            .setName(this.name)
            .setDropletRegion(dr)
            .setSize(this.size)
            .setImage(this.image);

        return client.createDroplet(droplet);
    }
}
