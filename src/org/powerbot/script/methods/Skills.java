package org.powerbot.script.methods;

import org.powerbot.bot.client.Client;
import org.powerbot.bot.client.PlayerMetaInfo;
import org.powerbot.bot.client.Skill;

public class Skills extends MethodProvider {
	public static final int[] XP_TABLE = {0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107,
			2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
			16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983,
			75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
			302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
			1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594,
			3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629,
			11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069,
			31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828,
			85539082, 94442737, 104273167};
	public static final int ATTACK = 0;
	public static final int DEFENSE = 1;
	public static final int STRENGTH = 2;
	public static final int CONSTITUTION = 3;
	public static final int RANGE = 4;
	public static final int PRAYER = 5;
	public static final int MAGIC = 6;
	public static final int COOKING = 7;
	public static final int WOODCUTTING = 8;
	public static final int FLETCHING = 9;
	public static final int FISHING = 10;
	public static final int FIREMAKING = 11;
	public static final int CRAFTING = 12;
	public static final int SMITHING = 13;
	public static final int MINING = 14;
	public static final int HERBLORE = 15;
	public static final int AGILITY = 16;
	public static final int THIEVING = 17;
	public static final int SLAYER = 18;
	public static final int FARMING = 19;
	public static final int RUNECRAFTING = 20;
	public static final int HUNTER = 21;
	public static final int CONSTRUCTION = 22;
	public static final int SUMMONING = 23;
	public static final int DUNGEONEERING = 24;
	public static final int DIVINATION = 25;

	public Skills(final MethodContext factory) {
		super(factory);
	}

	/**
	 * Returns the current level of the skill at the provided index.
	 *
	 * @param index the index of the skill
	 * @return the current level at the specified index
	 */
	public int getLevel(final int index) {
		final int[] levels = getLevels();
		if (index >= 0 && index < levels.length) {
			return levels[index];
		}
		return -1;
	}

	/**
	 * Returns the real level of the skill at the provided index.
	 *
	 * @param index the index of the skill
	 * @return the real level at the specified index
	 */
	public int getRealLevel(final int index) {
		final int[] levels = getRealLevels();
		if (index >= 0 && index < levels.length) {
			return levels[index];
		}
		return -1;
	}

	/**
	 * Returns the experience of the skill at the provided index.
	 *
	 * @param index the index of the skill
	 * @return the experience at the specified index
	 */
	public int getExperience(final int index) {
		final int[] exps = getExperiences();
		if (index >= 0 && index < exps.length) {
			return exps[index];
		}
		return -1;
	}

	public int[] getLevels() {
		final Client client = ctx.getClient();
		if (client == null) {
			return new int[0];
		}
		final PlayerMetaInfo info = client.getPlayerMetaInfo();
		final Skill[] skills;
		if (info != null && (skills = info.getSkills()) != null) {
			final int[] levels = new int[skills.length];
			for (int i = 0; i < skills.length; i++) {
				if (skills[i] == null) {
					return new int[0];
				}
				levels[i] = skills[i].getLevel();
			}
			return levels;
		}
		return new int[0];
	}

	public int[] getRealLevels() {
		final Client client = ctx.getClient();
		if (client == null) {
			return new int[0];
		}
		final PlayerMetaInfo info = client.getPlayerMetaInfo();
		final Skill[] skills;
		if (info != null && (skills = info.getSkills()) != null) {
			final int[] levels = new int[skills.length];
			for (int i = 0; i < skills.length; i++) {
				levels[i] = skills[i].getRealLevel();
			}
			return levels;
		}
		return new int[0];
	}

	public int[] getExperiences() {
		final Client client = ctx.getClient();
		if (client == null) {
			return new int[0];
		}
		final PlayerMetaInfo info = client.getPlayerMetaInfo();
		final Skill[] skills;
		if (info != null && (skills = info.getSkills()) != null) {
			final int[] exps = new int[skills.length];
			for (int i = 0; i < skills.length; i++) {
				exps[i] = skills[i].getExperience();
			}
			return exps;
		}
		return new int[0];
	}

	/**
	 * Determines the level at the specified amount of exp.
	 *
	 * @param exp the exp to convert to level
	 * @return the level with the given amount of exp
	 */
	public int getLevelAt(final int exp) {
		for (int i = XP_TABLE.length - 1; i > 0; i--) {
			if (exp > XP_TABLE[i]) {
				return i;
			}
		}
		return 1;
	}

	/**
	 * Determines the experience required for the specified level.
	 *
	 * @param level the level to get the exp at
	 * @return the exp at the specified level
	 */
	public int getExperienceAt(final int level) {
		if (level < 0 || level > 120) {
			return -1;
		}
		return XP_TABLE[level];
	}
}