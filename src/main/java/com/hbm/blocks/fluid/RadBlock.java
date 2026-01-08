package com.hbm.blocks.fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.RefStrings;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;
import com.hbm.util.i18n.I18nUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fluids.Fluid;

public class RadBlock extends VolcanicBlock implements ILookOverlay {

	@SideOnly(Side.CLIENT) public static IIcon stillIconRad;
	@SideOnly(Side.CLIENT) public static IIcon flowingIconRad;

	public RadBlock(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		stillIconRad = register.registerIcon(RefStrings.MODID + ":rad_lava_still");
		flowingIconRad = register.registerIcon(RefStrings.MODID + ":rad_lava_flowing");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIconRad : flowingIconRad;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLivingBase) ContaminationUtil.contaminate((EntityLivingBase) entity, HazardType.RADIATION, ContaminationType.CREATIVE, 5F);
	}

	@Override
	public void onSolidify(World world, int x, int y, int z, int lavaCount, int basaltCount, Random rand) {
		int r = rand.nextInt(400);
		
		Block above = world.getBlock(x, y + 10, z);
		boolean canMakeGem = lavaCount + basaltCount == 6 && lavaCount < 3 && (above == ModBlocks.sellafield_slaked || above == ModBlocks.rad_lava_block);
		int meta = 5 + rand.nextInt(3);
		
		if(r < 2) world.setBlock(x, y, z, ModBlocks.ore_sellafield_diamond, meta, 3);
		else if(r == 2) world.setBlock(x, y, z, ModBlocks.ore_sellafield_emerald, meta, 3);
		else if(r < 20 && canMakeGem) world.setBlock(x, y, z, ModBlocks.ore_sellafield_radgem, meta, 3);
		else world.setBlock(x, y, z, ModBlocks.sellafield_slaked, meta, 3);
	}

	@Override
	public Block getBasaltForCheck() {
		return ModBlocks.sellafield_slaked;
	}

	@Override
	public Block getReaction(World world, int x, int y, int z) {
		
		Block b = world.getBlock(x, y, z);
		if(b.getMaterial() == Material.water) return Blocks.stone;
		if(b == Blocks.log || b == Blocks.log2) return ModBlocks.waste_log;
		if(b == Blocks.planks) return ModBlocks.waste_planks;
		if(b == Blocks.leaves || b == Blocks.leaves2) return Blocks.fire;
		if(b == Blocks.diamond_ore) return ModBlocks.ore_sellafield_radgem;
		if(b == ModBlocks.ore_uranium || b == ModBlocks.ore_gneiss_uranium) return world.rand.nextInt(5) == 0 ? ModBlocks.ore_sellafield_schrabidium : ModBlocks.ore_sellafield_uranium_scorched;
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void printHook(RenderGameOverlayEvent.Pre event, World world, int x, int y, int z) {
		List<String> text = new ArrayList();
		text.add("Вы не получите лут с этого блока.");
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getUnlocalizedName() + ".name"), 0xffff00, 0x404000, text);
	}
}
