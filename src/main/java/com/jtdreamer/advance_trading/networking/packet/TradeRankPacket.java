package com.jtdreamer.advance_trading.networking.packet;

import java.util.function.Supplier;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class TradeRankPacket {
	private final int tradeRank;

	public TradeRankPacket(int i) {
        this.tradeRank = i;
    }

	public TradeRankPacket(FriendlyByteBuf buf) {
        this.tradeRank = buf.readInt();
    }

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(tradeRank);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ClientData.setTradeRank(tradeRank);
		});
		return true;
	}
}
