package com.jtdreamer.advance_trading.event;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.key.ModKeyBinds;
import com.jtdreamer.advance_trading.networking.ModPackets;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelIncreasePacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelResetPacket;
import com.jtdreamer.advance_trading.screen.TradingLevelScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModInit.MODID, value = Dist.CLIENT)
public class ForgeClientEvents {
	@SubscribeEvent
	public static void onKeyInput(InputEvent event) {
		if (ModKeyBinds.openLevel.consumeClick()) {
			Minecraft mc = Minecraft.getInstance();
			mc.setScreen(new TradingLevelScreen(Component.translatable("screen.advance_trading.title")));
		} else if (ModKeyBinds.increaseLevel.consumeClick()) {
			ModPackets.sendToServer(new TradeLevelIncreasePacket());
		} else if (ModKeyBinds.resetLevel.consumeClick()) {
			ModPackets.sendToServer(new TradeLevelResetPacket());
		}
	}
}
