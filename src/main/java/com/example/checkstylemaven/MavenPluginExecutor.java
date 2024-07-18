package com.example.checkstylemaven;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MavenPluginExecutor implements CommandLineRunner {
    @Override
    public void run(String... args) throws IOException, InterruptedException {
        File marker = new File(".git/hooks/marker");
        if (!marker.exists()) {
            String mvnCommand = isWindows() ? "mvnw.cmd" : "./mvnw";
            String command = mvnCommand + " compile";

            // Execute the command in the project root directory
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.directory(new File(System.getProperty("user.dir")));
            processBuilder.start();

            // Create the marker file to indicate the command has been executed
//            marker.createNewFile();
        }
    }

    private boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");
    }
}