package tc.oc.bungee.utils;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import tc.oc.bungee.utils.commands.ServerCommands;

import com.sk89q.bungee.util.BungeeCommandsManager;
import com.sk89q.bungee.util.CommandExecutor;
import com.sk89q.bungee.util.CommandRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public class BungeeUtils extends Plugin implements CommandExecutor<CommandSender> {
    private static BungeeUtils bungeeutils;
    private BungeeCommandsManager commands;

    public static BungeeUtils get() {
        return bungeeutils;
    }

    public BungeeUtils() {
        bungeeutils = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerCommands();
    }

    private void registerCommands() {
        PluginManager pluginManager = this.getProxy().getPluginManager();

        this.commands = new BungeeCommandsManager();

        CommandRegistration registrar = new CommandRegistration(this, pluginManager, this.commands, this);
        registrar.register(ServerCommands.class);
    }

    @Override
    public void onCommand(CommandSender sender, String commandName, String[] args) {
        try {
            this.commands.execute(commandName, args, sender, sender);
        } catch (CommandPermissionsException e) {
        	BungeeMessages.commandPermissionsException(sender);
        } catch (MissingNestedCommandException e) {
            BungeeMessages.nestedCommandUsageException(sender, e.getUsage());
        } catch (CommandUsageException e) {
        	BungeeMessages.commandUsageException(sender, e.getMessage(), e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                BungeeMessages.numberFormatException(sender);
            } else {
            	BungeeMessages.consoleError(sender);
            	e.printStackTrace();
            }
        } catch (CommandException e) {
        	BungeeMessages.commandException(sender, e.getMessage());
        }
    }
}
