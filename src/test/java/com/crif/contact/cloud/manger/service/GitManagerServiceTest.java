package com.crif.contact.cloud.manger.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class GitManagerServiceTest extends AbstractTest {
    @Autowired
    private GitService gitService;

    @Test
    public void execCmd() {
        gitService.gitClone();
        gitService.gitPull();
    }
}