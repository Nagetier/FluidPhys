package de.nagetier.FluidPhys;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Door;

public class DoorPhys implements Listener
{
	private Logger log;
	
	public DoorPhys(Logger log)
	{
		this.log = log;
	}
	
	@EventHandler
	public void onPlayerDoorOpen(PlayerInteractEvent event)
	{
		Action action = event.getAction();
		Block clicked = event.getClickedBlock();
				
		//Left or Right click?
		if ((action == Action.RIGHT_CLICK_BLOCK) || (action == Action.LEFT_CLICK_BLOCK))
		{
			//Door Block?
			if(isDoor(clicked))
			{
				//Send Material to waterdoor function, no need to change over and over....
				
				Material btype = clicked.getType();
				Door door = (Door) btype.getNewData((clicked.getData()));
				
				log.info("Door!?");
				waterThroughDoor(door, clicked);
			}
			else
			{
				log.info("no door!");
			}
		}
		else{	}
	}
	
	
	
	
	@SuppressWarnings("unused")
	private void waterThroughDoor(Door door, Block doorblock)
	{
		log.info("Door!");
		
		//Blocks touching the door (Only selecting the upper ones, need to get the lower ones!
		BlockFace facing = door.getFacing();		
		Block block_front = doorblock.getRelative(facing);
		Block block_back = doorblock.getRelative(facing.getOppositeFace());
		
		//Get Location of Blocks
		World w = doorblock.getWorld();
		Location loc_front = block_front.getLocation();
		Location loc_back = block_back.getLocation();
		
		/*		Check if water on one side of the door
		 * 		Works, but only checks top block of door, need to check lower block either
		 * 		Defining lower blocks 
		 */	
		
		Location loc_back_lower = loc_back.add(0, -1, 0);
		Location loc_front_lower = loc_front.add(0, -1, 0);
		
		
		/*		Blocks at defines positions,
		 * 		good for checking water conditions. 
		 */
		Block b_back = loc_back.getBlock();
		Block b_back_lower = loc_back_lower.getBlock();
		Block b_front = loc_front.getBlock();
		Block b_front_lower = loc_front_lower.getBlock();

		if(b_back.isLiquid() || b_back_lower.isLiquid() || b_front.isLiquid() || b_front_lower.isLiquid())
		{
			log.info("Liquid!");
		}
		
	}
	
	private boolean isDoor(Block clicked_block)
	{
		Material btype = clicked_block.getType();
		
		if((btype == Material.WOODEN_DOOR) || (btype == Material.IRON_DOOR))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
