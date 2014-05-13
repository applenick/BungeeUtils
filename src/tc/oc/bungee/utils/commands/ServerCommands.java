package tc.oc.bungee.utils.commands;

import java.net.InetSocketAddress;
import java.util.Collection;

import tc.oc.bungee.utils.BungeeMessages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import com.sk89q.bungee.util.BungeeWrappedCommandSender;
import com.sk89q.minecraft.util.commands.*;
import com.sk89q.minecraft.util.pagination.SimplePaginatedResult;

public class ServerCommands {
    public ServerCommands() {
    }

    @Command(
        aliases = {"hub", "lobby", "main"},
        desc= "Teleport to the lobby",
        min = 0,
        max = 0
    )
    public static void hub(final CommandContext args, CommandSender sender) throws CommandException {
        if(sender instanceof ProxiedPlayer) {
            ((ProxiedPlayer) sender).connect(ProxyServer.getInstance().getServers().get("default"));
            BungeeMessages.sendLobbyMessage(sender);
        } else {
            BungeeMessages.sendLobbyError(sender);
        }
    }

    @Command(
        aliases = {"serverlist", "sl"},
        desc = "List all BungeeCord servers",
        usage = "[page]",
        min = 0,
        max = 1
    )
    @CommandPermissions("bungeeutils.serverlist")
    public static void serverlist(final CommandContext args, CommandSender sender) throws CommandException {
        final Collection<ServerInfo> servers = BungeeCord.getInstance().getServers().values();

        new SimplePaginatedResult<ServerInfo>(ChatColor.BOLD + "BungeeCord Servers") {
            @Override public String format(ServerInfo server, int index) {
                return (index + 1) + ". " + "[" + ChatColor.GOLD + server.getName() + ChatColor.WHITE + "]" + " " + ChatColor.AQUA + server.getAddress().getHostString() + ChatColor.DARK_AQUA + ":" + ChatColor.AQUA + server.getAddress().getPort();
            }
        }.display(new BungeeWrappedCommandSender(sender), servers, args.getInteger(0, 1) /* page */);
    }

    @Command(
        aliases = {"addserver"},
        desc = "Add a BungeeCord server",
        usage = "<name> <address> [port]",
        flags = "r",
        min = 2,
        max = 4
    )
    @CommandPermissions("bungeeutils.addserver")
    public static void addserver(final CommandContext args, CommandSender sender) throws CommandException {
        String name = args.getString(0);
        String address = args.getString(1);
        int port = args.argsLength() > 2 ? args.getInteger(2) : 25565;
        boolean restricted = args.hasFlag('r');

        ServerInfo serverInfo = ProxyServer.getInstance().constructServerInfo(name, new InetSocketAddress(address, port), "", restricted);
        ProxyServer.getInstance().getServers().put(name, serverInfo);

        BungeeMessages.addServerMessage(sender, name);
    }

    @Command(
        aliases = {"delserver"},
        desc = "Remove a BungeeCord server",
        usage = "<name>",
        min = 1,
        max = 1
    )
    @CommandPermissions("bungeeutils.delserver")
    public static void delserver(final CommandContext args, CommandSender sender) throws CommandException {
        String name = args.getString(0);

        if (ProxyServer.getInstance().getServers().remove(name) == null) {
        	BungeeMessages.noServerError(sender, name);
        } else {
        	BungeeMessages.delServer(sender, name);
        }
    }
}
