package com.pawbla.project.home.application.monitoring.services;

import com.pawbla.project.home.application.monitoring.models.Network;

public interface NetworkService {
    void gatherNetworkInfo(Network network);
}
