package Event;

import Command.TelekinesiCommand;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;

public class Events implements Listener
{
	
	@EventHandler
	public void Hit(EntityDamageByEntityEvent args)
	{
		if(args.getDamager() instanceof Player && args.getEntity() instanceof Player && ((Player)args.getDamager()).getInventory().getItemInHand().equals(TelekinesiCommand.item))
		{
			args.setCancelled();
			TelekinesiCommand.datas.put((Player)args.getDamager(), (Player)args.getEntity());
		
		}
	}
	
	@EventHandler
	public void Býrak(PlayerInteractEvent e)
	{
		if(TelekinesiCommand.datas.containsKey(e.getPlayer()) && e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			TelekinesiCommand.datas.remove(e.getPlayer());
	}
}

