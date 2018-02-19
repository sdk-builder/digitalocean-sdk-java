package com.digitalocean.quickstart;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.client.Clients;
import com.digitalocean.sdk.resource.droplet.Droplet;

public class QuickStart {

    public static void main(String[] args) {

        Client client =  Clients.builder().build();

        Droplet droplet = client.instantiate(Droplet.class)
            .setName("micah-test")
            .setRegion("nyc3")
            .setSize("s-1vcpu-1gb")
            .setImage("ubuntu-16-04-x64");

        droplet = client.createDroplet(droplet);

        System.out.println("droplet id: " + droplet.getId() + ", name: " + droplet.getName());
        System.out.println("region: " + droplet.getRegion());
    }
}
