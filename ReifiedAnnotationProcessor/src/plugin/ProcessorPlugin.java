package plugin;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class ProcessorPlugin extends Plugin {
	//The shared instance.
	private static ProcessorPlugin plugin;
	//Resource bundle.
	@SuppressWarnings("unused")
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public ProcessorPlugin() {
		super();
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ProcessorPlugin getDefault() {
		return plugin;
	}

}
