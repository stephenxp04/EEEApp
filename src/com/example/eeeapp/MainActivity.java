package com.example.eeeapp;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private static final int SIDE_ACT_CODE = 1;
	private ArrayAdapter<String> navDrawerAdapter;
	private ListView navDrawerList;
	private String[] navDrawerItems;
	private TextView mainFragmentText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.replace(R.id.main_fragment, new thingFrag()).commit();
		}

		navDrawerItems = getResources().getStringArray(R.array.nav_drawer_items);
		navDrawerAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, navDrawerItems);
		navDrawerList = (ListView)findViewById(R.id.nav_drawer_list);
		navDrawerList.setAdapter(navDrawerAdapter);
		mainFragmentText = (TextView)findViewById(R.id.main_fragment_text);
		
		SharedPreferences sp = getSharedPreferences("app_settings", MODE_PRIVATE);
		sp.edit().putString("Titties", "80085").apply();
		Toast.makeText(this, sp.getString("Titties", "null"), Toast.LENGTH_SHORT).show();
		
//		DownloadManager mManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
	}

	private class thingFrag extends Fragment {
		public thingFrag() {}
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.main_fragment, container, false);
			return rootView;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent action) {
		switch(requestCode) {
		case SIDE_ACT_CODE:
			if (resultCode == RESULT_OK) {
				mainFragmentText.setText("I got clicked");
			} else if (resultCode == RESULT_CANCELED) {
				mainFragmentText.setText("Nope. Failed.");
			}
		}
	}
}

