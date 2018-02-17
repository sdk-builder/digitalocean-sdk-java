package com.digitalocean.sdk.impl.http.authc;

import com.digitalocean.sdk.authc.credentials.ClientCredentials;
import com.digitalocean.sdk.impl.http.Request;
import com.digitalocean.sdk.impl.http.support.RequestAuthenticationException;
import com.digitalocean.sdk.lang.Assert;

public class BearerAuthenticator implements RequestAuthenticator {

    public static final String AUTHENTICATION_SCHEME = "Bearer";

    private final ClientCredentials clientCredentials;

    public BearerAuthenticator(ClientCredentials clientCredentials) {
        Assert.notNull(clientCredentials, "clientCredentials must be not be null.");
        this.clientCredentials = clientCredentials;
    }

    @Override
    public void authenticate(Request request) throws RequestAuthenticationException {
        request.getHeaders().set(AUTHORIZATION_HEADER, AUTHENTICATION_SCHEME + " " + clientCredentials.getCredentials());
    }

}
