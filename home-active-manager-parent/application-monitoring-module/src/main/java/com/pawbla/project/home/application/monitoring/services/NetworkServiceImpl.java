package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Network;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("prod")
public class NetworkServiceImpl implements NetworkService {
    private final Logger logger = LogManager.getLogger(NetworkServiceImpl.class);

    @Override
    public void gatherNetworkInfo(Network network) {
        logger.info("Gather Network info");
        network.setHostName(readHostname());
        network.setIpAddressList(readIpAddress());
        network.setFqdnList(readFQDN());
        network.setNameserverList(readNameserver());
    }

    private String readHostname() {
        String hostName = "";
        try {
            hostName = NetworkInfo.getHostname();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read network host name. " + e.getMessage());
        }
        return hostName;
    }

    private List<String> readIpAddress() {
        String[] ipAddressList = {""};
        try {
            ipAddressList = NetworkInfo.getIPAddresses();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read network ip list. " + e.getMessage());
        }
        return Arrays.asList(ipAddressList);
    }

    private List<String> readFQDN() {
        String[] fqdnList = {""};
        try {
            fqdnList = NetworkInfo.getFQDNs();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read network FQDN list. " + e.getMessage());
        }
        return Arrays.asList(fqdnList);
    }

    private List<String> readNameserver() {
        String[] nameserverList = {""};
        try {
            nameserverList = NetworkInfo.getNameservers();
        } catch (IOException | InterruptedException e) {
            logger.warn("Cannot read network namserver list. " + e.getMessage());
        }
        return Arrays.asList(nameserverList);
    }
}

