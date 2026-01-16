package com.company.gmail.compliance.client;

import org.springframework.stereotype.Component;

import com.google.api.services.directory.Directory;

@Component
public class DirectoryClient {

    private final Directory directory;

    public DirectoryClient(Directory directory) {
        this.directory = directory;
    }
}
