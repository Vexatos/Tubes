package schmoller.tubes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import schmoller.tubes.api.Items;
import schmoller.tubes.api.TubeRegistry;
import schmoller.tubes.api.helpers.CommonHelper;
import schmoller.tubes.api.helpers.RenderHelper;
import schmoller.tubes.gui.CompressorTubeGui;
import schmoller.tubes.gui.FilterTubeGui;
import schmoller.tubes.gui.InjectionTubeGui;
import schmoller.tubes.gui.RequestingTubeGui;
import schmoller.tubes.gui.RoutingTubeGui;
import schmoller.tubes.network.ModPacket;
import schmoller.tubes.render.ColoringTubeRender;
import schmoller.tubes.render.CompressorTubeRender;
import schmoller.tubes.render.EjectionTubeRender;
import schmoller.tubes.render.ExtractionTubeRender;
import schmoller.tubes.render.FilterTubeRender;
import schmoller.tubes.render.InjectionTubeRender;
import schmoller.tubes.render.NormalTubeRender;
import schmoller.tubes.render.RenderTubeCap;
import schmoller.tubes.render.RenderTubeItem;
import schmoller.tubes.render.RequestingTubeRender;
import schmoller.tubes.render.RestrictionTubeRender;
import schmoller.tubes.render.RoutingTubeRender;
import schmoller.tubes.render.ValveTubeRender;
import schmoller.tubes.types.CompressorTube;
import schmoller.tubes.types.FilterTube;
import schmoller.tubes.types.InjectionTube;
import schmoller.tubes.types.RequestingTube;
import schmoller.tubes.types.RoutingTube;
import cpw.mods.fml.common.network.Player;

public class ClientProxy extends CommonProxy
{
	@Override
	public void initialize()
	{
		super.initialize();
		
		RenderHelper.initialize();
		
		registerRenderers();
	}
	
	private void registerRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(Items.Tube.getItemID(), new RenderTubeItem());
		MinecraftForgeClient.registerItemRenderer(Items.TubeCap.getItemID(), new RenderTubeCap());
		
		TubeRegistry.registerRenderer("basic",new NormalTubeRender());
		TubeRegistry.registerRenderer("restriction",new RestrictionTubeRender());
		TubeRegistry.registerRenderer("injection",new InjectionTubeRender());
		TubeRegistry.registerRenderer("ejection", new EjectionTubeRender());
		TubeRegistry.registerRenderer("filter", new FilterTubeRender());
		TubeRegistry.registerRenderer("compressor",new CompressorTubeRender());
		TubeRegistry.registerRenderer("extraction", new ExtractionTubeRender());
		TubeRegistry.registerRenderer("requesting", new RequestingTubeRender());
		TubeRegistry.registerRenderer("routing", new RoutingTubeRender());
		TubeRegistry.registerRenderer("valve", new ValveTubeRender());
		TubeRegistry.registerRenderer("coloring", new ColoringTubeRender());
	}
	
	@Override
	public boolean onPacketArrive( ModPacket packet, Player sender )
	{
		return super.onPacketArrive(packet, sender);
	}
	
	@Override
	public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		switch(ID)
		{
		case ModTubes.GUI_INJECTION_TUBE:
			return new InjectionTubeGui(CommonHelper.getMultiPart(world, x, y, z, InjectionTube.class), player);
		case ModTubes.GUI_FILTER_TUBE:
			return new FilterTubeGui(CommonHelper.getMultiPart(world, x, y, z, FilterTube.class), player);
		case ModTubes.GUI_COMPRESSOR_TUBE:
			return new CompressorTubeGui(CommonHelper.getMultiPart(world, x, y, z, CompressorTube.class), player);
		case ModTubes.GUI_REQUESTING_TUBE:
			return new RequestingTubeGui(CommonHelper.getMultiPart(world, x, y, z, RequestingTube.class), player);
		case ModTubes.GUI_ROUTING_TUBE:
			return new RoutingTubeGui(CommonHelper.getMultiPart(world, x, y, z, RoutingTube.class), player);
		}
		
		return null;
	}
}
