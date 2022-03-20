package com.caojx.learn.idea.plugin.domain.service.impl;

import com.caojx.learn.idea.plugin.domain.model.vo.ProjectConfigVO;
import com.caojx.learn.idea.plugin.domain.service.AbstractProjectGenerator;
import com.intellij.openapi.project.Project;

public class ProjectGeneratorImpl extends AbstractProjectGenerator {
    @Override
    protected void generateProjectPOM(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, "/", entryPath, "pom.xml", "pom.ftl", project);
    }

    @Override
    protected void generateProjectDDD(Project project, String entryPath, ProjectConfigVO projectConfig) {
        // create application
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + ".application", "package-info.java", "application/package-info.ftl", projectConfig);

        // create common
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + ".common", "package-info.java", "common/package-info.ftl", projectConfig);

        // create domain
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + ".domain", "package-info.java", "domain/package-info.ftl", projectConfig);

        // create infrastructure
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + ".infrastructure", "package-info.java", "infrastructure/package-info.ftl", projectConfig);

        // create interfaces
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + ".interfaces", "package-info.java", "interfaces/package-info.ftl", projectConfig);

    }

    @Override
    protected void generateApplication(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package(), projectConfig.get_artifactId() + "Application.java", "application.ftl", projectConfig);

    }

    @Override
    protected void generateYml(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, entryPath, "src/main/resources/", "application.yml", "yml.ftl", projectConfig);

    }

    @Override
    protected void generateCommon(Project project, String entryPath, ProjectConfigVO projectConfig) {
        writeFile(project, entryPath, "src/main/java/" + projectConfig.get_package() + "/common", "Constants.java", "common/Constants.ftl", projectConfig);

    }
}
