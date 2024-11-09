package xyz.swift.botlauncher;

import xyz.swift.botlauncher.helper.Launcher;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BotLauncher extends JavaPlugin {

    private Launcher launcher;

    @Override
    public void onEnable() {
        // Create a new launcher object
        launcher = new Launcher();

        // Set the folder where the bot will be stored
        File botDir = new File(getDataFolder().getParentFile(), "BotLauncher");

        // Check if the folder exists if not try to create it
        if (!botDir.exists()) {
            if (botDir.mkdirs()) {
                getLogger().info("Folder created: " + botDir.getAbsolutePath());
            } else {
                getLogger().severe("Couldn't create the folder: " + botDir.getAbsolutePath());
                return; // Stop here if folder creation fails
            }
        }

        // Launch the bot
        launchBot(botDir);
    }

    @Override
    public void onDisable() {
        // If the launcher exists stop the bot
        if (launcher != null) {
            launcher.closeBot();
        }
    }

    /**
     * Tries to start the bot by looking for Bot.jar in the botDir.
     * @param botDir The directory where Bot.jar should be.
     */
    private void launchBot(File botDir) {
        // Look for the Bot.jar file in the bot directory rename it according to your needs
        File file = new File(botDir, "Bot.jar");
        if (file.exists()) {
            // If the bot.jar exists launch the bot
            launcher.launchBot(file);
        } else {
            // If bot.jar doesnt exist
            getLogger().severe("Bot.jar not found. The jar should be put here: " + file.getAbsolutePath());
        }
    }
}
