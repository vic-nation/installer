package com.techygeek.installer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Backup button
        final Button button_backup = (Button) findViewById(R.id.button_backup);
        button_backup.setOnClickListener(new View.OnClickListener() {

            /* Checks if external storage is available for read and write /
            public boolean isExternalStorageWritable() {
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    return true;
                }
                return false;
            }*/

    @Override
            public void onClick(View v) {
                Runtime runtime = Runtime.getRuntime();
                Process proc = null;
                OutputStreamWriter osw = null;

                String command = "tar -cf /external_sd/MyDataBackup/data.tar /data";

                try { // Run Script

                    proc = runtime.exec("su");
                    osw = new OutputStreamWriter(proc.getOutputStream());
                    osw.write(command);
                    osw.flush();
                    osw.close();

                    Context context = getApplicationContext();
                    CharSequence text = "Backing Data up!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            ProgressBar mProgress = (ProgressBar) findViewById(R.id.progress_circular);
            mProgress.setVisibility(View.VISIBLE);
            mProgress.setVisibility(View.GONE);
        });

        // Restore button
        final Button button_restore = (Button) findViewById(R.id.button_restore);
        button_restore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        // Install Recovery Button
        final Button button_installrec = (Button) findViewById(R.id.button_installrec);
        button_installrec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Runtime runtime = Runtime.getRuntime();
                Process proc = null;
                OutputStreamWriter osw = null;

                String command = "wget -P /data/local/tmp/ http://unleashedprepaids.com/upload/devs/playfulgod/phones/LG/MS770/l0-cwm.lok && dd if=/data/local/tmp/l0-cwm.lok of=/dev/block/platform/msm_sdcc.1/by-name/recovery && rm /data/local/tmp/l0-cwm.lok";

                try { // Run Script

                    proc = runtime.exec("su");
                    osw = new OutputStreamWriter(proc.getOutputStream());
                    osw.write(command);
                    osw.flush();
                    osw.close();

                    Context context = getApplicationContext();
                    CharSequence text = "Downloading and Installing Recovery";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }
        });

// Reboot to Recovery Button
        final Button button_rebootrec = (Button) findViewById(R.id.button_rebootrec);
        button_rebootrec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Runtime runtime = Runtime.getRuntime();
                Process proc = null;
                OutputStreamWriter osw = null;

                String command = "/system/bin/reboot recovery";

                try { // Run Script

                    proc = runtime.exec("su");
                    osw = new OutputStreamWriter(proc.getOutputStream());
                    osw.write(command);
                    osw.flush();
                    osw.close();

                    Context context = getApplicationContext();
                    CharSequence text = "Rebooting to Recovery";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.quit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*  private boolean checkForDirectory()
    {
        boolean cardstate = true;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_BAD_REMOVAL.equals(state)) {
            cardstate = false;
            runDialog("Memory Card was removed before it was unmounted");
        }
        else if (Environment.MEDIA_CHECKING.equals(state)) {
            runDialog("Memory Card is present and being disk-checked");
        }
        else if (Environment.MEDIA_MOUNTED.equals(state)) {
            cardstate = true;
            //runDialog("Memory Card is present and mounted with read/write access");
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            runDialog("Memory Card is present and mounted with readonly access");
        }
        else if (Environment.MEDIA_NOFS.equals(state)) {
            cardstate = false;
            runDialog("Memory Card is present but is blank or using unsupported file system");
        }
        else if (Environment.MEDIA_REMOVED.equals(state)) {
            cardstate = false;
            runDialog("Memory Card is not present");
        }
        else if (Environment.MEDIA_SHARED.equals(state)) {
            cardstate = false;
            runDialog("Memory Card is present but shared via USB mass storage");
        }
        else if (Environment.MEDIA_UNMOUNTABLE.equals(state)) {
            cardstate = false;
            runDialog("Memory Card is present but cannot be mounted");
        }
        else if (Environment.MEDIA_UNMOUNTED.equals(state)) {
            cardstate = false;
            runDialog("Memory Card is present but not mounted");
        }

        File dir = new File(Environment.getExternalStorageDirectory() + "/MyDataBackup");
        if(dir.exists() && dir.isDirectory())
        {
            System.out.println("Folder exists");
        }
        else
        {
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory + "/MyDataBackup");
            myNewFolder.mkdir();
            System.out.println("Folder MyDataBackup created ");
        }
        return cardstate;
    }*/

}
