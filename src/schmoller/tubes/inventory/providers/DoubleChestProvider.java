package schmoller.tubes.inventory.providers;

import schmoller.tubes.api.interfaces.IInventoryProvider;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntityChest;

public class DoubleChestProvider implements IInventoryProvider
{
	@Override
	public IInventory provide( Object object )
	{
		if(object instanceof TileEntityChest)
		{
			TileEntityChest chest = (TileEntityChest)object;
			
			TileEntityChest adjacent = null;
			IInventory inventory = chest;
			
			if (chest.adjacentChestXNeg != null)
			{
				adjacent = chest.adjacentChestXNeg;
				inventory = new InventoryLargeChest("", adjacent, inventory);
			}
			
			if (chest.adjacentChestXPos != null)
			{
				adjacent = chest.adjacentChestXPos;
				inventory = new InventoryLargeChest("", inventory, adjacent);
			}
			
			if (chest.adjacentChestZNeg != null)
			{
				adjacent = chest.adjacentChestZNeg;
				inventory = new InventoryLargeChest("", adjacent, inventory);
			}
			
			if (chest.adjacentChestZPosition != null)
			{
				adjacent = chest.adjacentChestZPosition;
				inventory = new InventoryLargeChest("", inventory, adjacent);
			}
			
			return inventory;
		}
		
		return null;
	}

}
