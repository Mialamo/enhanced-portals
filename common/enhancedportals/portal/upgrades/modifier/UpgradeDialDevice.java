package enhancedportals.portal.upgrades.modifier;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import alz.core.lib.WorldLocation;
import enhancedportals.EnhancedPortals;
import enhancedportals.lib.Localization;
import enhancedportals.portal.upgrades.Upgrade;
import enhancedportals.tileentity.TileEntityPortalModifier;

public class UpgradeDialDevice extends Upgrade
{
    public UpgradeDialDevice()
    {
        super();
    }

    @Override
    public ItemStack getDisplayItemStack()
    {
        return new ItemStack(EnhancedPortals.proxy.blockPortalModifier, 1, 0);
    }

    @Override
    public ItemStack getItemStack()
    {
        return new ItemStack(EnhancedPortals.proxy.portalModifierUpgrade, 1, getUpgradeID());
    }

    @Override
    public List<String> getText(boolean includeTitle)
    {
        List<String> list = new ArrayList<String>();

        if (includeTitle)
        {
            list.add(EnumChatFormatting.AQUA + Localization.localizeString("item." + Localization.PortalModifierUpgrade_Name + ".dialDevice.name"));
        }

        list.add(EnumChatFormatting.GRAY + "Allows use with a dial device.");

        if (includeTitle)
        {
            list.add(EnumChatFormatting.DARK_GRAY + "gui.upgrade.remove");
        }

        return list;
    }

    @Override
    public boolean onActivated(TileEntity tileEntity)
    {
        TileEntityPortalModifier modifier = (TileEntityPortalModifier) tileEntity;
        
        if (!tileEntity.worldObj.isRemote)
        {
            EnhancedPortals.proxy.ModifierNetwork.removeFromNetwork(modifier.network, new WorldLocation(modifier.xCoord, modifier.yCoord, modifier.zCoord, modifier.worldObj));
        }
        
        modifier.network = "";
        return true;
    }

    @Override
    public boolean onDeactivated(TileEntity tileEntity)
    {
        TileEntityPortalModifier modifier = (TileEntityPortalModifier) tileEntity;
        
        // TODO : Remove from DHD network
        
        modifier.network = "";
        return true;
    }
}