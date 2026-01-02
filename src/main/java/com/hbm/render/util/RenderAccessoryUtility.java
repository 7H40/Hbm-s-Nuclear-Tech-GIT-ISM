package com.hbm.render.util;

import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.render.model.ModelArmorTailPeep;
import com.hbm.render.model.ModelArmorWings;
import com.hbm.render.model.ModelArmorWingsPheo;
import com.hbm.util.ShadyUtil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderAccessoryUtility {

	private static ResourceLocation hbm = new ResourceLocation(RefStrings.MODID + ":textures/models/capes/CapeHbm3.png");
	private static ResourceLocation hbm2 = new ResourceLocation(RefStrings.MODID + ":textures/models/capes/CapeHbm2.png");
	private static ResourceLocation drillgon = new ResourceLocation(RefStrings.MODID + ":textures/models/capes/CapeDrillgon.png");

	//private static ResourceLocation 7H40 = new ResourceLocation(RefStrings.MODID + ":textures/models/capes/CapeDafnik.png");


	public static ResourceLocation getCloakFromPlayer(EntityPlayer player) {

		String uuid = player.getUniqueID().toString();
		String name = player.getDisplayName();

		if(uuid.equals(ShadyUtil.HbMinecraft)) {
			return (MainRegistry.polaroidID == 11 ? hbm2 : hbm);
		}

		if(uuid.equals(ShadyUtil.Drillgon)) {
			return drillgon;
		}

	//	if(uuid.equals(ShadyUtil.defghi)) {
		//	return defghi;
		//}

	//	if(uuid.equals(ShadyUtil.7H40)) {
	//		return 7H40;
		//}

		return null;
	}

	private static ModelBiped[] wingModels = new ModelBiped[10];
	public static void renderWings(RenderPlayerEvent.SetArmorModel event, int mode) {

		if(wingModels[mode] == null)
			wingModels[mode] = new ModelArmorWings(mode);

		RenderPlayer renderer = event.renderer;
		ModelBiped model = renderer.modelArmor;
		EntityPlayer player = event.entityPlayer;

		wingModels[mode].isSneak = model.isSneak;

		float interp = event.partialRenderTick;
		float yawHead = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * interp;
		float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * interp;
		float yaw = yawHead - yawOffset;
		float yawWrapped = MathHelper.wrapAngleTo180_float(yawHead - yawOffset);
		float pitch = player.rotationPitch;

		wingModels[mode].render(event.entityPlayer, 0.0F, 0.0F, yawWrapped, yaw, pitch, 0.0625F);
	}

	private static ModelBiped axePackModel;
	public static void renderAxePack(RenderPlayerEvent.SetArmorModel event) {

		if(axePackModel == null)
			axePackModel = new ModelArmorWingsPheo();

		RenderPlayer renderer = event.renderer;
		ModelBiped model = renderer.modelArmor;
		EntityPlayer player = event.entityPlayer;

		axePackModel.isSneak = model.isSneak;

		float interp = event.partialRenderTick;
		float yawHead = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * interp;
		float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * interp;
		float yaw = yawHead - yawOffset;
		float yawWrapped = MathHelper.wrapAngleTo180_float(yawHead - yawOffset);
		float pitch = player.rotationPitch;

		axePackModel.render(event.entityPlayer, 0.0F, 0.0F, yawWrapped, yaw, pitch, 0.0625F);
	}

	private static ModelBiped tailModel;
	public static void renderFaggot(RenderPlayerEvent.SetArmorModel event) {

		if(tailModel == null)
			tailModel = new ModelArmorTailPeep();

		RenderPlayer renderer = event.renderer;
		ModelBiped model = renderer.modelArmor;
		EntityPlayer player = event.entityPlayer;

		tailModel.isSneak = model.isSneak;

		float interp = event.partialRenderTick;
		float yawHead = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * interp;
		float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * interp;
		float yaw = yawHead - yawOffset;
		float yawWrapped = MathHelper.wrapAngleTo180_float(yawHead - yawOffset);
		float pitch = player.rotationPitch;

		tailModel.render(event.entityPlayer, 0.0F, 0.0F, yawWrapped, yaw, pitch, 0.0625F);
	}
}
