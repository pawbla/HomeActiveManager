package com.pawbla.project.home.history.module.controllers;


import com.pawbla.project.home.history.module.parsers.ValidDatesParser;
import com.pawbla.project.home.history.module.parsers.WeatherDayHistoryParser;
import com.pawbla.project.home.history.module.parsers.WeatherMonthHistoryParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/history/weather")
public class WeatherHistoryController {

    private final ValidDatesParser validDatesParser;
    private final WeatherMonthHistoryParser monthHistoryParser;
    private final WeatherDayHistoryParser dayHistoryParser;

    public WeatherHistoryController(ValidDatesParser validDatesParser, WeatherMonthHistoryParser monthHistoryParser,
                                    WeatherDayHistoryParser dayHistoryParser) {
        this.validDatesParser = validDatesParser;
        this.monthHistoryParser = monthHistoryParser;
        this.dayHistoryParser = dayHistoryParser;
    }

    @GetMapping(value = "/validData", produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> getValidData() {
        return ResponseEntity.ok().body(validDatesParser.getParsed());
    }

    @GetMapping(value = "/year", produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> getYearHistoryData(@RequestParam String type, @RequestParam String year) {
        try {
            return ResponseEntity.ok().body(monthHistoryParser.getParsed(type, year));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(value = "/month", produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<String> getMonthHistoryData(@RequestParam String type, @RequestParam String year, @RequestParam String month) {
        try {
            return ResponseEntity.ok().body(dayHistoryParser.getParsed(type, year, month));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
