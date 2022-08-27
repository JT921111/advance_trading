package com.jtdreamer.advance_trading.key;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class ModKeyBinds {
	public static final KeyMapping openLevel = new KeyMapping("key.advance_trading.open_level",
			KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_LALT, "key.category.advance_trading");
	public static final KeyMapping increaseLevel = new KeyMapping("key.advance_trading.increase_level",
			KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_Z, "key.category.advance_trading");
	public static final KeyMapping resetLevel = new KeyMapping("key.advance_trading.reset_level",
			KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_X, "key.category.advance_trading");
}
