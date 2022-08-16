package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Network;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Profile({"dev", "test"})
public class NetworkServiceDev implements NetworkService {
    @Override
    public void gatherNetworkInfo(Network network) {
        network.setHostName("Network Host Name");
        network.setIpAddressList(Arrays.asList("1.1.1.1", "1.1.1.2"));
        network.setFqdnList(Arrays.asList("1.1.2.1", "1.1.2.2"));
        network.setNameserverList(Arrays.asList("Nameserver1", "Namserver2"));
    }
}
