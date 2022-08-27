package com.jtdreamer.advance_trading.capability;

import com.jtdreamer.advance_trading.client.ClientData;

import net.minecraft.nbt.CompoundTag;

public class PlayerTradeLevel {
	private int tradeLv;
	private final int MIN_tradeLv = 0;
	private final int MAX_tradeLv = 100;
	
	public int getTradeLv() {
		return tradeLv;
	}
	
	public int getMaxTradeLv() {
		return MAX_tradeLv + ClientData.getPlayerTradeRank() * 10;
	}
	
	public void setTradeLv(int i) {
		this.tradeLv = Math.max(Math.min(i, this.getMaxTradeLv()), MIN_tradeLv);
	}
	
	public void addTradeLv(int i) {
		this.tradeLv = tradeLv + i;
	}

    public void subTradeLv(int i) {
        this.tradeLv = Math.max(tradeLv - i, this.MIN_tradeLv);
    }

    public void copyFrom(PlayerTradeLevel source) {
        this.tradeLv = source.tradeLv;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("TradeLevel", tradeLv);
    }

    public void loadNBTData(CompoundTag nbt) {
    	tradeLv = nbt.getInt("TradeLevel");
    }
}
