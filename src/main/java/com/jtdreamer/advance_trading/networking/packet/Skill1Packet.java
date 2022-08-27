package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class Skill1Packet {
	private final int skill1;

	public Skill1Packet(int i) {
        this.skill1 = i;
    }

	public Skill1Packet(FriendlyByteBuf buf) {
        this.skill1 = buf.readInt();
    }

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(skill1);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ClientData.setSkill1(skill1);
		});
		return true;
	}
}
