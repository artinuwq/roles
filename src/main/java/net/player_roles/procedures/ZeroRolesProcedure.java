package net.player_roles.procedures;

import net.player_roles.PlayerRolesModVariables;
import net.player_roles.PlayerRolesMod;

import net.minecraft.world.IWorld;

import java.util.Map;

public class ZeroRolesProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PlayerRolesMod.LOGGER.warn("Failed to load dependency world for procedure ZeroRoles!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		PlayerRolesModVariables.WorldVariables.get(world).Roles = 0;
		PlayerRolesModVariables.WorldVariables.get(world).syncData(world);
	}
}
