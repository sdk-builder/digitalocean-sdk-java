package com.digitalocean.quickstart;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.client.Clients;
import com.digitalocean.sdk.resource.droplet.Droplet;
import com.digitalocean.sdk.resource.droplet.DropletRegion;

public class QuickStart {

    public static void main(String[] args) {

        Client client =  Clients.builder().build();

        DropletRegion dr = client.instantiate(DropletRegion.class);
        dr.setSlug("nyc3");

        Droplet droplet = client.instantiate(Droplet.class)
            .setName("micah-test")
            .setDropletRegion(dr)
            .setSize("s-1vcpu-1gb")
            .setImage("ubuntu-16-04-x64");

        droplet = client.createDroplet(droplet);

        System.out.println("droplet id: " + droplet.getId() + ", name: " + droplet.getName());
        System.out.println("region: " + droplet.getDropletRegion().getSlug());
    }
}
