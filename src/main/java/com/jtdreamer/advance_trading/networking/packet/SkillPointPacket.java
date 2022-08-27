package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SkillPointPacket {
	private final int skillPoint;

	public SkillPointPacket(int i) {
        this.skillPoint = i;
    }

	public SkillPointPacket(FriendlyByteBuf buf) {
        this.skillPoint = buf.readInt();
    }

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(skillPoint);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ClientData.setSkillPoint(skillPoint);
		});
		return true;
	}
}
