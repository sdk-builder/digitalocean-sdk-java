package com.okta.quickstart;

import com.okta.sdk.authc.credentials.ClientCredentials;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.AuthenticationScheme;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.droplet.Droplet;
import com.okta.sdk.resource.log.LogEventList;

public class QuickStart {

    public static void main(String[] args) {

        Client client =  Clients.builder().build();

        LogEventList logs = client.getLogs();
        logs.stream()
            .filter(d -> d.getActor().getAlternateId().equals("system@okta.com"))
            .forEach(d -> System.out.println(d.getDisplayMessage()));

        ClientCredentials cc = new TokenClientCredentials(System.getenv("digitalocean.client.token"));
        Client client2 = Clients.builder()
            .setClientCredentials(cc)
            .setAuthenticationScheme(AuthenticationScheme.Bearer)
            .setOrgUrl("https://api.digitalocean.com")
            .build();
        Droplet droplet = client2.getDroplet("58635411");

        System.out.println("droplet: " + droplet.getName());
    }
}
