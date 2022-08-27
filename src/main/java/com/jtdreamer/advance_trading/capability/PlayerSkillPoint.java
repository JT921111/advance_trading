package com.jtdreamer.advance_trading.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerSkillPoint {
	private int skillPoint;
	private final int MIN_skillPoint = 0;
	
	public int getSkillPoint() {
		return skillPoint;
	}
	
	public void setSkillPoint(int i) {
		this.skillPoint = Math.max(i, MIN_skillPoint);
	}
	
	public void addSkillPoint(int i) {
		this.skillPoint = skillPoint + i;
	}

    public void subSkillPoint(int i) {
        this.skillPoint = Math.max(skillPoint - i, MIN_skillPoint);
    }

    public void copyFrom(PlayerSkillPoint source) {
        this.skillPoint = source.skillPoint;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("SkillPoint", skillPoint);
    }

    public void loadNBTData(CompoundTag nbt) {
    	skillPoint = nbt.getInt("SkillPoint");
    }
}
