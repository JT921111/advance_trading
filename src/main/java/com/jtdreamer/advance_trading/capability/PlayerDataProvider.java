package com.jtdreamer.advance_trading.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	public static Capability<PlayerTradeLevel> PLAYER_TRADE_LEVEL = CapabilityManager
			.get(new CapabilityToken<PlayerTradeLevel>() {
			});
	public static Capability<PlayerTradeRank> PLAYER_TRADE_RANK = CapabilityManager
			.get(new CapabilityToken<PlayerTradeRank>() {
			});
	public static Capability<PlayerSkillPoint> PLAYER_SKILL_POINT = CapabilityManager
			.get(new CapabilityToken<PlayerSkillPoint>() {
			});
	public static Capability<PlayerSkill1> PLAYER_SKILL_1 = CapabilityManager.get(new CapabilityToken<PlayerSkill1>() {
	});
	public static Capability<PlayerSkill2> PLAYER_SKILL_2 = CapabilityManager.get(new CapabilityToken<PlayerSkill2>() {
	});

	private PlayerTradeLevel tradeLv = null;
	private PlayerTradeRank tradeRank = null;
	private PlayerSkillPoint skillPoint = null;
	private PlayerSkill1 skill1 = null;
	private PlayerSkill2 skill2 = null;

	private final LazyOptional<PlayerTradeLevel> castTradeLv = LazyOptional.of(this::createPlayerTradeLevel);
	private final LazyOptional<PlayerTradeRank> castTradeRank = LazyOptional.of(this::createPlayerTradeRank);
	private final LazyOptional<PlayerSkillPoint> castSkillPoint = LazyOptional.of(this::createPlayerSkillPoint);
	private final LazyOptional<PlayerSkill1> castSkill1 = LazyOptional.of(this::createPlayerSkill1);
	private final LazyOptional<PlayerSkill2> castSkill2 = LazyOptional.of(this::createPlayerSkill2);

	private PlayerTradeLevel createPlayerTradeLevel() {
		if (this.tradeLv == null) {
			this.tradeLv = new PlayerTradeLevel();
		}

		return this.tradeLv;
	}

	private PlayerTradeRank createPlayerTradeRank() {
		if (this.tradeRank == null) {
			this.tradeRank = new PlayerTradeRank();
		}

		return this.tradeRank;
	}

	private PlayerSkillPoint createPlayerSkillPoint() {
		if (this.skillPoint == null) {
			this.skillPoint = new PlayerSkillPoint();
		}

		return this.skillPoint;
	}

	private PlayerSkill1 createPlayerSkill1() {
		if (this.skill1 == null) {
			this.skill1 = new PlayerSkill1();
		}

		return this.skill1;
	}

	private PlayerSkill2 createPlayerSkill2() {
		if (this.skill2 == null) {
			this.skill2 = new PlayerSkill2();
		}

		return this.skill2;
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capbility, @Nullable Direction dir) {
		if (capbility == PLAYER_TRADE_LEVEL) {
			return castTradeLv.cast();
		} else if (capbility == PLAYER_TRADE_RANK) {
			return castTradeRank.cast();
		} else if (capbility == PLAYER_SKILL_POINT) {
			return castSkillPoint.cast();
		} else if (capbility == PLAYER_SKILL_1) {
			return castSkill1.cast();
		} else if (capbility == PLAYER_SKILL_2) {
			return castSkill2.cast();
		}

		return LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createPlayerTradeLevel().saveNBTData(nbt);
		createPlayerTradeRank().saveNBTData(nbt);
		createPlayerSkillPoint().saveNBTData(nbt);
		createPlayerSkill1().saveNBTData(nbt);
		createPlayerSkill2().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createPlayerTradeLevel().loadNBTData(nbt);
		createPlayerTradeRank().loadNBTData(nbt);
		createPlayerSkillPoint().loadNBTData(nbt);
		createPlayerSkill1().loadNBTData(nbt);
		createPlayerSkill2().loadNBTData(nbt);
	}
}
