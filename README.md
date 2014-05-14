BungeeUtils
===========

BungeeUtils is a useful utility to modify the BungeeCord configuration without restarting.

Created by [Overcast Network](https://github.com/OvercastNetwork)

Updated to 1.7 by [AppleNick](https://github.com/AppleNick)

Commands
===========

###/lobby 
Alias: /hub , /main

The Lobby command will teleport the player to the defined default server.

Command Permissions: None

***

###/serverlist [page]
Alias: /sl

The Server List command will list all connected BungeeCord servers  as well as their IP & Port.

Command Permissions: `bungeeutils.serverlist`

***

###/addserver (-r) (Name) (Adress) [port]

The Add Server command allows you to add servers on the fly to your BungeeCord proxy.

__Warning__: Servers which are added with the /addserver command will only function until the proxy is restarted. 
Your serverlist will be restored to your default config set servers once restarted.

The `-r` flag will set restricted to true

The `[port]` will default to 25565 if not provided

Command Permission: `bungeeutils.addserver`

***

###/delserver [Name]

The Delete Server command will remove the specified server from your BungeeCord proxy.

__Warning__ : This will not delete servers from the bungeecord configuration, It will simply remove the server from the /server command until the next time the Proxy is restarted. To fully remove a configured server, delete it from the BungeeCord configuration.

Command Permission: `bungeeutils.delserver`

***



