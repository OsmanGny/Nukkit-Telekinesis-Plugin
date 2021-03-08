package Command;

import java.util.ArrayList;
import java.util.Arrays;
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
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemStick;
import cn.nukkit.math.MathHelper;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.CraftingDataPacket;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.SetEntityDataPacket;
import cn.nukkit.scheduler.Task;

public class TelekinesiCommand extends Command implements Listener
{
	// LOL :-)
	public static HashMap<Player, Entity> datas = new HashMap<Player, Entity>();
	public static final Item item = new ItemStick(16);
	public static float distance = 10;
	public static float ejectforce = 10;
	
	
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
					datas.get(p).setPositionAndRotation(p.getDirectionVector().multiply(distance).add(p.getPosition()) , p.yaw, p.pitch);
					if(datas.get(p) instanceof Player)
						Server.getInstance().getPluginManager().callEvent(new DataPacketSendEvent((Player) datas.get(p), new SetEntityDataPacket()));
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
		if(args.getWindow() instanceof FormWindowSimple)
		{
			FormResponseSimple response = (FormResponseSimple)args.getResponse();
			if(response == null)
				return;
			
			
			if (response.getClickedButton().getText().equalsIgnoreCase("Telekinesi sopasý al"))
				args.getPlayer().getInventory().setItemInHand(item);
			else if(response.getClickedButton().getText().equalsIgnoreCase("Ayarlarý Deðiþtir"))
			{
				args.getPlayer().showFormWindow(TForm.custom);
			}
		}
		else
		{
			try {
				distance = Float.parseFloat(TForm.custom.getResponse().getInputResponse(0));
				ejectforce = Float.parseFloat(TForm.custom.getResponse().getInputResponse(1));
			} catch (NumberFormatException e) {
				args.getPlayer().sendMessage("Sayý girin yazý deðil!");
			}
		}
	}
	
}
