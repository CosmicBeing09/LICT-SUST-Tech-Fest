package com.example.raihan.sharefoods;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.raihan.sharefoods.Objects.RecordObject;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarChart_Activity extends AppCompatActivity {

    protected Typeface tfRegular;
    protected Typeface tfLight;
    private Switch aSwitch;
    Fragment fragment;
    public static int totalResponse = 0;
    public static int totalPost;
    ArrayList<Date> dateArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_);
        final BarChart chart = findViewById(R.id.barChart);
        tfRegular = Typeface.DEFAULT;
        tfLight = Typeface.DEFAULT;
        aSwitch = findViewById(R.id.switchDashBoard);

        final ProgressDialog Dialog = new ProgressDialog(BarChart_Activity.this);
        Dialog.setMessage("Please Wait.....");
        Dialog.show();

        Call<List<RecordObject>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getRecordObject();

        call.enqueue(new Callback<List<RecordObject>>() {
            @Override
            public void onResponse(Call<List<RecordObject>> call, Response<List<RecordObject>> response) {
                List<BarEntry> barEntries = new ArrayList<>();
                for(RecordObject recordObject: response.body())
                {
                    totalResponse++;

                    try {
                        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(recordObject.getFinished().toString().trim());

                       // Toast.makeText(BarChart_Activity.this,String.valueOf(date.getDate()+"\\"+date.getYear()),Toast.LENGTH_LONG).show();

                        barEntries.add(new BarEntry(date.getDate(),recordObject.getBeneficent()));
                        Dialog.dismiss();


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // barEntries.add(new BarEntry(v_info_object.getDay(),v_info_object.getResponse()));

                }
                Toast.makeText(BarChart_Activity.this,String.valueOf(totalResponse),Toast.LENGTH_LONG).show();
                barEntries.add(new BarEntry(20,15));

                barEntries.add(new BarEntry(21,17));
                barEntries.add(new BarEntry(22,8));
                BarDataSet barDataSet = new BarDataSet(barEntries,"Donation History");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                BarData barData = new BarData(barDataSet);
                barData.setBarWidth(1);

                chart.setVisibility(View.VISIBLE);
                chart.animateY(1000);
                chart.setData(barData);
                chart.setFitBars(true);

                Description description = new Description();
                description.setText("Volunteer Info in last weeks");
                chart.setDescription(description);
                chart.invalidate();

                ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTypeface(tfLight);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1f); // only intervals of 1 day
                xAxis.setLabelCount(7);
                xAxis.setValueFormatter(xAxisFormatter);
            }

            @Override
            public void onFailure(Call<List<RecordObject>> call, Throwable t) {

            }
        });

        Call<List<FoodRequestObject>> call1 = AppClient.getApiClient().create(IApi_Vinfo.class).getFoodRequestObject();
        call1.enqueue(new Callback<List<FoodRequestObject>>() {
            @Override
            public void onResponse(Call<List<FoodRequestObject>> call, Response<List<FoodRequestObject>> response) {
                for (FoodRequestObject foodRequestObject : response.body())
                {
                    totalPost++;
                }
            }

            @Override
            public void onFailure(Call<List<FoodRequestObject>> call, Throwable t) {

            }
        });


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(aSwitch.isChecked())
                {
                  fragment = new PieChart_Activity();
                  if(fragment!=null)
                  {
                      FragmentManager fragmentManager = getSupportFragmentManager();
                      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                      fragmentTransaction.replace(R.id.screenArea,fragment).addToBackStack("Tag");

//                      Bundle bundle = new Bundle();
//                      bundle.putString("Total_response",String.valueOf(totalResponse));
//                      fragment.setArguments(bundle);

                      fragmentTransaction.commit();
                  }

                }
                else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.remove(fragment).commit();
                }
            }
        });

    }
}


