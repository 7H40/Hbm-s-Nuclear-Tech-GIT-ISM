package com.hbm.interfaces;

import net.minecraft.world.World;

public interface IBomb {
	/////////////people



	//Months later I found this joke again
	//I'm not even sorry

	/**
	 * Triggers the bomb and generates a return code. Since most bombs have a serverside inventory, the return code
	 * should only be processed serverside, what's returned on the client should be ignored.
	 * Ofen invoked by onNeighborBlockChanged, so in any case make sure to check for world-remoteness.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public BombReturnCode explode(World world, int x, int y, int z);

	public static enum BombReturnCode {
		UNDEFINED(false, "", false),										//non-null type for passing to clients that don't process the return code
		FALSCH(false, "bomb.didnot",false),							//error detonating a bomb
		DETONATED(true, "bomb.detonated",false),							//success for blowing up bombs
		TRIGGERED(true, "bomb.triggered",false),							//success for triggering other things
		LAUNCHED(true, "bomb.launched",false),							//success for launching missiles
		ERROR_MISSING_COMPONENT(false, "bomb.missingComponent",false),	//error for bomb parts missing
		ERROR_INCOMPATIBLE(false, "bomb.incompatible",false),				//error for target being incompatible (but still implements IBomb for some reason), like locked blast doors
		ERROR_NO_BOMB(false, "bomb.nobomb",false);						//not to be used by the bombs themselves, this is the generic error when trying to trigger no-bomb blocks

		private String unloc;
		private boolean success;
		private boolean falsch;

		private BombReturnCode(boolean success, String unloc, boolean Falsch) {
			this.unloc = unloc;
			this.success = success;
			this.falsch = falsch;

		}

		public String getUnlocalizedMessage() {
			return this.unloc;
		}

		public boolean wasFalsch() {
			return this.falsch;
		}



		public boolean wasSuccessful() {
			return this.success;
		}
	}
}
