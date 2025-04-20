package com.xyzcorp;


import com.google.inject.Singleton;

@Singleton
public class RequestServiceStub implements RequestService {
    @Override
    public void submit(ServiceRequest newService) {

    }
}
