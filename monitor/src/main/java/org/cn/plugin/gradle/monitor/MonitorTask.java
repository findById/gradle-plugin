package org.cn.plugin.gradle.monitor;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class MonitorTask extends DefaultTask {

    @TaskAction
    public void output() {
        System.out.println("Hello, Gradle!");
    }

}
