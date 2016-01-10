package net.shadowfacts.shadowmc.event;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
@Getter
@Setter
public class ScreenShotEvent extends Event {

	protected File screenshotFile;

	@Cancelable
	public static class Pre extends ScreenShotEvent {

		public Pre(File screenshotFile) {
			this.screenshotFile = screenshotFile;
		}

	}

	@Getter
	@Setter
	public static class Post extends ScreenShotEvent {
		protected List<IChatComponent> extraComponents = new ArrayList<>();

		public Post(File screenshotFile) {
			this.screenshotFile = screenshotFile;
		}

	}

}
