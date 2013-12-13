package com.android.mobileevent;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class CreateEvents extends Activity {
	private String titre ;
	private String nom;
	private String email;
	private ArrayList<String> jour;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.presentation);
	}

}
