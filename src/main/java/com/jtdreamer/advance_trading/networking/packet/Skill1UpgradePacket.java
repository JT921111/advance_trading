package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.capability.PlayerDataProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class Skill1UpgradePacket {
	public Skill1UpgradePacket() {
	}

	public Skill1UpgradePacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(data -> {
				data.subSkillPoint(1);
			});
			player.getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(data -> {
				data.addSkill_1(1);
			});
		});
		return true;
	}
}
