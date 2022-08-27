package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.capability.PlayerDataProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class TradeLevelResetPacket {
	public TradeLevelResetPacket() {
	}

	public TradeLevelResetPacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			if (player.hasPermissions(2)) {
				player.sendSystemMessage(Component.literal("Lv reset"));
				player.getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(data -> {
					data.setTradeLv(0);
				});
				player.getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(data -> {
					data.setTradeRank(0);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(data -> {
					data.setSkillPoint(0);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(data -> {
					data.setSkill_1(0);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_2).ifPresent(data -> {
					data.setSkill_2(0);
				});
			} else {
				player.sendSystemMessage(Component.literal("No permission"));
			}
		});
		return true;
	}
}
