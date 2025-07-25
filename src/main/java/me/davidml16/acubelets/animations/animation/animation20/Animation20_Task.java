package me.davidml16.acubelets.animations.animation.animation20;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.cryptomorin.xseries.XMaterial;
import me.davidml16.acubelets.Main;
import me.davidml16.acubelets.animations.Animation;
import me.davidml16.acubelets.animations.AnimationSettings;
import me.davidml16.acubelets.animations.FakeBlock;
import me.davidml16.acubelets.utils.LocationUtils;
import me.davidml16.acubelets.utils.ParticlesAPI.Particles;
import me.davidml16.acubelets.utils.ParticlesAPI.UtilParticles;
import me.davidml16.acubelets.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class Animation20_Task extends Animation {

	private Location rotationLocation;
	private LivingEntity enderman;
	public Animation20_Task(Main main, AnimationSettings animationSettings) {
		super(main, animationSettings);
	}

	@Override
	public void onTick(int time) {

		if (time == 50) {

			enderman = (LivingEntity) getCubeletBox().getLocation()
					.getWorld()
					.spawnEntity(rotationLocation, EntityType.ENDERMAN);

			UtilParticles.display(Particles.FIREWORKS_SPARK, 0.35, 0.1, 0.35, enderman.getLocation(), 10);

			Sounds.playSound(getCubeletBox().getLocation(), Sounds.MySound.ENDERMAN_TELEPORT, 0.5F, 2F);

			enderman.setCollidable(false);

			((Enderman) enderman).setCarriedBlock(Bukkit.createBlockData(XMaterial.CHEST.parseMaterial()));

			enderman.setRemoveWhenFarAway(false);
			enderman.setMetadata("ACUBELETS", new FixedMetadataValue(getMain(), Boolean.TRUE));

			enderman.setAI(false);
			enderman.setSilent(true);
			enderman.setInvulnerable(true);
		}

		if (time > 50 && time < 90) {

			if (enderman != null) enderman.teleport(rotationLocation);

		}

		if (time == 70) {

			if (enderman != null)
				((Enderman) enderman).setCarriedBlock(Bukkit.createBlockData(XMaterial.AIR.parseMaterial()));

			Location eye = getBoxLocation().clone().add(0.0D, 0.4D, 0.0D);
			for (Location location2 : LocationUtils.getCircle(getBoxLocation().clone().add(0, 0.75, 0), 0.25D, 50)) {
				Vector direction = location2.toVector()
						.subtract(getBoxLocation().clone().add(0, 0.75, 0).toVector())
						.normalize();
				UtilParticles.display(Particles.CLOUD, direction, eye, 0.3F);
			}

			placeOrientedChest(getBoxLocation());
			Sounds.playSound(getCubeletBox().getLocation(), Sounds.MySound.CHICKEN_EGG_POP, 0.5F, 0F);

		}

		if (time == 90) {

			if (enderman != null) {

				UtilParticles.display(Particles.FIREWORKS_SPARK, 0.35, 0.1, 0.35, enderman.getLocation(), 10);
				enderman.remove();

			}

			Sounds.playSound(getCubeletBox().getLocation(), Sounds.MySound.ENDERMAN_TELEPORT, 0.5F, 2F);

		}


		if (time == 120) {

			setChestOpened(getBoxLocation().getBlock(), true);
			Sounds.playSound(getCubeletBox().getLocation(), Sounds.MySound.CHEST_OPEN, 0.5F, 1F);

		} else if (time == 123) {

			doPreRewardReveal();

		}

	}

	@Override
	public void onStart() {

		rotationLocation = getLocationRotation(0);

		setAnimationBlocks(new Animation20_Blocks(getCubeletBox().getLocation()));
		startAnimationBlocks(6L);

		setColors(Arrays.asList(Color.BLACK, Color.PURPLE));

	}

	@Override
	public void onStop() {

		stopAnimationBlocks();

	}

	@Override
	public void onPreRewardReveal() {

		getMain().getFireworkUtil()
				.spawn(getCubeletBox().getLocation()
						.clone()
						.add(0.5, 0.35, 0.5), FireworkEffect.Type.BALL_LARGE, getColors().get(0), getColors().get(1));

	}

	@Override
	public void onRewardReveal() {

	}

	@Override
	public void onRewardDuplication() {
	}

	public void placeOrientedChest(Location loc) {

		BlockFace blockFace;

		switch (getCubeletBox().getRotation()) {
			case SOUTH:
				blockFace = BlockFace.SOUTH;
				break;
			case EAST:
				blockFace = BlockFace.EAST;
				break;
			case WEST:
				blockFace = BlockFace.WEST;
				break;
			default:
				blockFace = BlockFace.NORTH;
				break;
		}

		getAnimationBlocks().setStepFakeBlocks(1, new FakeBlock[]{ new FakeBlock(loc, XMaterial.CHEST, blockFace) });

	}

	public void setChestOpened(Block block, boolean opened) {
		PacketContainer libPacket = new PacketContainer(PacketType.Play.Server.BLOCK_ACTION);
		libPacket.getBlockPositionModifier().write(0, new BlockPosition(block.getX(), block.getY(), block.getZ()));
		libPacket.getIntegers().write(0, 1);
		libPacket.getIntegers().write(1, opened ? 1 : 0);
		libPacket.getBlocks().write(0, block.getType());
		int distanceSquared = 64 * 64;
		Location loc = block.getLocation();
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		for (Player player : block.getWorld().getPlayers()) {
			if (player.getLocation().distanceSquared(loc) < distanceSquared) {
				manager.sendServerPacket(player, libPacket);
			}
		}
	}

}
