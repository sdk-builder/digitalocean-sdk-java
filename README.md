## Beautiful Java SDKs for APIs

This project is used for a talk on beautiful SDK Design

### Run the Demo

The example demonstrates using an SDK design based on the one Lez Hazlewood created for Stormpath.

The SDK interacts with the [Digital Ocean](http://digitalocean.com) API.

*NOTE*: This is NOT a complete SDK and is not suitable for production use

To run the demo, you need a Digital Ocean account and you need to create an API token.

Create the file: `~/.digitalocean/digitalocean.yaml` with the following:

```
digitalocean:
  client:
    token: <your digital ocean api token>
    orgUrl: https://api.digitalocean.com
```

### Resources

Here are some links used in the talk:

* [Speaker Deck](beautiful_sdk_design_with_java.pdf)
* [HTTPie](https://github.com/jkbrzt/httpie)
* [Digital Ocean API docs](https://developers.digitalocean.com/documentation/v2)
* [Free Okta Org for Developers](https://developer.okta.com)
* [Okta Java SDK](https://github.com/okta/okta-sdk-java)
* [Okta Training](https://www.okta.com/services/training/)
* Micah on twitter: [@afitnerd](https://twitter.com/afitnerd)
* OktaDev on twitter: [@OktaDev](https://twitter.com/oktadev)
