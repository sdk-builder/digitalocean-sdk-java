package com.okta.quickstart;

import com.okta.sdk.authc.credentials.ClientCredentials;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.AuthenticationScheme;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.droplet.Droplet;

public class QuickStart {

    public static void main(String[] args) {

        ClientCredentials cc = new TokenClientCredentials(System.getenv("digitalocean.client.token"));
        Client client =  Clients.builder()
            .setClientCredentials(cc)
            .setAuthenticationScheme(AuthenticationScheme.Bearer)
            .setOrgUrl("https://api.digitalocean.com")
            .build();

        Droplet droplet = client.getDroplet("58635411");
        System.out.println("droplet: " + droplet.getName());
    }
}
