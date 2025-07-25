package me.davidml16.acubelets.utils.ParticlesAPI;

import me.davidml16.acubelets.utils.Version;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <b>ParticleEffect Library</b>
 * <p>
 * This library was created by @DarkBlade12 and allows you to display all Minecraft particle effects on a Bukkit server
 * <p>
 * You are welcome to use it, modify it and redistribute it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * Special thanks:
 * <ul>
 * <li>@microgeek (original idea, names and packet parameters)
 * <li>@ShadyPotato (1.8 names, ids and packet parameters)
 * <li>@RingOfStorms (particle behavior)
 * <li>@Cybermaxke (particle behavior)
 * <li>@JamieSinn (hosting a jenkins server and documentation for particleeffect)
 * </ul>
 * <p>
 * <i>It would be nice if you provide credit to me if you use this class in a published project</i>
 *
 * @author DarkBlade12
 * @version 1.7
 */
public enum Particles {
	/**
	 * A particle effect which is displayed by exploding tnt and creepers:
	 * <ul>
	 * <li>It looks like a white cloud
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	EXPLOSION_NORMAL("explode", 0, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by exploding ghast fireballs and wither skulls:
	 * <ul>
	 * <li>It looks like a gray ball which is fading away
	 * <li>The speed value slightly influences the size of this particle effect
	 * </ul>
	 */
	EXPLOSION_LARGE("largeexplode", 1, -1),
	/**
	 * A particle effect which is displayed by exploding tnt and creepers:
	 * <ul>
	 * <li>It looks like a crowd of gray balls which are fading away
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	EXPLOSION_HUGE("hugeexplosion", 2, -1),
	/**
	 * A particle effect which is displayed by launching fireworks:
	 * <ul>
	 * <li>It looks like a white star which is sparkling
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	FIREWORKS_SPARK("fireworksSpark", 3, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by swimming entities and arrows in water:
	 * <ul>
	 * <li>It looks like a bubble
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	WATER_BUBBLE("bubble", 4, -1, ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_WATER),
	/**
	 * A particle effect which is displayed by swimming entities and shaking wolves:
	 * <ul>
	 * <li>It looks like a blue drop
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	WATER_SPLASH("splash", 5, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed on water when fishing:
	 * <ul>
	 * <li>It looks like a blue droplet
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	WATER_WAKE("wake", 6, 7, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by water:
	 * <ul>
	 * <li>It looks like a tiny blue square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SUSPENDED("suspended", 7, -1, ParticleProperty.REQUIRES_WATER),
	/**
	 * A particle effect which is displayed by air when close to bedrock and the in the void:
	 * <ul>
	 * <li>It looks like a tiny gray square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SUSPENDED_DEPTH("depthSuspend", 8, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed when landing a critical hit and by arrows:
	 * <ul>
	 * <li>It looks like a light brown cross
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	CRIT("crit", 9, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed when landing a hit with an enchanted weapon:
	 * <ul>
	 * <li>It looks like a cyan star
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	CRIT_MAGIC("magicCrit", 10, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by primed tnt, torches, droppers, dispensers, end portals, brewing stands
	 * and monster spawners:
	 * <ul>
	 * <li>It looks like a little gray cloud
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	SMOKE_NORMAL("smoke", 11, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by fire, minecarts with furnace and blazes:
	 * <ul>
	 * <li>It looks like a large gray cloud
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	SMOKE_LARGE("largesmoke", 12, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed when splash potions or bottles o' enchanting hit something:
	 * <ul>
	 * <li>It looks like a white swirl
	 * <li>The speed value causes the particle to only move upwards when set to 0
	 * <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
	 * </ul>
	 */
	SPELL("spell", 13, -1),
	/**
	 * A particle effect which is displayed when instant splash potions hit something:
	 * <ul>
	 * <li>It looks like a white cross
	 * <li>The speed value causes the particle to only move upwards when set to 0
	 * <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
	 * </ul>
	 */
	SPELL_INSTANT("instantSpell", 14, -1),
	/**
	 * A particle effect which is displayed by entities with active potion effects:
	 * <ul>
	 * <li>It looks like a colored swirl
	 * <li>The speed value causes the particle to be colored black when set to 0
	 * <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
	 * </ul>
	 */
	SPELL_MOB("mobSpell", 15, -1, ParticleProperty.COLORABLE),
	/**
	 * A particle effect which is displayed by entities with active potion effects applied through a beacon:
	 * <ul>
	 * <li>It looks like a transparent colored swirl
	 * <li>The speed value causes the particle to be always colored black when set to 0
	 * <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
	 * </ul>
	 */
	SPELL_MOB_AMBIENT("mobSpellAmbient", 16, -1, ParticleProperty.COLORABLE),
	/**
	 * A particle effect which is displayed by witches:
	 * <ul>
	 * <li>It looks like a purple cross
	 * <li>The speed value causes the particle to only move upwards when set to 0
	 * <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
	 * </ul>
	 */
	SPELL_WITCH("witchMagic", 17, -1),
	/**
	 * A particle effect which is displayed by BLOCKS beneath a water source:
	 * <ul>
	 * <li>It looks like a blue drip
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	DRIP_WATER("dripWater", 18, -1),
	/**
	 * A particle effect which is displayed by BLOCKS beneath a lava source:
	 * <ul>
	 * <li>It looks like an orange drip
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	DRIP_LAVA("dripLava", 19, -1),
	/**
	 * A particle effect which is displayed when attacking a villager in a village:
	 * <ul>
	 * <li>It looks like a cracked gray heart
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	VILLAGER_ANGRY("angryVillager", 20, -1),
	/**
	 * A particle effect which is displayed when using bone meal and trading with a villager in a village:
	 * <ul>
	 * <li>It looks like a green star
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	VILLAGER_HAPPY("happyVillager", 21, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by mycelium:
	 * <ul>
	 * <li>It looks like a tiny gray square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	TOWN_AURA("townaura", 22, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by note BLOCKS:
	 * <ul>
	 * <li>It looks like a colored note
	 * <li>The speed value causes the particle to be colored green when set to 0
	 * </ul>
	 */
	NOTE("note", 23, -1, ParticleProperty.COLORABLE),
	/**
	 * A particle effect which is displayed by nether portals, endermen, ender pearls, eyes of ender, ender chests and
	 * dragon eggs:
	 * <ul>
	 * <li>It looks like a purple cloud
	 * <li>The speed value influences the spread of this particle effect
	 * </ul>
	 */
	PORTAL("portal", 24, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by enchantment tables which are nearby bookshelves:
	 * <ul>
	 * <li>It looks like a cryptic white letter
	 * <li>The speed value influences the spread of this particle effect
	 * </ul>
	 */
	ENCHANTMENT_TABLE("enchantmenttable", 25, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by torches, active furnaces, magma cubes and monster spawners:
	 * <ul>
	 * <li>It looks like a tiny flame
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	FLAME("flame", 26, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by lava:
	 * <ul>
	 * <li>It looks like a spark
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	LAVA("lava", 27, -1),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It looks like a transparent gray square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
//	FOOTSTEP("footstep", 28, -1), Doesn't support 1.13 Particle
	/**
	 * A particle effect which is displayed when a mob dies:
	 * <ul>
	 * <li>It looks like a large white cloud
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	CLOUD("cloud", 29, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by redstone ore, powered redstone, redstone torches and redstone repeaters:
	 * <ul>
	 * <li>It looks like a tiny colored cloud
	 * <li>The speed value causes the particle to be colored red when set to 0
	 * </ul>
	 */
	REDSTONE("reddust", 30, -1, ParticleProperty.COLORABLE),
	/**
	 * A particle effect which is displayed when snowballs hit a block:
	 * <ul>
	 * <li>It looks like a little piece with the snowball texture
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SNOWBALL("snowballpoof", 31, -1),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It looks like a tiny white cloud
	 * <li>The speed value influences the velocity at which the particle flies off
	 * </ul>
	 */
	SNOW_SHOVEL("snowshovel", 32, -1, ParticleProperty.DIRECTIONAL),
	/**
	 * A particle effect which is displayed by slimes:
	 * <ul>
	 * <li>It looks like a tiny part of the slimeball icon
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SLIME("slime", 33, -1),
	/**
	 * A particle effect which is displayed when breeding and taming animals:
	 * <ul>
	 * <li>It looks like a red heart
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	HEART("heart", 34, -1),
	/**
	 * A particle effect which is displayed by barriers:
	 * <ul>
	 * <li>It looks like a red box with a slash through it
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	BARRIER("barrier", 35, 8),
	/**
	 * A particle effect which is displayed when breaking a tool or eggs hit a block:
	 * <ul>
	 * <li>It looks like a little piece with an item texture
	 * </ul>
	 */
	ITEM_CRACK("iconcrack", 36, -1, ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA),
	/**
	 * A particle effect which is displayed when breaking BLOCKS or sprinting:
	 * <ul>
	 * <li>It looks like a little piece with a block texture
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	BLOCK_CRACK("blockcrack", 37, -1, ParticleProperty.REQUIRES_DATA),
	/**
	 * A particle effect which is displayed when falling:
	 * <ul>
	 * <li>It looks like a little piece with a block texture
	 * </ul>
	 */
	BLOCK_DUST("blockdust", 38, 7, ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA),
	/**
	 * A particle effect which is displayed when rain hits the ground:
	 * <ul>
	 * <li>It looks like a blue droplet
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	WATER_DROP("droplet", 39, 8),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It has no visual effect
	 * </ul>
	 */
//	ITEM_TAKE("take", 40, 8), Doesn't support 1.13 Particle
	/**
	 * A particle effect which is displayed by elder guardians:
	 * <ul>
	 * <li>It looks like the shape of the elder guardian
	 * <li>The speed value has no influence on this particle effect
	 * <li>The offset values have no influence on this particle effect
	 * </ul>
	 */
	MOB_APPEARANCE("mobappearance", 41, 8);

	private static final Map<String, Particles> NAME_MAP = new HashMap<>();
	private static final Map<Integer, Particles> ID_MAP = new HashMap<>();

	// Initialize map for quick name and id lookup
	static {
		for (Particles effect : values()) {
			NAME_MAP.put(effect.name, effect);
			ID_MAP.put(effect.id, effect);
		}
	}

	private final String name;
	private final int id;
	private final int requiredVersion;
	private final List<ParticleProperty> properties;

	/**
	 * Construct a new particle effect
	 *
	 * @param name            Name of this particle effect
	 * @param id              Id of this particle effect
	 * @param requiredVersion Version which is required (1.x)
	 * @param properties      Properties of this particle effect
	 */
	Particles(String name, int id, int requiredVersion, ParticleProperty... properties) {
		this.name = name;
		this.id = id;
		this.requiredVersion = requiredVersion;
		this.properties = Arrays.asList(properties);
	}

	/**
	 * Returns the particle effect with the given name
	 *
	 * @param name Name of the particle effect
	 * @return The particle effect
	 */
	public static Particles fromName(String name) {
		for (Entry<String, Particles> entry : NAME_MAP.entrySet()) {
			if (!entry.getKey().equalsIgnoreCase(name)) {
				continue;
			}
			return entry.getValue();
		}
		return null;
	}

	/**
	 * Returns the particle effect with the given id
	 *
	 * @param id Id of the particle effect
	 * @return The particle effect
	 */
	public static Particles fromId(int id) {
		for (Entry<Integer, Particles> entry : ID_MAP.entrySet()) {
			if (entry.getKey() != id) {
				continue;
			}
			return entry.getValue();
		}
		return null;
	}

	/**
	 * Determine if the distance between @param location and one of the players exceeds 256
	 *
	 * @param location Location to check
	 * @return Whether the distance exceeds 256 or not
	 */
	private static boolean isLongDistance(Location location, List<Player> players) {
		String world = location.getWorld().getName();
		for (Player player : players) {
			Location playerLocation = player.getLocation();
			if (!world.equals(playerLocation.getWorld()
					.getName()) || playerLocation.distanceSquared(location) < 65536) {
				continue;
			}
			return true;
		}
		return false;
	}

	/**
	 * Determine if the data type for a particle effect is correct
	 *
	 * @param effect Particle effect
	 * @param data   Particle data
	 * @return Whether the data type is correct or not
	 */
	private static boolean isDataCorrect(Particles effect, ParticleData data) {
		return ((effect == BLOCK_CRACK || effect == BLOCK_DUST) && data instanceof BlockData) || (effect == ITEM_CRACK && data instanceof ItemData);
	}

	/**
	 * Determine if the color type for a particle effect is correct
	 *
	 * @param effect Particle effect
	 * @param color  Particle color
	 * @return Whether the color type is correct or not
	 */
	private static boolean isColorCorrect(Particles effect, ParticleColor color) {
		return ((effect == SPELL_MOB || effect == SPELL_MOB_AMBIENT || effect == REDSTONE) && color instanceof OrdinaryColor) || (effect == NOTE && color instanceof NoteColor);
	}

	/**
	 * Returns the name of this particle effect
	 *
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the id of this particle effect
	 *
	 * @return The id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the required version for this particle effect (1.x)
	 *
	 * @return The required version
	 */
	public int getRequiredVersion() {
		return requiredVersion;
	}

	/**
	 * Determine if this particle effect has a specific property
	 *
	 * @return Whether it has the property or not
	 */
	public boolean hasProperty(ParticleProperty property) {
		return properties.contains(property);
	}

	/**
	 * Determine if this particle effect is supported by your current server version
	 *
	 * @return Whether the particle effect is supported or not
	 */
	public boolean isSupported() {
		return requiredVersion == -1 || ParticlePacket.getVersion() >= requiredVersion;
	}

	/**
	 * Displays a particle effect which is only visible for all players within a certain range in the world of @param
	 * center
	 *
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param range   Range of the visibility
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect requires water and none is at the center location
	 * @see ParticlePacket
	 * @see ParticlePacket#sendTo(Location)
	 */
	public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect requires additional data");
		}
		if (hasProperty(ParticleProperty.REQUIRES_WATER)) {
			throw new IllegalArgumentException("There is no water at the center location");
		}
		new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256, null).sendTo(center);
	}

