package Command;

import java.util.Arrays;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;

public final class TForm implements Listener
{
	private static FormWindowSimple form = new FormWindowSimple("§eTelekinesi","§4Telekinesi Menü" , Arrays.asList(new ElementButton("Telekinesi sopasý al")));
	private TForm()
	{
		
	}
	
	public static void OpenUI(Player player)
	{
		player.showFormWindow(form);
	}
}
