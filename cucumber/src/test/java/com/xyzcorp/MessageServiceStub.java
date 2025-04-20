package com.xyzcorp;


import com.google.inject.Singleton;

@Singleton
public class MessageServiceStub implements MessageService {


    @Override
    public boolean contains(Prospect prospect) {
        return false;
    }
}
