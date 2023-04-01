package com.pawbla.project.home.testing.module.handlers.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("reactAppProcessHandler")
public class ReactAppProcessHandler extends AbstractProcessHandler {
    public ReactAppProcessHandler(@Value("${react.app.path}") final String pathToApp) {
        super(pathToApp, Collections.emptyList());
    }
}
