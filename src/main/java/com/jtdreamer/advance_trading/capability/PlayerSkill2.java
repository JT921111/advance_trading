package com.jtdreamer.advance_trading.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerSkill2 {
	private int skill_2;
	private final int MIN_skill_2 = 0;
	private final int MAX_skill_2 = 20;
	
	public int getSkill_2() {
		return skill_2;
	}
	
	public int getMaxSkill_2() {
		return MAX_skill_2;
	}
	
	public void setSkill_2(int i) {
		this.skill_2 = i;
	}
	
	public void addSkill_2(int i) {
		this.skill_2 = Math.min(skill_2 + i, this.MAX_skill_2);
	}

    public void subSkill_2(int i) {
        this.skill_2 = Math.max(skill_2 - i, this.MIN_skill_2);
    }

    public void copyFrom(PlayerSkill2 source) {
        this.skill_2 = source.skill_2;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("Skill_2", skill_2);
    }

    public void loadNBTData(CompoundTag nbt) {
    	skill_2 = nbt.getInt("Skill_2");
    }
}
