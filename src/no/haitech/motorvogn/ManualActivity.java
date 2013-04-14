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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import no.haitech.motorvogn.R;
import no.haitech.vegvesen.VegvesenAPI;
import no.haitech.vegvesen.Vehicle;

/**
 * This activity contains manual input for user. Users can type in their
 * vehicle registration number. 
 * 
 * @author Thomas Le
 *
 */
public class ManualActivity extends Activity {
    Context context;
    EditText etVehicleNumber;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        context = getApplicationContext();
        res = getResources();
        
        etVehicleNumber = (EditText) findViewById(R.id.etVehicleNumber1);
        
        // Makes the window fullscreen, with titlebar.
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
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
    
    /**
     * OnClick listener for Search button.
     * Checks if there is Internet Connection, if not; toast a message to user.
     * if yes; Opens a background process ({@code GrabVehicle} and checks for 
     * registration number.
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void searchOnClick(View view) {
        // Vehicle number
        String vehicleNumber = etVehicleNumber.getText().toString();
        
        // Check for connection
        ConnectivityManager connMgr = (ConnectivityManager) 
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new GrabVehicle().execute(vehicleNumber);
        } else {
            Toast.makeText(context, res.getString(
                    R.string.manual_txtNoInternet),Toast.LENGTH_SHORT).show();
        }
    }
    
    
    /*
     * {@code AsyncTask}, a background process.
     * Using {@code VegvesenAPI} to lookup registration number.
     * If success; Opens a new intent, {@code ResultActivity}.
     * If failure; Gives a toast message to user.
     */
    private class GrabVehicle extends AsyncTask<String, Void, Vehicle> {
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(ManualActivity.this);
        
        protected void onPreExecute() {
            Dialog.setMessage(res.getString(R.string.manual_txtSearching));
            Dialog.show();
        }

        protected Vehicle doInBackground(String... vehicleNumber) {
            try {
                VegvesenAPI vegvesen = new VegvesenAPI(vehicleNumber[0]);
                return vegvesen.getVehicle();
            } catch (ParserConfigurationException e) {
                // Something went wrong with SAX Parser.
                Error = e.getMessage();
                cancel(true);
            } catch (SAXException e) {
                // Something went wrong with SAX Parser.
                Error = e.getMessage();
                cancel(true);
            } catch (ProtocolException e) {
                // Something went wrong with HTTPURLConnection
                Error = e.getMessage();
                cancel(true);
            } catch (MalformedURLException e) {
                // Something went wrong with HTTPURLConnection
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                // Couldn't open url.connection.
                Error = e.getMessage();
                cancel(true);
            }
            
            return null;
        }
        
        protected void onPostExecute(Vehicle result) {
            Dialog.dismiss();
            
            if (Error != null) {
                Toast.makeText(ManualActivity.this, Error,
                        Toast.LENGTH_LONG).show();
            } else {
                if(result == null) {
                    Toast.makeText(ManualActivity.this, "Ugyldig registreringsnr.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(ManualActivity.this,
                            ResultActivity.class);
                    intent.putExtra("Vehicle", result);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                }
            }
        }
        
    }
    
    
    

}
