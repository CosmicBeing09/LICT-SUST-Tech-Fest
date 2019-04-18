package com.example.raihan.sharefoods;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarChart_Activity extends AppCompatActivity {

    protected Typeface tfRegular;
    protected Typeface tfLight;
    private Switch aSwitch;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_);
        final BarChart chart = findViewById(R.id.barChart);
        tfRegular = Typeface.DEFAULT;
        tfLight = Typeface.DEFAULT;
        aSwitch = findViewById(R.id.switchDashBoard);

        Call<List<V_info_object>> call = AppClient.getApiClient().create(IApi_Vinfo.class).getVinfo();

        call.enqueue(new Callback<List<V_info_object>>() {
            @Override
            public void onResponse(Call<List<V_info_object>> call, Response<List<V_info_object>> response) {
                List<BarEntry> barEntries = new ArrayList<>();

                for(V_info_object v_info_object: response.body())
                {
                    barEntries.add(new BarEntry(v_info_object.getDay(),v_info_object.getResponse()));

                }
                BarDataSet barDataSet = new BarDataSet(barEntries,"Volunteer Info");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                BarData barData = new BarData(barDataSet);
                barData.setBarWidth(1f);

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
            public void onFailure(Call<List<V_info_object>> call, Throwable t) {

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


