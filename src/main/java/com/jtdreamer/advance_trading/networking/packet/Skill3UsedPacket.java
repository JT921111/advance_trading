package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.capability.PlayerDataProvider;
import com.jtdreamer.advance_trading.item.ModItems;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class Skill3UsedPacket {
	public Skill3UsedPacket() {
	}

	public Skill3UsedPacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(data -> {
				data.subSkillPoint(3);
			});
			player.drop(new ItemStack(ModItems.STOCK_INCREASE.get(), 1), false);
		});
		return true;
	}
}
