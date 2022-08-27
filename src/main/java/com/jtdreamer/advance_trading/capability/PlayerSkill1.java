package com.jtdreamer.advance_trading.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerSkill1 {
	private int skill_1;
	private final int MIN_skill_1 = 0;
	private final int MAX_skill_1 = 20;
	
	public int getSkill_1() {
		return skill_1;
	}
	
	public int getMaxSkill_1() {
		return MAX_skill_1;
	}
	
	public void setSkill_1(int i) {
		this.skill_1 = i;
	}
	
	public void addSkill_1(int i) {
		this.skill_1 = Math.min(skill_1 + i, this.MAX_skill_1);
	}

    public void subSkill_1(int i) {
        this.skill_1 = Math.max(skill_1 - i, this.MIN_skill_1);
    }

    public void copyFrom(PlayerSkill1 source) {
        this.skill_1 = source.skill_1;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("Skill_1", skill_1);
    }

    public void loadNBTData(CompoundTag nbt) {
    	skill_1 = nbt.getInt("Skill_1");
    }
}
