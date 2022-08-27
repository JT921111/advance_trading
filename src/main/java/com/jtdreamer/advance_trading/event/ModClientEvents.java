package com.jtdreamer.advance_trading.event;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.key.ModKeyBinds;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModInit.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
	@SubscribeEvent
	public static void onKeyRegister(RegisterKeyMappingsEvent event) {
		event.register(ModKeyBinds.openLevel);
		event.register(ModKeyBinds.increaseLevel);
		event.register(ModKeyBinds.resetLevel);
	}
}
