package Command;

import java.util.Arrays;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

public final class TForm implements Listener
{
	public static FormWindowCustom custom = new FormWindowCustom("Ayarlar", Arrays.asList(new Element[] { new ElementInput("Uzaklýk"),new ElementInput("Fýrlatma gücü") }));
	public static FormWindowSimple form = new FormWindowSimple("§eTelekinesi","§4Telekinesi Menü" , Arrays.asList(new ElementButton("Telekinesi sopasý al"),new ElementButton("Ayarlarý Deðiþtir")));
	private TForm()
	{
		
	}
	
	public static void OpenUI(Player player)
	{
		player.showFormWindow(form);
	}
}
