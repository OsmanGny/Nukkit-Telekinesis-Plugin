import Command.TelekinesiCommand;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Telekinesi extends PluginBase
{
	private static Telekinesi instance = null;
	@Override
	public void onEnable()
	{
		instance = this;
		getLogger().info(TextFormat.GREEN + "Telekinesi Aktif");
		
		getServer().getCommandMap().register("Kullan�m /telekinesi", new TelekinesiCommand("telekinesi", 
				"Wow evet telekinetik g��lere sahip olacaks�n�z ve zihninizle yarat�klar� hareket ettirebileceksiniz"));
		getServer().getPluginManager().registerEvents(new TelekinesiCommand("", ""), this);
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(TextFormat.RED + "Telekinesi Deaktif");
	}
	
	public Telekinesi getInstance()
	{
		return instance;
	}
}
