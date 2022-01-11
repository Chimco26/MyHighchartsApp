package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.highsoft.highcharts.common.HIColor;
import com.highsoft.highcharts.common.hichartsclasses.HICSSObject;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIDataLabels;
import com.highsoft.highcharts.common.hichartsclasses.HIEvents;
import com.highsoft.highcharts.common.hichartsclasses.HIHeatmap;
import com.highsoft.highcharts.common.hichartsclasses.HILegend;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HITooltip;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.HIChartView;
import com.highsoft.highcharts.core.HIFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    HIOptions options = new HIOptions();
    HIChartView chartView;

    private ArrayList<String> YLabels;
    private ArrayList<String> XLabels;
    private  ArrayList<Number[]> heatmapData;
    private ArrayList<String> mKeys;


    public ArrayList<String> getmYLabels() {
        return YLabels;
    }

    public void setYLabels(ArrayList<String> YLabels) {
        this.YLabels = YLabels;
    }

    public ArrayList<String> getmXLabels() {
        return XLabels;
    }

    public void setXLabels(ArrayList<String> XLabels) {
        this.XLabels = XLabels;
    }

    public ArrayList<Number[]> getmHeatMapData() {
        return heatmapData;
    }

    public void setHeatmapData(ArrayList<Number[]> heatmapData) {
        this.heatmapData = heatmapData;
    }

    public ArrayList<String> getmKeys() {
        return mKeys;
    }

    public void setKeys(ArrayList<String> mKeys) {
        this.mKeys = mKeys;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        chartView = findViewById(R.id.hc2);
        Button mSwitchButton = findViewById(R.id.switch_button2);
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        setXLabels(getXLabels());
        setYLabels(getYLabels());
        setHeatmapData(getNumberArrayList());
        setKeys(getKeys());

        initOptions();


    }

    public static ArrayList<String> getKeys() {
        ArrayList<String> mKeys = new ArrayList<>();
        mKeys.add("x");
        mKeys.add("y");
        mKeys.add("value");
        mKeys.add("color");
        mKeys.add("YName");
        mKeys.add("XName");
        mKeys.add("valueInPercentage");
        mKeys.add("target");
        mKeys.add("Percentage");
        return mKeys;
    }
    public static ArrayList<String> getXLabels() {
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
        return mXlabels;
    }

    public ArrayList<String> getYLabels() {
        ArrayList<String> mYlabels = new ArrayList<>();
        mYlabels.add("03-01");
        return mYlabels;
    }

    public ArrayList<Number[]> getNumberArrayList(){
        Number[][] heatmapData = new Number[][]{
                {0, 0, 10},
                {0, 1, 19},
                {0, 2, 8},
                {0, 3, 24},
                {0, 4, 67},
                {1, 0, 92},
                {1, 1, 58},
                {1, 2, 78},
                {1, 3, 117},
                {1, 4, 48},
                {2, 0, 35},
                {2, 1, 15},
                {2, 2, 123},
                {2, 3, 64},
                {2, 4, 52},
                {3, 0, 72},
                {3, 1, 132},
                {3, 2, 114},
                {3, 3, 19},
                {3, 4, 16},
                {4, 0, 38},
                {4, 1, 5},
                {4, 2, 8},
                {4, 3, 117},
                {4, 4, 115},
                {5, 0, 88},
                {5, 1, 32},
                {5, 2, 12},
                {5, 3, 6},
                {5, 4, 120},
                {6, 0, 13},
                {6, 1, 44},
                {6, 2, 88},
                {6, 3, 98},
                {6, 4, 96},
                {7, 0, 31},
                {7, 1, 1},
                {7, 2, 82},
                {7, 3, 32},
                {7, 4, 30},
                {8, 0, 85},
                {8, 1, 97},
                {8, 2, 123},
                {8, 3, 64},
                {8, 4, 84},
                {9, 0, 47},
                {9, 1, 114},
                {9, 2, 31},
                {9, 3, 48},
                {9, 4, 91}
        };
        return new ArrayList<>(Arrays.asList(heatmapData));
    }

    private HITitle getTitle(String titleTxt, int fontSizePx) {
        HITitle title = new HITitle();
        title.setText(titleTxt);
        title.setStyle(new HICSSObject());
        title.getStyle().setFontSize(fontSizePx + "px");
        return title;
    }

    public void initOptions() {

        options.setChart(getChart("heatmap"));

        options.setXAxis(getXAxis());

        options.setYAxis(getYAxis());

        options.setLegend(getLegend());

        options.setTooltip(setTooltip());

        options.setSeries(getSeries());

        options.setPlotOptions(getPlotOptions());

        options.additionalOptions = new HashMap<>();
        options.additionalOptions.put("colorAxis", getColorAxisOptions());
        chartView.setOptions(options);

    }

    private HashMap<String, Object> getColorAxisOptions() {
        HashMap<String, Object> colorAxisOptions = new HashMap<>();
        colorAxisOptions.put("min", 0);
        colorAxisOptions.put("minColor", "#FFFFFF");
        colorAxisOptions.put("maxColor", "#7cb5ec");
        return colorAxisOptions;
    }

    private ArrayList<HIDataLabels> getDataLabels() {
        ArrayList<HIDataLabels> hiDataLabelsArrayList = new ArrayList<HIDataLabels>();
        HIDataLabels hiDataLabels = new HIDataLabels();
        hiDataLabels.setEnabled(true);
        //also try to add this:
//        hiDataLabels.setStyle(new HIStyle());
//        hiDataLabels.getStyle().setColor("000000");
        hiDataLabels.setColor(HIColor.initWithHexValue("000000"));
        hiDataLabelsArrayList.add(hiDataLabels);
        return hiDataLabelsArrayList;
    }

    private HIChart getChart(String type) {
        HIChart chart = new HIChart();
        chart.setZoomType("xy");
        chart.setType(type);
        chart.setPlotBorderWidth(1);
        chart.setEvents(new HIEvents());

        return chart;
    }

    private ArrayList<HIXAxis> getXAxis() {
        HIXAxis xaxis = new HIXAxis();
        xaxis.setCategories(XLabels);
        xaxis.setGridLineWidth(2);
        xaxis.setGridLineColor(HIColor.initWithHexValue("000000"));
        return new ArrayList<>(Collections.singletonList(xaxis));
    }

    private ArrayList<HIYAxis> getYAxis() {
        HIYAxis yaxis = new HIYAxis();
        yaxis.setCategories(YLabels);
        yaxis.setTitle(new HITitle());
        yaxis.getTitle().setText("");
        yaxis.setGridLineWidth(2);
        yaxis.setGridLineColor(HIColor.initWithHexValue("000000"));
        return new ArrayList<>(Collections.singletonList(yaxis));
    }

    private HILegend getLegend() {
        HILegend legend = new HILegend();
        legend.setEnabled(false);
        return legend;
    }

    private HITooltip setTooltip() {
        HITooltip tooltip = new HITooltip();
        tooltip.setUseHTML(true);
        tooltip.setShared(true);
        tooltip.setFormatter(new HIFunction("function () { return '" +  " <b>' + this.point.YName + '</b><br>"  +  " ' + this.point.XName + ' "  +
                " <b>' + this.point.valueInPercentage + '</b><br>" + "target_" + " <b>' " +
                "+ this.point.target + '</b><br>" + "percentage" + " <b>' + this.point.Percentage + '</b>' }"));
        return tooltip;
    }

    private ArrayList<HISeries> getSeries() {

        HIHeatmap heatmap = new HIHeatmap();
        heatmap.setName("");
        heatmap.setData(heatmapData);
        //try to add this also before setdata()
        heatmap.setDataLabels(getDataLabels());
        heatmap.setKeys(mKeys);
        heatmap.setPointPadding(1);
        return new ArrayList<>(Collections.singletonList(heatmap));
    }

    private HIPlotOptions getPlotOptions() {
        //try with and without this
        HIPlotOptions plotOptions = new HIPlotOptions();
        plotOptions.setSeries(new HIHeatmap());
        plotOptions.getSeries().setDataLabels(getDataLabels());
        return plotOptions;
    }
}