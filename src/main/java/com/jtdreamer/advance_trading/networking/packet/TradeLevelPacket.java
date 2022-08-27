package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class TradeLevelPacket {
	private final int tradeLv;

	public TradeLevelPacket(int i) {
        this.tradeLv = i;
    }

	public TradeLevelPacket(FriendlyByteBuf buf) {
        this.tradeLv = buf.readInt();
    }

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(tradeLv);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ClientData.setTradeLv(tradeLv);
		});
		return true;
	}
}
