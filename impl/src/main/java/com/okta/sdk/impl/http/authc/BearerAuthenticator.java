package com.okta.sdk.impl.http.authc;

import com.okta.sdk.authc.credentials.ClientCredentials;
import com.okta.sdk.impl.http.Request;
import com.okta.sdk.impl.http.support.RequestAuthenticationException;
import com.okta.sdk.lang.Assert;

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
