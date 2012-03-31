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
			if((clicked.getType() == Material.IRON_DOOR) || (clicked.getType() == Material.WOODEN_DOOR) || (clicked.getType() == Material.TRAP_DOOR))
			{
				log.info("Door!?");
				waterThroughDoor(clicked);
			}
			else{	}
		}
		else{	}
	}
	
	private void waterThroughDoor(Block doorblock)
	{
		if(doorblock.getState() instanceof Door)
		{
			log.info("Door!");
			Door door = (Door)doorblock.getState();
			door.setOpen(false);			
			BlockFace facing = door.getFacing();
			//Wheres the block?
			Location loc = doorblock.getLocation();
			World w = loc.getWorld();
			
			//Blocks touching the door
			Block block_front = doorblock.getRelative(facing);
			Block block_back = doorblock.getRelative(facing.getOppositeFace());
			
			//Check if water on one side of the door
			
		}
		else
		{
			log.info("null");
		}
						
	}
}
