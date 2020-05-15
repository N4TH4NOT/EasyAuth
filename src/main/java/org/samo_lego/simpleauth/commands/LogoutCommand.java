package org.samo_lego.simpleauth.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import org.samo_lego.simpleauth.SimpleAuth;

import static net.minecraft.server.command.CommandManager.literal;
import static org.samo_lego.simpleauth.SimpleAuth.config;

public class LogoutCommand {

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        // Registering the "/logout" command
        dispatcher.register(literal("logout")
                .executes(ctx -> logout(ctx.getSource())) // Tries to deauthenticate user
        );
    }

    private static int logout(ServerCommandSource serverCommandSource) throws CommandSyntaxException {
        ServerPlayerEntity player = serverCommandSource.getPlayer();
        SimpleAuth.deauthenticatePlayer(player);
        player.sendMessage(new LiteralText(config.lang.successfulLogout), false);
        return 1;
    }
}
