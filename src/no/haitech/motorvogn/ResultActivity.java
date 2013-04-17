/*
 * Copyright (C) 2013 Thomas Le
 * 
 * This file is part of MotorvognRegister.
 *
 * MotorvognRegister is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MotorvognRegister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public license
 * along with MotorvognRegister. If not, see <http://www.gnu.org/licenses/>.
 */
package no.haitech.motorvogn;

import no.haitech.vegvesen.Vehicle;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity shows the result of the search.
 * 
 * 
 * @author Thomas Le
 *
 */
public class ResultActivity extends Activity {
    LinearLayout llResults;
    LayoutParams lp;
    TextView tvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        
        // Makes the window fullscreen, with titlebar.
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Testing
        Bundle b = getIntent().getExtras();
        Vehicle v = b.getParcelable("Vehicle");
        Toast.makeText(ResultActivity.this, "" + v.getMerke(),  Toast.LENGTH_LONG).show();
        
        llResults = (LinearLayout) findViewById(R.id.llResults);
        
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 40);
        tvItem = new TextView(this);
        lp.setMargins(0, 5, 0, 5);
        tvItem.setLayoutParams(lp);
        tvItem.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        tvItem.setText("hello world1");
        llResults.addView(tvItem);
        
        for(int i = 2; i < 41; i++) {
            tvItem = new TextView(this);
            tvItem.setLayoutParams(lp);
            tvItem.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            tvItem.setText("hello world"+i);
            llResults.addView(tvItem);
        }

        
   
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                Intent parentActivityIntent = new Intent(this, MainActivity.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
