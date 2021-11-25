package io.fabric8.maven.docker.access;

import io.fabric8.maven.docker.access.DockerBuildx;
import io.fabric8.maven.docker.access.DockerBuildx.BuildxCommand;
import org.apache.maven.model.Build;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks the creation / desctruction of the buildx container and the created images in order to push them at a later time.
 */
public class BuildxTracker {
    private String builderName;

    private Map<String, BuildxCommand> imageIdToBuildxCommand = new HashMap<>();

    public void registerBuilder(String builderName) {
        this.builderName = builderName;
    }

    public boolean isBuilderStarted() {
        return builderName != null;
    }

    public String getBuilderName() {
        return builderName;
    }

    public void registerBuildxBuild(String imageId, BuildxCommand buildCommand) {
        imageIdToBuildxCommand.put(imageId, buildCommand);
    }

    public BuildxCommand getBuildCommand(String imageId) {
        return imageIdToBuildxCommand.get(imageId);
    }

    public void unregisterBuild(String imageId) {
        imageIdToBuildxCommand.remove(imageId);
    }
}
