package org.cn.plugin.gradle.monitor;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class MonitorPluginImpl implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getGradle().addBuildListener(new TimeListener());

        Task task = project.getTasks().create("helloGradle", MonitorTask.class);
        task.setGroup("monitor");
    }
}
