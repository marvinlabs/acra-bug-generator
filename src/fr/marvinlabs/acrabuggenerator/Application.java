package fr.marvinlabs.acrabuggenerator;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

//@formatter:off
@ReportsCrashes(
		formKey = "", // will not be used
		
		formUri = "http://acra-server-demo.marvinlabs.com/crash/add", 
		
		httpMethod = org.acra.sender.HttpSender.Method.POST, 
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.bug_reported)
// @formatter:on
public class Application extends android.app.Application {
	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
	}
}
