package xyz.swift.botlauncher.helper;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class Launcher {

    // This will hold the bot's running process
    private Process run;

    /**
     * Starts the bot using the given bot.jar file.
     * @param botJar The File object for the bot's JAR file.
     */
    public void launchBot(File botJar) {
        // Check if the bot.jar file exists or whatever it is named in your case
        if (!botJar.exists()) {
            Bukkit.getLogger().info("Bot.jar not found at: " + botJar.getAbsolutePath());
            return; // If it doesn't exist stop the method here
        }

        try {
            // Try to start the bot with the provided JAR file
            run = new ProcessBuilder("java", "-jar", botJar.getAbsolutePath()).start();
            Bukkit.getLogger().info("Bot has been started");
        } catch (IOException e) {
            // If fails
            Bukkit.getLogger().severe("Failed to launch bot: " + e.getMessage());
        }
    }

    /**
     * Stops the bot if it is running.
     * It will close the process and set it to null.
     */
    public void closeBot() {
        // Check if the bot is running
        if (run != null) {
            // Stop the bot process and set it to null
            run.destroy();
            run = null;
            Bukkit.getLogger().info("Bot has been disabled");
        }
    }
}
