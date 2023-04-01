package com.pawbla.project.home.testing.module.handlers.response;

import com.pawbla.project.home.testing.module.view.panel.response.ScenarioPanel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractServiceHandler implements ServiceHandler {

    private static final List<String> ITEMS = Arrays.asList("200_OK", "400_BadRequest", "500_InternalServerError");

    private final ScenarioPanel scenarioPanel;

    public AbstractServiceHandler(ScenarioPanel scenarioPanel) {
        this.scenarioPanel = scenarioPanel;
        this.scenarioPanel.createItems(ITEMS);
    }

    @Override
    public ResponseEntity<String> getResponse() {
        ResponseEntity<String> responseEntity = null;
        String selection = scenarioPanel.getSelection();
        String[] parts = selection.split("_");
        String respCode = parts[0];
        switch (respCode) {
            case "200":
                responseEntity = ResponseEntity.ok().body(getBody());
                break;
            case "400":
                responseEntity = ResponseEntity.badRequest().body(null);
                break;
            case "500":
                responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                break;
        }
        return responseEntity;
    }
}
