package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.capability.PlayerDataProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class TradeLevelIncreasePacket {
	public TradeLevelIncreasePacket() {
	}

	public TradeLevelIncreasePacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			if (player.hasPermissions(2)) {
				player.sendSystemMessage(Component.literal("Lv up"));
				player.getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(level -> {
					level.addTradeLv(1);
					if (level.getTradeLv() >= level.getMaxTradeLv()) {
						level.subTradeLv(level.getMaxTradeLv());
						player.getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(rank -> {
							rank.addTradeRank(1);
							player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(pt -> {
								pt.addSkillPoint(1);
								if (rank.getTradeRank() % 5 == 0) {
									pt.addSkillPoint(1);
								}
							});
						});
					}
				});
			} else {
				player.sendSystemMessage(Component.literal("No permission"));
			}
		});
		return true;
	}
}
