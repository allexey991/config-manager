package com.crif.contact.cloud.manger.service;

import com.crif.contact.cloud.manger.config.ProperyHandler;
import com.crif.contact.cloud.manger.model.FileRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConfigService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);
    private static final int MAX_DEPTH = 100;

    @Autowired
    private GitService gitService;
    @Autowired
    private ProperyHandler properyHandler;

    /**
     * return file content by
     *
     * @param appName  application name
     * @param stand    stand name
     * @param env      environment
     * @param fileName fileName
     * @param request  fileProperties
     * @return {@link String}
     */
    public String getFileContent(String appName, String stand, String env, String fileName, FileRequest request) {
        boolean successPull = gitService.gitPull();
        if (successPull) {
            Path filePath = searchFile(fileName);
            BufferedReader reader = null;
            try {
                reader = Files.newBufferedReader(filePath);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line+"\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    reader.close();
                    return null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private Path searchFile(String fileName) {
        String workDir = properyHandler.getWorkDir();
        Path startPath = Paths.get(workDir);
        try (Stream<Path> matches = Files.find(startPath, MAX_DEPTH, (path, basicFileAttributes) ->
                path.getFileName()
                        .toString()
                        .contains(fileName))) {
            List<Path> pathList = matches.map(Path::toAbsolutePath).collect(Collectors.toList());
            if (!pathList.isEmpty()) {
                logger.info("searchFile: PathList=[{}]", pathList);
                if (pathList.size() > 1) {
                    logger.info("searchFile: Found more than 1 file, return first. Filepath=[{}]",
                            pathList.get(0).getFileName());
                }
                return pathList.get(0);
            } else {
                logger.info("searchFile: File not found, FileName=[{}]", fileName);
                return null;
            }
        } catch (IOException ex) {
            logger.error("searchFile: Error while searching. WorkDir=[{}], FileName=[{}]", workDir, fileName, ex);
            return null;
        }
    }

    /**
     * return file by
     *
     * @param appName  application name
     * @param stand    stand name
     * @param env      environment
     * @param fileName fileName
     * @param request  fileProperties
     * @return {@link File}
     */
    public File getFile(String appName, String stand, String env, String fileName, FileRequest request) {
        return null;
    }
}
