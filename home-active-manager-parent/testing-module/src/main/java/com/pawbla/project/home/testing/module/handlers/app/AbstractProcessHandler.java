package com.pawbla.project.home.testing.module.handlers.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbstractProcessHandler implements ProcessHandler {
    private final Logger LOG = LogManager.getLogger(AbstractProcessHandler.class);

    private final ProcessBuilder processBuilder;
    private Process process;

    public AbstractProcessHandler(final String pathToApp, final List<String> params) {
        List<String> command = getCommand(pathToApp, params);
        processBuilder = new ProcessBuilder(command);
        //processBuilder.redirectErrorStream(true); // redirect error stream to output stream
        //processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    }

    @Override
    public boolean start() {
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            LOG.warn("Cannot start process {}", e.getMessage());
            return false;
        }
        LOG.info("Process started");
        return true;
    }

    @Override
    public void stop() {
        destroyProcess();
        destroyNpmFallback();
    }

    @Override
    public boolean isAlive() {
        if (process != null) {
            return process.isAlive();
        }
        return false;
    }

    @PreDestroy
    public void killProcess() {
        destroyProcess();
        destroyNpmFallback();
    }

    private List<String> getCommand(final String pathToApp, final List<String> params) {
        if(pathToApp.contains("jar")) {
            return getJarCommand(pathToApp, params);
        }
        return getNpmCommand(pathToApp);
    }

    private List<String> getJarCommand(final String pathToApp, final List<String> params) {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-jar");
        command.add(pathToApp);
        command.addAll(params);
        return command;
    }

    private List<String> getNpmCommand(final String pathToApp) {
        List<String> command = new ArrayList<>();
        command.add("npm.cmd");
        command.add("--prefix");
        command.add(pathToApp);
        command.add("start");
        return command;
    }

    private void destroyProcess() {
        if(process != null && process.isAlive()) {
            process.destroy();
            LOG.info("Process stopped");
        }
    }

    private void destroyNpmFallback() {
        if (processBuilder.command().get(0).contains("npm")) {
            LOG.info("Fallback for npm");
            try {
                Runtime.getRuntime().exec("taskkill -F /im node.exe");
            } catch (IOException e) {
                LOG.warn("Exception when npm fallback {}", e.getMessage());
            }
        }
    }
}
