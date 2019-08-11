package com.crif.contact.cloud.manger.service;


import com.crif.contact.cloud.manger.config.ProperyHandler;
import com.crif.contact.cloud.manger.dictionary.GitManager;
import com.crif.contact.cloud.manger.dictionary.Command;
import org.apache.http.util.Asserts;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GitService {
    private static final Logger logger = LoggerFactory.getLogger(GitService.class);
    private static final String BRANCH = "master";

    @Autowired
    private ProperyHandler properyHandler;

    private File file;
    private CredentialsProvider credentialsProvider;

    @PostConstruct
    public void initProperties() {
        Asserts.notEmpty(properyHandler.getLogin(), "Login");
        Asserts.notEmpty(properyHandler.getPassword(), "Password");
        Asserts.notEmpty(properyHandler.getWorkDir(), "Working directory");
        Asserts.notEmpty(properyHandler.getRepositoryUri(), "Repository URL");
        file = new File(properyHandler.getWorkDir());
        credentialsProvider = new UsernamePasswordCredentialsProvider(properyHandler.getLogin(), properyHandler.getPassword());
    }

    public boolean gitPull() {
        file = new File(properyHandler.getWorkDir());
        credentialsProvider = new UsernamePasswordCredentialsProvider(properyHandler.getLogin(), properyHandler.getPassword());
        try {
            Git.open(file).pull()
                    .setCredentialsProvider(credentialsProvider)
                    .call();
            return true;
        } catch (GitAPIException | IOException e) {
            logger.error("GitPull: Error while pulling. WorkDir=[{}] Error=[{}]", file, e);
            return false;
        }
    }

    public Boolean gitClone() {
        try {
            Git.cloneRepository().setURI(properyHandler.getRepositoryUri())
                    .setDirectory(file)
                    .setCredentialsProvider(credentialsProvider)
                    .setBranch(BRANCH)
                    .call();
            return true;
        } catch (GitAPIException | JGitInternalException e) {
            logger.error("GitClone: Error while clone repository. WorkDir=[{}] Branch=[{}] URL=[{}] Error=[{}]",
                    file,
                    BRANCH,
                    properyHandler.getRepositoryUri(),
                    e);
            return false;
        }
    }
}
