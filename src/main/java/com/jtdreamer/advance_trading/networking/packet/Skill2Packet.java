package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class Skill2Packet {
	private final int skill2;

	public Skill2Packet(int i) {
        this.skill2 = i;
    }

	public Skill2Packet(FriendlyByteBuf buf) {
        this.skill2 = buf.readInt();
    }

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(skill2);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ClientData.setSkill2(skill2);
		});
		return true;
	}
}
