package environments;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"classpath:configurations/${env}.properties",
	"classpath:configurations/dev.properties"
    })
public interface Environment extends Config  {
	@Key("target")
	 String gettarget();
	
	@Key("url.base")
	 String geturlBase();
	
	@Key("grid.port")
	 String getgridport();
}
