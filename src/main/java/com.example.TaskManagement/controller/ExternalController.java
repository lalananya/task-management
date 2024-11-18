package com.example.TaskManagement.controller;


import com.example.TaskManagement.api.response.TemplateServiceResponse;
import com.example.TaskManagement.api.response.WeatherResponse;
import com.example.TaskManagement.api.response.updateDob.UpdateDobResponse;
import com.example.TaskManagement.service.TemplateService;
import com.example.TaskManagement.service.UpdateDobService;
import com.example.TaskManagement.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
public class ExternalController {

    private static final Logger log = LoggerFactory.getLogger(ExternalController.class);

    @Autowired
    WeatherService weatherService;

    @Autowired
    TemplateService templateService;

    @Autowired
    UpdateDobService updateDobService;

    @GetMapping("/weatherDetails")
    public WeatherResponse getWeatherDetails() {
        try {
            WeatherResponse weatherResponse = weatherService.getWeatherDetails();
            return weatherResponse;
        } catch (Exception e) {
            log.error("weatherResponse | Exception Occured | : " + String.valueOf(e));
            return null;
        }
    }

    @GetMapping("/templateService")
    public TemplateServiceResponse getTemplateService() {
        try {
            TemplateServiceResponse templateServiceResponse = templateService.getTemplateData();
            return templateServiceResponse;
        } catch (Exception e) {
            log.error("TemplateServiceResponse | Exception Occured | : " + String.valueOf(e));
            return null;
        }
    }

    @PostMapping("/updateDob")
    public UpdateDobResponse updateDob() {
        try{
            UpdateDobResponse updateDobServiceResponse = updateDobService.updateDobResponse();
            return updateDobServiceResponse;
        } catch (Exception e) {
            log.error("UpdateDobResponse | Exception Occured | : " + String.valueOf(e));
            return null;
        }
    }
}
