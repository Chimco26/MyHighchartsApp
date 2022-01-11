package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.highsoft.highcharts.common.HIColor;
import com.highsoft.highcharts.common.hichartsclasses.*;
import com.highsoft.highcharts.core.HIChartView;
import com.highsoft.highcharts.core.HIFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

        HIOptions options = new HIOptions();
        HIChartView chartView;

        private ArrayList<String> YLabels;
        private ArrayList<String> XLabels;
        private  ArrayList<Object[]> heatmapData;
        private ArrayList<String> mKeys;
        private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chartView = findViewById(R.id.hc);
        Button mSwitchButton = findViewById(R.id.switch_button);
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
            }
        });

        ArrayList<String> mXlabels = new ArrayList<>();
        mXlabels.add("M-1");
        mXlabels.add("M-2");
        mXlabels.add("M-8");
        mXlabels.add("M-3");
        mXlabels.add("M-4");
        mXlabels.add("M-5");
        mXlabels.add("M-6");
        mXlabels.add("M-7");
        mXlabels.add("M-10");
        XLabels = mXlabels;

        ArrayList<String> mYlabels = new ArrayList<>();
        mYlabels.add("03-01");
        YLabels = mYlabels;

        heatmapData = mGson.fromJson("[[0,0,100,\"rgba(4,159,30,0.9\",\"03-01\",\"M-1\",\"100%\",\"100%\",\"100%\"],[1,0, 22.24%,\"rgba(208,210,45,0.9\",\"03-01\",\"M-2\",\"22.24%\",\"100%\",\"22%\"],[2,0,100,\"rgba(4,159,30,0.9\",\"03-01\",\"M-8\",\"100%\",\"100%\",\"100%\"],[3,0,1.42,\"rgba(230,29,15,0.9\",\"03-01\",\"M-3\",\"1.42%\",\"100%\",\"1%\"],[4,0,100,\"rgba(4,159,30,0.9\",\"03-01\",\"M-4\",\"100%\",\"100%\",\"100%\"],[5,0,\"100%\",\"rgba(4,159,30,0.9\",\"03-01\",\"M-5\",\"100%\",\"100%\",\"100%\"],[6,0,\"100%\",\"rgba(4,159,30,0.9\",\"03-01\",\"M-6\",\"100%\",\"100%\",\"100%\"],[7,0,\"98.27%\",\"rgba(4,159,30,0.9\",\"03-01\",\"M-7\",\"98.27%\",\"100%\",\"98%\"],[8,0,\"0.00%\",\"rgba(230,29,15,0.9\",\"03-01\",\"M-10\",\"0.00%\",\"100%\",\"0%\"]]\n" + " ",
                new TypeToken<ArrayList<Object[]>>() {}.getType());

        ArrayList<String> mKeys1 = new ArrayList<>();
        mKeys1.add("x");
        mKeys1.add("y");
        mKeys1.add("value");
        mKeys1.add("color");
        mKeys1.add("YName");
        mKeys1.add("XName");
        mKeys1.add("valueInPercentage");
        mKeys1.add("target");
        mKeys1.add("Percentage");
        mKeys = mKeys1;

        HIChart chart = new HIChart();
        chart.setZoomType("xy");
        chart.setType("heatmap");
        chart.setPlotBorderWidth(1);
        chart.setEvents(new HIEvents());

        options.setChart(chart);

        HIXAxis xaxis = new HIXAxis();
        xaxis.setCategories(XLabels);
        xaxis.setGridLineWidth(2);
        xaxis.setGridLineColor(HIColor.initWithHexValue("000000"));
        options.setXAxis(new ArrayList<HIXAxis>(Collections.singletonList(xaxis)));

        HIYAxis yaxis = new HIYAxis();
        yaxis.setCategories(YLabels);
        yaxis.setTitle(new HITitle());
        yaxis.getTitle().setText("");
        yaxis.setGridLineWidth(2);
        yaxis.setGridLineColor(HIColor.initWithHexValue("000000"));
        options.setYAxis(new ArrayList<HIYAxis>(Collections.singletonList(yaxis)));

        HILegend legend = new HILegend();
        legend.setEnabled(false);
        options.setLegend(legend);

        HITooltip tooltip = new HITooltip();
        tooltip.setUseHTML(true);
        tooltip.setShared(true);
        tooltip.setFormatter(new HIFunction("function () { return '" +  " <b>' + this.point.YName + '</b><br>"  +  " ' + this.point.XName + ' "  +
                " <b>' + this.point.valueInPercentage + '</b><br>" + "target_" + " <b>' " +
                "+ this.point.target + '</b><br>" + "percentage" + " <b>' + this.point.Percentage + '</b>' }"));
        options.setTooltip(tooltip);

        HIHeatmap heatmap = new HIHeatmap();
        heatmap.setName("");
        heatmap.setData(heatmapData);
        ArrayList<HIDataLabels> hiDataLabelsArrayList = new ArrayList<HIDataLabels>();
        HIDataLabels hiDataLabels = new HIDataLabels();
        hiDataLabels.setEnabled(true);
        hiDataLabels.setColor(HIColor.initWithHexValue("000000"));
        hiDataLabelsArrayList.add(hiDataLabels);
        heatmap.setDataLabels(hiDataLabelsArrayList);
        heatmap.setKeys(mKeys);
        heatmap.setPointPadding(1);
        options.setSeries(new ArrayList<HISeries>(Collections.singletonList(heatmap)));

        HIPlotOptions plotOptions = new HIPlotOptions();
        plotOptions.setSeries(new HIHeatmap());
        ArrayList<HIDataLabels> hiDataLabelsArrayList1 = new ArrayList<HIDataLabels>();
        HIDataLabels hiDataLabels1 = new HIDataLabels();
        hiDataLabels1.setEnabled(true);
        hiDataLabels1.setColor(HIColor.initWithHexValue("000000"));
        hiDataLabelsArrayList1.add(hiDataLabels1);
        plotOptions.getSeries().setDataLabels(hiDataLabelsArrayList1);
        options.setPlotOptions(plotOptions);

        options.additionalOptions = new HashMap<>();
        HashMap<String, Object> colorAxisOptions = new HashMap<>();
        colorAxisOptions.put("min", 0);
        colorAxisOptions.put("minColor", "#FFFFFF");
        colorAxisOptions.put("maxColor", "#7cb5ec");
        options.additionalOptions.put("colorAxis", colorAxisOptions);
        chartView.setOptions(options);

    }

}