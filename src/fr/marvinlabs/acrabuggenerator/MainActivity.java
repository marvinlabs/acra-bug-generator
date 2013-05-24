package fr.marvinlabs.acrabuggenerator;

import org.acra.ACRA;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences prefs = getPreferences(MODE_PRIVATE);

		serverUri = (TextView) findViewById(R.id.server_uri);
		serverUri.setText(prefs.getString("server_uri", "acra-server-demo.marvinlabs.com"));

		((Button) findViewById(R.id.btn_npe)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_re)).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public void onClick(View v) {
		int id = v.getId();

		// Remember the server
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		prefs.edit().putString("server_uri", serverUri.getText().toString())
				.commit();

		// Update ACRA configuration
		ACRA.getConfig().setFormUri(getFormUri());

		// Crash!
		if (id == R.id.btn_re) {
			throw new RuntimeException("Some message for the exception");
		} else if (id == R.id.btn_npe) {
			Object obj = null;
			Log.d("", obj.toString());
		}
	}

	private String getFormUri() {
		return "http://" + serverUri.getText() + "/crash/add";
	}

	private TextView serverUri;
}
