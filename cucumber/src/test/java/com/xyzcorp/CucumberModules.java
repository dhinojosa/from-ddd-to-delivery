package com.xyzcorp;

import com.google.inject.AbstractModule;

public class CucumberModules extends AbstractModule {
    @Override
    protected void configure() {
        bind(EmailService.class).to(EmailServiceStub.class);
        bind(MessageService.class).to(MessageServiceStub.class);
        bind(RequestService.class).to(RequestServiceStub.class);
    }
}
