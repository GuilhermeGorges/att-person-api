package com.guilherme.attornatus.personapi.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class OperationServer {
    protected static String formatData(String data){
        return data.replaceAll("[^0-9]+", "");
    }
}
