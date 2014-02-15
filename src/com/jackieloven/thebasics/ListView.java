package com.jackieloven.thebasics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ListView extends ListActivity {

	String classNames[] = { "Main", "menu", "Sweet", "SecondScreen" };

	// an array uses []
	// and if you reference it later with a number in the brackets, it will
	// refer to a specific item
	// first item is item 0

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, classNames));

	}

	// it's what he calls TutorialTwo
	protected void onListItemClick(android.widget.ListView lv, View v, int position, long id) {
		super.onListItemClick(lv, v, position, id);
		String openClass = classNames[position];
		// passes in position when item is clicked
		try {
			Class selected = Class.forName("com.jackieloven.thebasics."
					+ openClass);
			Intent selectedIntent = new Intent(this, selected);
			startActivity(selectedIntent);
			// button pressed, gives string names, then intent of class starts
			// and opens class

		} catch (ClassNotFoundException e) {
			// exception is called e, standard
			e.printStackTrace();
		}
	}
}
