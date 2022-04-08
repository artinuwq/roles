package net.player_roles.procedures;

import net.player_roles.PlayerRolesModVariables;
import net.player_roles.PlayerRolesMod;

import net.minecraft.world.IWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class LoggerChoiceProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PlayerRolesMod.LOGGER.warn("Failed to load dependency world for procedure LoggerChoice!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				PlayerRolesMod.LOGGER.warn("Failed to load dependency entity for procedure LoggerChoice!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		PlayerRolesModVariables.WorldVariables.get(world).Roles = 3;
		PlayerRolesModVariables.WorldVariables.get(world).syncData(world);
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
