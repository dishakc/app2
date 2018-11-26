package example.com.app2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import example.com.app2.Views.CustomView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private CustomView mCustomView;
        Button alert;
        Button Progress;
        Button lis;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomView=(CustomView)findViewById(R.id.CustomView);
        findViewById(R.id.swap).setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View V)
                        {
                        mCustomView.SwapColor();
                        }
        });
        Button btn = (Button) findViewById(R.id.longpress);
        registerForContextMenu(btn);

        alert = (Button) findViewById(R.id.alert);
        alert.setOnClickListener(MainActivity.this);

        Progress = (Button) findViewById(R.id.Progress);
        Progress.setOnClickListener(MainActivity.this);

        lis = (Button) findViewById(R.id.lis);
        lis.setOnClickListener(MainActivity.this);

        }

@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Upload");
        menu.add(0, v.getId(), 3, "Search");
        menu.add(0, v.getId(), 2, "Share");
        menu.add(0, v.getId(), 1, "Bookmark");
        }
@Override
public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
        }
@Override
public boolean onCreateOptionsMenu(Menu menu)
        {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
        }

@Override
public boolean  onOptionsItemSelected(MenuItem item)

        {
        switch (item.getItemId()){
        case R.id.item1:
        Toast.makeText(this,"option 1 is selected",Toast.LENGTH_SHORT).show();
        break;

        case R.id.item2:
        Toast.makeText(this,"option 2 is selected",Toast.LENGTH_SHORT).show();
        break;
        case R.id.item3:
        Toast.makeText(this,"option 3 is selected",Toast.LENGTH_SHORT).show();
        break;

        }
        return super.onOptionsItemSelected(item);
        }

@Override
public void onClick(View v) {
        switch (v.getId()) {
        case R.id.alert:
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Do you want to close this app!!!").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        finish();
                        }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        }
                        });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("ALERT!!");
                        alert.show();
                        break;
        case R.id.Progress:
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Download");
                progressDialog.setMessage("Downloading....");
                progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);

                Thread t = new Thread(new Runnable() {
                        @Override
                         public void run() {
                                int progress = 0;
                                while (progress <= 100) {
                                        try {
                                        progressDialog.setProgress(progress);
                                        progress++;
                                        Thread.sleep(200);
                                        }
                                        catch (Exception ex)
                                        { }
                                }
                                progressDialog.dismiss();
                                MainActivity.this.runOnUiThread(new Runnable()
                                {
                                        @Override
                                        public void run() {
                                        Toast.makeText(MainActivity.this, "Download completed", Toast.LENGTH_SHORT).show();
                                        }
                                });
                        }
                });
                t.start();
                progressDialog.show();
                break;
        case R.id.lis:
                Intent in = new Intent(MainActivity.this, ListAdapter.class);
                startActivity(in);
                break;
        }
   }
}
