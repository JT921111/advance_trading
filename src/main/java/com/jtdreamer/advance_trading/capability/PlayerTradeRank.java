package com.jtdreamer.advance_trading.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerTradeRank {
	private int tradeRank;
	private final int MIN_tradeRank = 0;
	private final int MAX_tradeRank = 100;
    
	public int getTradeRank() {
		return tradeRank;
	}
	
	public void setTradeRank(int i) {
		this.tradeRank = Math.max(Math.min(i, MAX_tradeRank), MIN_tradeRank);
	}
	
	public void addTradeRank(int i) {
		this.tradeRank = Math.min(tradeRank + i, MAX_tradeRank);
	}

    public void subTradeRank(int i) {
        this.tradeRank = Math.max(tradeRank - i, MIN_tradeRank);
    }

    public void copyFrom(PlayerTradeRank source) {
        this.tradeRank = source.tradeRank;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("TradeRank", tradeRank);
    }

    public void loadNBTData(CompoundTag nbt) {
    	tradeRank = nbt.getInt("TradeRank");
    }
}
