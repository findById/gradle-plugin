package org.cn.plugin.gradle.monitor;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskState;
import org.gradle.internal.time.Clock;

import java.util.HashMap;
import java.util.Map;

public class TimeListener implements TaskExecutionListener, BuildListener {

    private Clock clock;
    private Map<Long, String> times = new HashMap<>();
    private long totalTime;

    @Override
    public void buildStarted(Gradle gradle) {
    }

    @Override
    public void settingsEvaluated(Settings settings) {

    }

    @Override
    public void projectsLoaded(Gradle gradle) {

    }

    @Override
    public void projectsEvaluated(Gradle gradle) {

    }

    @Override
    public void buildFinished(BuildResult buildResult) {
        System.out.println(String.format("Total times %sms", totalTime));
        System.out.println(String.format("These tasks are simply taking far too long:"));
        for (Long time : times.keySet()) {
            if (time >= 100) {
                System.out.println(String.format("%s - %sms ", times.get(time), time));
            }
        }
    }

    @Override
    public void beforeExecute(Task task) {
        clock = new Clock();
    }

    @Override
    public void afterExecute(Task task, TaskState taskState) {
        long ms = clock.getElapsedMillis();
        times.put(ms, task.getPath());
        totalTime += ms;
        System.out.println(String.format("'%s' task used %sms", task.getPath(), clock.getElapsed()));
    }
}
