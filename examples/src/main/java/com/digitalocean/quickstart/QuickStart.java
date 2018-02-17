package com.digitalocean.quickstart;

import com.digitalocean.sdk.client.Client;
import com.digitalocean.sdk.client.Clients;
import com.digitalocean.sdk.resource.droplet.Droplet;

public class QuickStart {

    public static void main(String[] args) {

        Client client =  Clients.builder().build();
        Droplet droplet = client.getDroplet("58635411");
        System.out.println("droplet: " + droplet.getName());
    }
}
