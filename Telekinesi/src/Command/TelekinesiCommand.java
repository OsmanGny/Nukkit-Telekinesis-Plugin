package Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Event.Events;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemStick;
import cn.nukkit.math.MathHelper;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;

public class TelekinesiCommand extends Command implements Listener
{
	// LOL :-)
	public static HashMap<Player, Player> datas = new HashMap<Player, Player>();
	public static final Item item = new ItemStick(16);
	
	
	
	public TelekinesiCommand(String name, String description) {
		super(name, description);
		
		setPermission("telekinesis.command");
		
		item.setCustomName("§eT§de§4l§5e§8k§9i§0n§1e§cs§bi §6S§8o§5p§9a§2s§3ý");
		Server.getInstance().getPluginManager().registerEvents(new Events(), Server.getInstance().getPluginManager().getPlugin("Telekinesi"));
		Server.getInstance().getScheduler().scheduleRepeatingTask(new Task() {
			
			@Override
			public void onRun(int currentTick) 
			{
				for (Player p : datas.keySet()) {
					datas.get(p).setPositionAndRotation(p.getDirectionVector().multiply(10).add(p.getPosition()) , p.yaw, p.pitch);
				}
			}
		}, 1);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase(super.getName()) && sender.isPlayer() && sender.hasPermission("telekinesis.command"))
		{
			TForm.OpenUI((Player)sender);
		}
		else if(!sender.hasPermission(getPermission()))
			sender.sendMessage("§4You don't have permission for this");
		return false;
	}

	@EventHandler
	public void bum(PlayerFormRespondedEvent args)
	{
		FormResponseSimple response = (FormResponseSimple)args.getResponse();
		if(response == null)
			return;
		
		
		if (response.getClickedButton().getText().equalsIgnoreCase("Telekinesi sopasý al"))
			args.getPlayer().getInventory().setItemInHand(item);
	}
	
}
