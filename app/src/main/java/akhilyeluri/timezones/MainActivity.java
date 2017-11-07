package akhilyeluri.timezones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
TextView date_at_timezone;
    String braces="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] akhil;
        akhil = TimeZone.getAvailableIDs();
        // populating the spinner
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,akhil);
        spinner.setAdapter(adapter);
        // On-Item-Click
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(MainActivity.this, ""+akhil[i], Toast.LENGTH_LONG).show();
                ShowZoneTime(akhil[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







       // TimeZone tz=TimeZone.getTimeZone(akhil[1]);
       // Calendar c =Calendar.getInstance(tz);
        //get date for given  timezone.
       //String day=String.format("%02d",c.get(Calendar.DAY_OF_MONTH));
        //get time for given timzone.
       // String time = String.format("%2d" , c.get(Calendar.HOUR_OF_DAY))+":"+
               // String.format("%02d" , c.get(Calendar.MINUTE))+":" + String.format("%2d" , c.get(Calendar.SECOND))+":"+ String.format("%2d" , c.get(Calendar.MILLISECOND));
       // ids.setText(time);
        //clock.getFormat12Hour();
        //clock.setTimeZone(akhil[1]);

    }

     public void ShowZoneTime(String id){
        TimeZone tz=TimeZone.getTimeZone(id);
         Calendar cal=Calendar.getInstance(tz);
         TextClock clock =(TextClock)findViewById(R.id.clock);
         //clock.getFormat12Hour();
         clock.setTimeZone(id);
         date_at_timezone=(TextView)findViewById(R.id.timezones);
         String date=String.format("%02d",cal.get(Calendar.DAY_OF_MONTH))+"-"+String.format("%s",cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.ENGLISH))+"-"+String.format("%d",cal.get(Calendar.YEAR));
         date_at_timezone.setText(date);
         TextView area=(TextView)findViewById(R.id.area);
         String area_name=id;
         area.setText(id);
         // getting gmt or utc
         TextView gmt_umt=(TextView)findViewById(R.id.gmtutc);
         int GmtOffset = tz.getRawOffset();
         int offsetInMillis=tz.getOffset(cal.getTimeInMillis());
         String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
         offset = "GMT"+(offsetInMillis >= 0 ? "+" : "-") + offset;
         gmt_umt.setText(offset);
    }
}