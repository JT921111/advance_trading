package com.jtdreamer.advance_trading.networking;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.networking.packet.Skill1Packet;
import com.jtdreamer.advance_trading.networking.packet.Skill1UpgradePacket;
import com.jtdreamer.advance_trading.networking.packet.Skill2Packet;
import com.jtdreamer.advance_trading.networking.packet.Skill2UpgradePacket;
import com.jtdreamer.advance_trading.networking.packet.SkillPointPacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelIncreasePacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelPacket;
import com.jtdreamer.advance_trading.networking.packet.TradeLevelResetPacket;
import com.jtdreamer.advance_trading.networking.packet.TradeRankPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {
	private static SimpleChannel INSTANCE;

	private static int packetId = 0;

	private static int id() {
		return packetId++;
	}

	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ModInit.MODID, "messages"))
				.networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
				.simpleChannel();

		INSTANCE = net;

		net.messageBuilder(TradeLevelIncreasePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(TradeLevelIncreasePacket::new).encoder(TradeLevelIncreasePacket::toBytes)
				.consumerMainThread(TradeLevelIncreasePacket::handle).add();

		net.messageBuilder(TradeLevelResetPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(TradeLevelResetPacket::new).encoder(TradeLevelResetPacket::toBytes)
				.consumerMainThread(TradeLevelResetPacket::handle).add();

		net.messageBuilder(Skill1UpgradePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(Skill1UpgradePacket::new).encoder(Skill1UpgradePacket::toBytes)
				.consumerMainThread(Skill1UpgradePacket::handle).add();

		net.messageBuilder(Skill2UpgradePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(Skill2UpgradePacket::new).encoder(Skill2UpgradePacket::toBytes)
				.consumerMainThread(Skill2UpgradePacket::handle).add();
		
		net.messageBuilder(TradeLevelPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(TradeLevelPacket::new).encoder(TradeLevelPacket::toBytes)
				.consumerMainThread(TradeLevelPacket::handle).add();
		
		net.messageBuilder(TradeRankPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(TradeRankPacket::new).encoder(TradeRankPacket::toBytes)
				.consumerMainThread(TradeRankPacket::handle).add();

		net.messageBuilder(SkillPointPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(SkillPointPacket::new).encoder(SkillPointPacket::toBytes)
				.consumerMainThread(SkillPointPacket::handle).add();

		net.messageBuilder(Skill1Packet.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(Skill1Packet::new).encoder(Skill1Packet::toBytes)
				.consumerMainThread(Skill1Packet::handle).add();

		net.messageBuilder(Skill2Packet.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(Skill2Packet::new).encoder(Skill2Packet::toBytes)
				.consumerMainThread(Skill2Packet::handle).add();
	}

	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}

	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
}
