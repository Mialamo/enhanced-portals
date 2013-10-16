package uk.co.shadeddimensions.ep3.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.shadeddimensions.ep3.container.ContainerModuleManipulator;
import uk.co.shadeddimensions.ep3.container.ContainerNetworkInterface;
import uk.co.shadeddimensions.ep3.container.ContainerPortalFrameController;
import uk.co.shadeddimensions.ep3.container.ContainerPortalFrameRedstone;
import uk.co.shadeddimensions.ep3.container.ContainerPortalFrameTexture;
import uk.co.shadeddimensions.ep3.container.ContainerPortalTexture;
import uk.co.shadeddimensions.ep3.gui.GuiModuleManipulator;
import uk.co.shadeddimensions.ep3.gui.GuiPortalFrameController;
import uk.co.shadeddimensions.ep3.gui.GuiPortalFrameNetworkInterface;
import uk.co.shadeddimensions.ep3.gui.GuiPortalFrameRedstone;
import uk.co.shadeddimensions.ep3.gui.GuiPortalFrameTexture;
import uk.co.shadeddimensions.ep3.gui.GuiPortalTexture;
import uk.co.shadeddimensions.ep3.lib.GuiIds;
import uk.co.shadeddimensions.ep3.tileentity.frame.TileModuleManipulator;
import uk.co.shadeddimensions.ep3.tileentity.frame.TileNetworkInterface;
import uk.co.shadeddimensions.ep3.tileentity.frame.TilePortalController;
import uk.co.shadeddimensions.ep3.tileentity.frame.TileRedstoneInterface;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (ID == GuiIds.PORTAL_CONTROLLER && tile instanceof TilePortalController)
        {
            return new GuiPortalFrameController(player, (TilePortalController) tile);
        }
        else if (ID == GuiIds.PORTAL_REDSTONE && tile instanceof TileRedstoneInterface)
        {
            return new GuiPortalFrameRedstone(player, (TileRedstoneInterface) tile);
        }
        else if (ID == GuiIds.PORTAL_FRAME_TEXTURE && tile instanceof TilePortalController)
        {
            return new GuiPortalFrameTexture(player, (TilePortalController) tile);
        }
        else if (ID == GuiIds.PORTAL_TEXTURE && tile instanceof TilePortalController)
        {
            return new GuiPortalTexture(player, (TilePortalController) tile);
        }
        else if (ID == GuiIds.NETWORK_INTERFACE && tile instanceof TileNetworkInterface)
        {
            return new GuiPortalFrameNetworkInterface((TileNetworkInterface) tile);
        }
        else if (ID == GuiIds.MODULE_MANIPULATOR && tile instanceof TileModuleManipulator)
        {
            return new GuiModuleManipulator(player, (TileModuleManipulator) tile);
        }

        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (ID == GuiIds.PORTAL_CONTROLLER && tile instanceof TilePortalController)
        {
            CommonProxy.sendUpdatePacketToPlayer((TilePortalController) tile, player);
            return new ContainerPortalFrameController((TilePortalController) tile);
        }
        else if (ID == GuiIds.PORTAL_REDSTONE && tile instanceof TileRedstoneInterface)
        {
            CommonProxy.sendUpdatePacketToPlayer((TileRedstoneInterface) tile, player);
            return new ContainerPortalFrameRedstone((TileRedstoneInterface) tile);
        }
        else if (ID == GuiIds.PORTAL_FRAME_TEXTURE && tile instanceof TilePortalController)
        {
            return new ContainerPortalFrameTexture(player, (TilePortalController) tile);
        }
        else if (ID == GuiIds.PORTAL_TEXTURE && tile instanceof TilePortalController)
        {
            return new ContainerPortalTexture(player, (TilePortalController) tile);
        }
        else if (ID == GuiIds.NETWORK_INTERFACE && tile instanceof TileNetworkInterface)
        {
            CommonProxy.sendUpdatePacketToPlayer((TileNetworkInterface) tile, player);
            return new ContainerNetworkInterface((TileNetworkInterface) tile);
        }
        else if (ID == GuiIds.MODULE_MANIPULATOR && tile instanceof TileModuleManipulator)
        {
            return new ContainerModuleManipulator(player, (TileModuleManipulator) tile);
        }

        return null;
    }
}
