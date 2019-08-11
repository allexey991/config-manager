package com.crif.contact.cloud.manger.controller;

import com.crif.contact.cloud.manger.model.FileRequest;
import com.crif.contact.cloud.manger.model.SuccessResponse;
import com.crif.contact.cloud.manger.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configs")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @GetMapping(value = "/{appName}/{stand}/{env}/{fileName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SuccessResponse getConfig(@PathVariable("appName") String appName,
                                     @PathVariable("stand") String stand,
                                     @PathVariable("env") String env,
                                     @PathVariable("fileName") String fileName,
                                     @RequestBody FileRequest request) {
        logger.info("ConfigController: Input parameters. Application name=[{}]," +
                "Stand=[{}]," +
                "Environment=[{}]," +
                "File name=[{}]," +
                "Request body=[{}]", appName, stand, env, fileName, request);
        return new SuccessResponse(configService.getFileContent(appName, stand, env, fileName, request));
    }
}
