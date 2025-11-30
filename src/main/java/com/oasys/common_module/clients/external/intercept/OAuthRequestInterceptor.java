package com.oasys.common_module.clients.external.intercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;


/*
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization",
                "Bearer " + oAuth2AuthorizedClientManager.authorize
                                (
OAuth2AuthorizeRequest.withClientRegistrationId("internal-client")
        .principal("internal")
                                                .build())
                        .getAccessToken()
                        .getTokenValue());
    }
*/


    @Override
    public void apply(RequestTemplate template) {

        OAuth2AuthorizeRequest request =
                OAuth2AuthorizeRequest
                        .withClientRegistrationId("internal-client")
                        .principal("internal")
                        .build();

        var client = oAuth2AuthorizedClientManager.authorize(request);

        template.header("Authorization", "Bearer " +
                client.getAccessToken().getTokenValue());
    }
}