	/**
	 * Displays a particle effect which is only visible for the specified players
	 *
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect requires water and none is at the center location
	 * @see ParticlePacket
	 * @see ParticlePacket#sendTo(Location)
	 */
	public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect requires additional data");
		}
		if (hasProperty(ParticleProperty.REQUIRES_WATER)) {
			throw new IllegalArgumentException("There is no water at the center location");
		}
		new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), null).sendTo(center);
	}

	/**
	 * Displays a particle effect which is only visible for the specified players
	 *
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect requires water and none is at the center location
	 * @see #display(float, float, float, float, int, Location, List)
	 */
	public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player... players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		display(offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
	}

	/**
	 * Displays a single particle which flies into a determined direction and is only visible for all players within a
	 * certain range in the world of @param center
	 *
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particle
	 * @param center    Center location of the effect
	 * @param range     Range of the visibility
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect is not directional or if it requires water and none is at
	 *                                  the center location
	 * @see ParticlePacket#ParticlePacket(Particles, Vector, float, boolean, ParticleData)
	 */
	public void display(Vector direction, float speed, Location center, double range) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect requires additional data");
		}
		if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
			throw new IllegalArgumentException("This particle effect is not directional");
		}
		if (hasProperty(ParticleProperty.REQUIRES_WATER)) {
			throw new IllegalArgumentException("There is no water at the center location");
		}
		new ParticlePacket(this, direction, speed, range > 256, null).sendTo(center);
	}

	/**
	 * Displays a single particle which flies into a determined direction and is only visible for the specified players
	 *
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particle
	 * @param center    Center location of the effect
	 * @param players   Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect is not directional or if it requires water and none is at
	 *                                  the center location
	 * @see ParticlePacket#ParticlePacket(Particles, Vector, float, boolean, ParticleData)
	 */
	public void display(Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect requires additional data");
		}
		if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
			throw new IllegalArgumentException("This particle effect is not directional");
		}
		if (hasProperty(ParticleProperty.REQUIRES_WATER)) {
			throw new IllegalArgumentException("There is no water at the center location");
		}
		new ParticlePacket(this, direction, speed, isLongDistance(center, players), null).sendTo(center);
	}

	/**
	 * Displays a single particle which flies into a determined direction and is only visible for the specified players
	 *
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particle
	 * @param center    Center location of the effect
	 * @param players   Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect requires additional data
	 * @throws IllegalArgumentException If the particle effect is not directional or if it requires water and none is at
	 *                                  the center location
	 * @see #display(Vector, float, Location, List)
	 */
	public void display(Vector direction, float speed, Location center, Player... players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
		display(direction, speed, center, Arrays.asList(players));
	}

	/**
	 * Displays a single particle which is colored and only visible for all players within a certain range in the world
	 * of @param center
	 *
	 * @param color  Color of the particle
	 * @param center Center location of the effect
	 * @param range  Range of the visibility
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleColorException   If the particle effect is not colorable or the color type is incorrect
	 * @see ParticlePacket#ParticlePacket(Particles, ParticleColor, boolean)
	 * @see ParticlePacket#sendTo(Location)
	 */
	public void display(ParticleColor color, Location center, double range) throws ParticleVersionException, ParticleColorException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.COLORABLE)) {
			throw new ParticleColorException("This particle effect is not colorable");
		}
		if (!isColorCorrect(this, color)) {
			throw new ParticleColorException("The particle color type is incorrect");
		}
		new ParticlePacket(this, color, range > 256).sendTo(center);
	}

	/**
	 * Displays a single particle which is colored and only visible for the specified players
	 *
	 * @param color   Color of the particle
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleColorException   If the particle effect is not colorable or the color type is incorrect
	 * @see ParticlePacket#ParticlePacket(Particles, ParticleColor, boolean)
	 */
	public void display(ParticleColor color, Location center, List<Player> players) throws ParticleVersionException, ParticleColorException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.COLORABLE)) {
			throw new ParticleColorException("This particle effect is not colorable");
		}
		if (!isColorCorrect(this, color)) {
			throw new ParticleColorException("The particle color type is incorrect");
		}
		new ParticlePacket(this, color, isLongDistance(center, players)).sendTo(center);
	}

	/**
	 * Displays a single particle which is colored and only visible for the specified players
	 *
	 * @param color   Color of the particle
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleColorException   If the particle effect is not colorable or the color type is incorrect
	 * @see #display(ParticleColor, Location, List)
	 */
	public void display(ParticleColor color, Location center, Player... players) throws ParticleVersionException, ParticleColorException {
		display(color, center, Arrays.asList(players));
	}

	/**
	 * Displays a particle effect which requires additional data and is only visible for all players within a certain
	 * range in the world of @param center
	 *
	 * @param data    Data of the effect
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param range   Range of the visibility
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see ParticlePacket
	 */
	public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException, ParticleDataException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect does not require additional data");
		}
		if (!isDataCorrect(this, data)) {
			throw new ParticleDataException("The particle data type is incorrect");
		}
		new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256, data).sendTo(center);
	}

	/**
	 * Displays a particle effect which requires additional data and is only visible for the specified players
	 *
	 * @param data    Data of the effect
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see ParticlePacket
	 */
	public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect does not require additional data");
		}
		if (!isDataCorrect(this, data)) {
			throw new ParticleDataException("The particle data type is incorrect");
		}
		new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), data).sendTo(center);
	}

	/**
	 * Displays a particle effect which requires additional data and is only visible for the specified players
	 *
	 * @param data    Data of the effect
	 * @param offsetX Maximum distance particles can fly away from the center on the x-axis
	 * @param offsetY Maximum distance particles can fly away from the center on the y-axis
	 * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
	 * @param speed   Display speed of the particles
	 * @param amount  Amount of particles
	 * @param center  Center location of the effect
	 * @param players Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see #display(ParticleData, float, float, float, float, int, Location, List)
	 */
	public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player... players) throws ParticleVersionException, ParticleDataException {
		display(data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
	}

	/**
	 * Displays a single particle which requires additional data that flies into a determined direction and is only
	 * visible for all players within a certain range in the world of @param center
	 *
	 * @param data      Data of the effect
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particles
	 * @param center    Center location of the effect
	 * @param range     Range of the visibility
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see ParticlePacket
	 */
	public void display(ParticleData data, Vector direction, float speed, Location center, double range) throws ParticleVersionException, ParticleDataException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect does not require additional data");
		}
		if (!isDataCorrect(this, data)) {
			throw new ParticleDataException("The particle data type is incorrect");
		}
		new ParticlePacket(this, direction, speed, range > 256, data).sendTo(center);
	}

	/**
	 * Displays a single particle which requires additional data that flies into a determined direction and is only
	 * visible for the specified players
	 *
	 * @param data      Data of the effect
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particles
	 * @param center    Center location of the effect
	 * @param players   Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see ParticlePacket
	 */
	public void display(ParticleData data, Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
		if (!isSupported()) {
			throw new ParticleVersionException("This particle effect is not supported by your server version");
		}
		if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException("This particle effect does not require additional data");
		}
		if (!isDataCorrect(this, data)) {
			throw new ParticleDataException("The particle data type is incorrect");
		}
		new ParticlePacket(this, direction, speed, isLongDistance(center, players), data).sendTo(center);
	}

	/**
	 * Displays a single particle which requires additional data that flies into a determined direction and is only
	 * visible for the specified players
	 *
	 * @param data      Data of the effect
	 * @param direction Direction of the particle
	 * @param speed     Display speed of the particles
	 * @param center    Center location of the effect
	 * @param players   Receivers of the effect
	 * @throws ParticleVersionException If the particle effect is not supported by the server version
	 * @throws ParticleDataException    If the particle effect does not require additional data or if the data type is
	 *                                  incorrect
	 * @see #display(ParticleData, Vector, float, Location, List)
	 */
	public void display(ParticleData data, Vector direction, float speed, Location center, Player... players) throws ParticleVersionException, ParticleDataException {
		display(data, direction, speed, center, Arrays.asList(players));
	}

	/**
	 * Represents the property of a particle effect
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.7
	 */
	public enum ParticleProperty {
		/**
		 * The particle effect requires water to be displayed
		 */
		REQUIRES_WATER,
		/**
		 * The particle effect requires block or item data to be displayed
		 */
		REQUIRES_DATA,
		/**
		 * The particle effect uses the offsets as direction values
		 */
		DIRECTIONAL,
		/**
		 * The particle effect uses the offsets as color values
		 */
		COLORABLE
	}

	/**
	 * Represents the particle data for effects like {@link Particles#ITEM_CRACK}, {@link Particles#BLOCK_CRACK} and
	 * {@link Particles#BLOCK_DUST}
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.6
	 */
	public static abstract class ParticleData {
		private final Material material;
		private final byte data;
		private final int[] packetData;

		/**
		 * Construct a new particle data
		 *
		 * @param material Material of the item/block
		 * @param data     Data value of the item/block
		 */
		@SuppressWarnings( "deprecation" )
		public ParticleData(Material material, byte data) {
			this.material = material;
			this.data = data;
			this.packetData = new int[]{ material.getId(), data };
		}

		/**
		 * Returns the material of this data
		 *
		 * @return The material
		 */
		public Material getMaterial() {
			return material;
		}

		/**
		 * Returns the data value of this data
		 *
		 * @return The data value
		 */
		public byte getData() {
			return data;
		}

		/**
		 * Returns the data as an int array for packet construction
		 *
		 * @return The data for the packet
		 */
		public int[] getPacketData() {
			return packetData;
		}

		/**
		 * Returns the data as a string for pre 1.8 versions
		 *
		 * @return The data string for the packet
		 */
		public String getPacketDataString() {
			return "_" + packetData[0] + "_" + packetData[1];
		}
	}

	/**
	 * Represents the item data for the {@link Particles#ITEM_CRACK} effect
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.6
	 */
	public static final class ItemData extends ParticleData {
		/**
		 * Construct a new item data
		 *
		 * @param material Material of the item
		 * @param data     Data value of the item
		 * @see ParticleData#ParticleData(Material, byte)
		 */
		public ItemData(Material material, byte data) {
			super(material, data);
		}
	}

	/**
	 * Represents the block data for the {@link Particles#BLOCK_CRACK} and {@link Particles#BLOCK_DUST} effects
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.6
	 */
	public static final class BlockData extends ParticleData {
		/**
		 * Construct a new block data
		 *
		 * @param material Material of the block
		 * @param data     Data value of the block
		 * @throws IllegalArgumentException If the material is not a block
		 * @see ParticleData#ParticleData(Material, byte)
		 */
		public BlockData(Material material, byte data) throws IllegalArgumentException {
			super(material, data);
			if (!material.isBlock()) {
				throw new IllegalArgumentException("The material is not a block");
			}
		}
	}

	/**
	 * Represents the color for effects like {@link Particles#SPELL_MOB}, {@link Particles#SPELL_MOB_AMBIENT},
	 * {@link Particles#REDSTONE} and {@link Particles#NOTE}
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.7
	 */
	public static abstract class ParticleColor {
		/**
		 * Returns the value for the offsetX field
		 *
		 * @return The offsetX value
		 */
		public abstract float getValueX();

		/**
		 * Returns the value for the offsetY field
		 *
		 * @return The offsetY value
		 */
		public abstract float getValueY();

		/**
		 * Returns the value for the offsetZ field
		 *
		 * @return The offsetZ value
		 */
		public abstract float getValueZ();
	}

	/**
	 * Represents the color for effects like {@link Particles#SPELL_MOB}, {@link Particles#SPELL_MOB_AMBIENT} and
	 * {@link Particles#NOTE}
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.7
	 */
	public static final class OrdinaryColor extends ParticleColor {
		private final int red;
		private final int green;
		private final int blue;

		/**
		 * Construct a new ordinary color
		 *
		 * @param red   Red value of the RGB format
		 * @param green Green value of the RGB format
		 * @param blue  Blue value of the RGB format
		 * @throws IllegalArgumentException If one of the values is lower than 0 or higher than 255
		 */
		public OrdinaryColor(int red, int green, int blue) throws IllegalArgumentException {
			this.red = Math.min(255, red);
			this.green = Math.min(255, green);
			this.blue = Math.min(255, blue);
		}

		/**
		 * Construct a new ordinary color
		 *
		 * @param color Bukkit color
		 */
		public OrdinaryColor(Color color) {
			this(color.getRed(), color.getGreen(), color.getBlue());
		}

		/**
		 * Returns the red value of the RGB format
		 *
		 * @return The red value
		 */
		public int getRed() {
			return red;
		}

		/**
		 * Returns the green value of the RGB format
		 *
		 * @return The green value
		 */
		public int getGreen() {
			return green;
		}

		/**
		 * Returns the blue value of the RGB format
		 *
		 * @return The blue value
		 */
		public int getBlue() {
			return blue;
		}

		/**
		 * Returns the red value divided by 255
		 *
		 * @return The offsetX value
		 */
		@Override
		public float getValueX() {
			return (float) red / 255F;
		}

		/**
		 * Returns the green value divided by 255
		 *
		 * @return The offsetY value
		 */
		@Override
		public float getValueY() {
			return (float) green / 255F;
		}

		/**
		 * Returns the blue value divided by 255
		 *
		 * @return The offsetZ value
		 */
		@Override
		public float getValueZ() {
			return (float) blue / 255F;
		}
	}

	/**
	 * Represents the color for the {@link Particles#NOTE} effect
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.7
	 */
	public static final class NoteColor extends ParticleColor {
		private final int note;

		/**
		 * Construct a new note color
		 *
		 * @param note Note id which determines color
		 * @throws IllegalArgumentException If the note value is lower than 0 or higher than 24
		 */
		public NoteColor(int note) throws IllegalArgumentException {
			if (note < 0) {
				throw new IllegalArgumentException("The note value is lower than 0");
			}
			if (note > 24) {
				throw new IllegalArgumentException("The note value is higher than 24");
			}
			this.note = note;
		}

		/**
		 * Returns the note value divided by 24
		 *
		 * @return The offsetX value
		 */
		@Override
		public float getValueX() {
			return (float) note / 24F;
		}

		/**
		 * Returns zero because the offsetY value is unused
		 *
		 * @return zero
		 */
		@Override
		public float getValueY() {
			return 0;
		}

		/**
		 * Returns zero because the offsetZ value is unused
		 *
		 * @return zero
		 */
		@Override
		public float getValueZ() {
			return 0;
		}

	}

	/**
	 * Represents a runtime exception that is thrown either if the displayed particle effect requires data and has none
	 * or vice-versa or if the data type is incorrect
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.6
	 */
	private static final class ParticleDataException extends RuntimeException {
		private static final long serialVersionUID = 3203085387160737484L;

		/**
		 * Construct a new particle data exception
		 *
		 * @param message Message that will be logged
		 */
		public ParticleDataException(String message) {
			super(message);
		}
	}

	/**
	 * Represents a runtime exception that is thrown either if the displayed particle effect is not colorable or if the
	 * particle color type is incorrect
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.7
	 */
	private static final class ParticleColorException extends RuntimeException {
		private static final long serialVersionUID = 3203085387160737484L;

		/**
		 * Construct a new particle color exception
		 *
		 * @param message Message that will be logged
		 */
		public ParticleColorException(String message) {
			super(message);
		}
	}

	/**
	 * Represents a runtime exception that is thrown if the displayed particle effect requires a newer version
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.6
	 */
	private static final class ParticleVersionException extends RuntimeException {
		private static final long serialVersionUID = 3203085387160737484L;

		/**
		 * Construct a new particle version exception
		 *
		 * @param message Message that will be logged
		 */
		public ParticleVersionException(String message) {
			super(message);
		}
	}

	/**
	 * Represents a particle effect packet with all attributes which is used for sending packets to the players
	 * <p>
	 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
	 *
	 * @author DarkBlade12
	 * @since 1.5
	 */
	public static final class ParticlePacket {
		private static int version;
		private static boolean initialized;
		private final Particles effect;
		private final float offsetY;
		private final float offsetZ;
		private final float speed;
		private final int amount;
		private final ParticleData data;
		private float offsetX;
		private Object packet;

		/**
		 * Construct a new particle packet
		 *
		 * @param effect       Particle effect
		 * @param offsetX      Maximum distance particles can fly away from the center on the x-axis
		 * @param offsetY      Maximum distance particles can fly away from the center on the y-axis
		 * @param offsetZ      Maximum distance particles can fly away from the center on the z-axis
		 * @param speed        Display speed of the particles
		 * @param amount       Amount of particles
		 * @param longDistance Indicates whether the maximum distance is increased from 256 to 65536
		 * @param data         Data of the effect
		 * @throws IllegalArgumentException If the speed or amount is lower than 0
		 * @see #initialize()
		 */
		public ParticlePacket(Particles effect, float offsetX, float offsetY, float offsetZ, float speed, int amount, boolean longDistance, ParticleData data) throws IllegalArgumentException {
			initialize();
			if (speed < 0) {
				throw new IllegalArgumentException("The speed is lower than 0");
			}
			if (amount < 0) {
				throw new IllegalArgumentException("The amount is lower than 0");
			}
			this.effect = effect;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.offsetZ = offsetZ;
			this.speed = speed;
			this.amount = amount;
			this.data = data;
		}

		/**
		 * Construct a new particle packet of a single particle flying into a determined direction
		 *
		 * @param effect       Particle effect
		 * @param direction    Direction of the particle
		 * @param speed        Display speed of the particle
		 * @param longDistance Indicates whether the maximum distance is increased from 256 to 65536
		 * @param data         Data of the effect
		 * @throws IllegalArgumentException If the speed is lower than 0
		 */
		public ParticlePacket(Particles effect, Vector direction, float speed, boolean longDistance, ParticleData data) throws IllegalArgumentException {
			this(effect, (float) direction.getX(), (float) direction.getY(), (float) direction.getZ(), speed, 0, longDistance, data);
		}

		/**
		 * Construct a new particle packet of a single colored particle
		 *
		 * @param effect       Particle effect
		 * @param color        Color of the particle
		 * @param longDistance Indicates whether the maximum distance is increased from 256 to 65536
		 */
		public ParticlePacket(Particles effect, ParticleColor color, boolean longDistance) {
			this(effect, color.getValueX(), color.getValueY(), color.getValueZ(), 1, 0, longDistance, null);
			if (effect == Particles.REDSTONE && color instanceof OrdinaryColor && ((OrdinaryColor) color).getRed() == 0) {
				offsetX = Float.MIN_NORMAL;
			}
		}

		public static void initialize() {
			if (initialized) {
				return;
			}
			version = Version.V.v1_16.getMinor();
			initialized = true;
		}

		/**
		 * Returns the version of your server (1.x)
		 *
		 * @return The version number
		 */
		public static int getVersion() {
			if (!initialized) {
				initialize();
			}
			return version;
		}

		/**
		 * Initializes {@link #packet} with all set values
		 *
		 * @param center Center location of the effect
		 */
		private void initializePacket(Location center) {
			if (packet != null) {
				return;
			}
			if (effect == Particles.REDSTONE) {
				int r = (int) (offsetX * 255);
				int g = (int) (offsetY * 255);
				int b = (int) (offsetZ * 255);
				if (r == 0 && g == 0 && b == 0) { // Normal redstone particle, no color data supplied
					r = 255;
				}
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center, 0, new org.bukkit.Particle.DustOptions(Color.fromRGB(r, g, b), 1));
			} else if (effect == Particles.SPELL_MOB || effect == Particles.SPELL_MOB_AMBIENT) {
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center, amount, offsetX, offsetY, offsetZ, 1);
			} else if (effect == Particles.NOTE) {
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center, amount, offsetX, 0, 0, 1);
			} else if (effect == Particles.ITEM_CRACK) {
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center, amount, offsetX, offsetY, offsetZ, new ItemStack(data.getMaterial()));
			} else if (effect == Particles.BLOCK_CRACK) {
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center, amount, offsetX, offsetY, offsetZ, data.getMaterial()
								.createBlockData());
			} else {
				center.getWorld()
						.spawnParticle(org.bukkit.Particle.valueOf(effect.toString()), center.getX(), center.getY(), center.getZ(), amount, offsetX, offsetY, offsetZ, speed);
			}
		}

		/**
		 * Sends the packet to all players
		 *
		 * @param center Center location of the effect
		 * @throws IllegalArgumentException If the player list is empty
		 */
		public void sendTo(Location center) throws IllegalArgumentException {
			initializePacket(center);
		}

		/**
		 * Represents a runtime exception that is thrown if packet instantiation fails
		 * <p>
		 * This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
		 *
		 * @author DarkBlade12
		 * @since 1.4
		 */
		private static final class PacketInstantiationException extends RuntimeException {
			private static final long serialVersionUID = 3203085387160737484L;

			/**
			 * Construct a new packet instantiation exception
			 *
			 * @param message Message that will be logged
			 * @param cause   Cause of the exception
			 */
			public PacketInstantiationException(String message, Throwable cause) {
				super(message, cause);
			}
		}
	}
}
