package com.jtdreamer.advance_trading.event;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.capability.PlayerDataProvider;
import com.jtdreamer.advance_trading.capability.PlayerSkill1;
import com.jtdreamer.advance_trading.capability.PlayerSkill2;
import com.jtdreamer.advance_trading.capability.PlayerSkillPoint;
import com.jtdreamer.advance_trading.capability.PlayerTradeLevel;
import com.jtdreamer.advance_trading.capability.PlayerTradeRank;
import com.jtdreamer.advance_trading.client.ClientData;
import com.jtdreamer.advance_trading.item.ModItems;
import com.jtdreamer.advance_trading.networking.ModPackets;
import com.jtdreamer.advance_trading.networking.packet.Skill1Packet;
import com.jtdreamer.advance_trading.networking.packet.Skill2Packet;
import com.jtdreamer.advance_trading.networking.packet.SkillPointPacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelIncreasePacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelPacket;
import com.jtdreamer.advance_trading.networking.packet.TradeRankPacket;

import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModInit.MODID)
public class ModEvents {
	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			if (!event.getObject().getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).isPresent()
					|| !event.getObject().getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).isPresent()
					|| !event.getObject().getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).isPresent()
					|| !event.getObject().getCapability(PlayerDataProvider.PLAYER_SKILL_1).isPresent()
					|| !event.getObject().getCapability(PlayerDataProvider.PLAYER_SKILL_2).isPresent()) {
				event.addCapability(new ResourceLocation(ModInit.MODID, "properties"), new PlayerDataProvider());
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			event.getOriginal().getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
			event.getOriginal().getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
			event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
			event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
			event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_2).ifPresent(oldStore -> {
				event.getOriginal().getCapability(PlayerDataProvider.PLAYER_SKILL_2).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(PlayerTradeLevel.class);
		event.register(PlayerTradeRank.class);
		event.register(PlayerSkillPoint.class);
		event.register(PlayerSkill1.class);
		event.register(PlayerSkill2.class);
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER) {
			event.player.getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(data -> {
				ModPackets.sendToPlayer(new TradeLevelPacket(data.getTradeLv()), ((ServerPlayer) event.player));
			});
			event.player.getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(data -> {
				ModPackets.sendToPlayer(new TradeRankPacket(data.getTradeRank()), ((ServerPlayer) event.player));
			});
			event.player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(data -> {
				ModPackets.sendToPlayer(new SkillPointPacket(data.getSkillPoint()), ((ServerPlayer) event.player));
			});
			event.player.getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(data -> {
				ModPackets.sendToPlayer(new Skill1Packet(data.getSkill_1()), ((ServerPlayer) event.player));
			});
			event.player.getCapability(PlayerDataProvider.PLAYER_SKILL_2).ifPresent(data -> {
				ModPackets.sendToPlayer(new Skill2Packet(data.getSkill_2()), ((ServerPlayer) event.player));
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide()) {
			if (event.getEntity() instanceof ServerPlayer player) {
				player.getCapability(PlayerDataProvider.PLAYER_TRADE_LEVEL).ifPresent(data -> {
					ModPackets.sendToPlayer(new TradeLevelPacket(data.getTradeLv()), player);
				});
				player.getCapability(PlayerDataProvider.PLAYER_TRADE_RANK).ifPresent(data -> {
					ModPackets.sendToPlayer(new TradeRankPacket(data.getTradeRank()), player);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_POINT).ifPresent(data -> {
					ModPackets.sendToPlayer(new SkillPointPacket(data.getSkillPoint()), player);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_1).ifPresent(data -> {
					ModPackets.sendToPlayer(new Skill1Packet(data.getSkill_1()), player);
				});
				player.getCapability(PlayerDataProvider.PLAYER_SKILL_2).ifPresent(data -> {
					ModPackets.sendToPlayer(new Skill2Packet(data.getSkill_2()), player);
				});
			}
		}
	}

	@SubscribeEvent
	public static void tradeLevelRaise(AdvancementEvent event) {
		if (event.getAdvancement().getId().equals(new ResourceLocation("advance_trading:trade_level"))) {
			ModPackets.sendToServer(new TradeLevelIncreasePacket());
			ServerPlayer player = (ServerPlayer) event.getEntity();
			AdvancementProgress advancementprogress = player.getAdvancements()
					.getOrStartProgress(event.getAdvancement());
			for (String string : advancementprogress.getCompletedCriteria()) {
				player.getAdvancements().revoke(event.getAdvancement(), string);
			}
			if (ClientData.getPlayerSkill1() > 0 && (Math.random() * 100) < ClientData.getPlayerSkill1()) {
				player.drop(new ItemStack(Items.EMERALD, ClientData.getPlayerSkill2() + 1), false);
			}
		}
	}

	@SubscribeEvent
	public static void stockIncreasing(EntityInteract event) {
		Player player = event.getEntity();
		Entity entity = event.getTarget();
		if (player.getMainHandItem().is(ModItems.STOCK_INCREASE.get()) && entity instanceof Villager) {
			if (!player.isCreative()) {
				player.getMainHandItem().setCount(player.getMainHandItem().getCount() - 1);
			}
			Villager villager = (Villager) entity;
			MerchantOffers trades = villager.getOffers();
			int l = trades.toArray().length;
			for (int i = 0; i < l; i++) {
				MerchantOffer trade = villager.getOffers().get(i);
				trades.set(i,
						new MerchantOffer(trade.getBaseCostA(), trade.getCostB(), trade.getResult(), trade.getUses(),
								trade.getMaxUses() + 4, trade.getXp(), trade.getPriceMultiplier(), trade.getDemand()));
			}
		}
	}
}
