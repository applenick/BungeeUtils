package tc.oc.bungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;


public class BungeeMessages {
	
	/*
	 * Command Messages
	 */
	
	public static void sendLobbyMessage(CommandSender sender){
		TextComponent lobbyTP = new TextComponent("Teleporting you to the lobby...");
		lobbyTP.setColor(ChatColor.GREEN);
		
		sender.sendMessage(lobbyTP);
	}
	
	public static void sendLobbyError(CommandSender sender){
		TextComponent lobbyError = new TextComponent("Only Players may use this command");
		lobbyError.setColor(ChatColor.RED);
		
		sender.sendMessage(lobbyError);
	}
	
	public static void addServerMessage(CommandSender sender , String name){
		TextComponent addServer = new TextComponent("Added Server ");
		addServer.setColor(ChatColor.GREEN);
		addServer.addExtra(ChatColor.GOLD + name);
		
		sender.sendMessage(addServer);
	}
	
	public static void delServer(CommandSender sender, String name){
		TextComponent delServer = new TextComponent("Removed Server ");
		delServer.setColor(ChatColor.GREEN);
		delServer.addExtra(ChatColor.GOLD + name);
		
		sender.sendMessage(delServer);
	}
	
	public static void noServerError(CommandSender sender, String name){
		TextComponent noServer = new TextComponent("Could not find server ");
		noServer.setColor(ChatColor.RED);
		noServer.addExtra(ChatColor.GOLD + name);
		
		sender.sendMessage(noServer);
	}
	
	/*
	 * sk89q command framework messages
	 */
	
	public static void commandPermissionsException(CommandSender sender){
		TextComponent permissionException = new TextComponent("You don't have permission.");
		permissionException.setColor(ChatColor.RED);
		
		sender.sendMessage(permissionException);
	}
	
	public static void nestedCommandUsageException(CommandSender sender , String usage){
		TextComponent nestedException = new TextComponent(usage);
		nestedException.setColor(ChatColor.RED);
		
		sender.sendMessage(nestedException);
	}
	
	public static void commandUsageException(CommandSender sender, String message ,String usage){
		TextComponent usageMessage = new TextComponent(message);
		TextComponent usageException = new TextComponent(usage);
		
		usageMessage.setColor(ChatColor.RED);
		usageException.setColor(ChatColor.RED);
		
		sender.sendMessage(usageMessage);
		sender.sendMessage(usageException);
	}
	
	public static void numberFormatException(CommandSender sender){
		TextComponent numberFormat = new TextComponent("Number expected, string received instead.");
		numberFormat.setColor(ChatColor.RED);
		
		sender.sendMessage(numberFormat);
	}
	
	public static void consoleError(CommandSender sender){
		TextComponent consoleError = new TextComponent("An error has occurred. See console.");
		consoleError.setColor(ChatColor.RED);
		
		sender.sendMessage(consoleError);
	}
	
	public static void commandException(CommandSender sender, String exception){
		TextComponent consoleError = new TextComponent(exception);
		consoleError.setColor(ChatColor.RED);

		sender.sendMessage(consoleError);
	}
}
