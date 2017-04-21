package com.venky97vp.android.dietjanitor;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ArcProgress dietProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        dietProgress = (ArcProgress) view.findViewById(R.id.diet_progress);
        dietProgress.setFinishedStrokeColor(getResources().getColor(R.color.colorPrimary));
        dietProgress.setUnfinishedStrokeColor(getResources().getColor(R.color.background));
        dietProgress.setTextColor(getResources().getColor(R.color.colorPrimary));
        dietProgress.setProgress(53);

        /*ValueLineChart mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.diet_linechart);
        mCubicValueLineChart.setUseCubic(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mCubicValueLineChart.forceHasOverlappingRendering(true);
        }

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(getResources().getColor(R.color.colorPrimary));

        series.addPoint(new ValueLinePoint("Sun", 56));
        series.addPoint(new ValueLinePoint("Mon", 23));
        series.addPoint(new ValueLinePoint("Tue", 12));
        series.addPoint(new ValueLinePoint("Wed", 98));
        series.addPoint(new ValueLinePoint("Thu", 76));
        series.addPoint(new ValueLinePoint("Fri", 85));
        series.addPoint(new ValueLinePoint("Sat", 54));


        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
*/
        LineChart monthChart = (LineChart) view.findViewById(R.id.diet_MPLineChartMonth);

        ArrayList<Entry> points = new ArrayList<Entry>();
        for (int i = 0; i < 28; i++) {
            points.add(new Entry(1750 + new Random().nextInt(1500), i));
        }
        LineDataSet dataSet = new LineDataSet(points, "Calories/Day in KCals");
        dataSet.setDrawFilled(true);
        ArrayList<String> Labels = new ArrayList<String>();
        for (int i = 28; i >= 1; i--) {
            Labels.add(Integer.toString(i));
        }
        LimitLine RequiredCalorie = new LimitLine(2500f);
        LineData monthChartData = new LineData(Labels, dataSet);
        monthChart.getAxisLeft().addLimitLine(RequiredCalorie);
        monthChart.setData(monthChartData);
        monthChart.setDescription("One Month data");
        monthChart.zoom(2.2f, 0f, 0f, 0f);
        monthChart.setGridBackgroundColor(Color.WHITE);
        monthChart.getXAxis().setDrawGridLines(false);
        monthChart.getAxisRight().setDrawGridLines(false);
        monthChart.getAxisLeft().setDrawGridLines(false);
        monthChart.getAxisLeft().setDrawLabels(false);
        monthChart.getAxisRight().setDrawLabels(false);
        monthChart.getXAxis().setTextColor(Color.parseColor("#A9A9A9"));
        monthChart.getAxisLeft().setAxisLineColor(Color.parseColor("#00FF0000"));
        monthChart.getAxisRight().setAxisLineColor(Color.parseColor("#00FF0000"));
        monthChart.getLineData().setValueTextColor(Color.parseColor("#C0C0C0"));
        monthChart.getXAxis().setAxisLineColor(Color.parseColor("#00FF0000"));
        monthChart.animateXY(1000, 1000);

        LineChart WeekChart = (LineChart) view.findViewById(R.id.diet_MPLineChartWeek);
        ArrayList<Entry> WeekEntries = new ArrayList<Entry>();
        String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        ArrayList<String> LabelsMonth = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            LabelsMonth.add(weeks[i]);
            WeekEntries.add(new Entry(1700 + new Random().nextInt(1500), i));
        }
        LineDataSet weekDataSet = new LineDataSet(WeekEntries, "Calories this week");
        weekDataSet.setDrawFilled(true);
        LineData WeekData = new LineData(LabelsMonth, weekDataSet);
        WeekChart.setData(WeekData);
        WeekChart.getAxisLeft().addLimitLine(RequiredCalorie);
        WeekChart.setDescription("One week data");
        WeekChart.setBorderColor(getResources().getColor(R.color.colorAccent));
        WeekChart.setGridBackgroundColor(Color.WHITE);
        WeekChart.getXAxis().setDrawGridLines(false);
        WeekChart.getAxisLeft().setDrawGridLines(false);
        WeekChart.getAxisRight().setDrawGridLines(false);
        WeekChart.getAxisLeft().setDrawLabels(false);
        WeekChart.getLineData().setValueTextColor(Color.parseColor("#C0C0C0"));
        WeekChart.getAxisRight().setDrawLabels(false);
        WeekChart.getXAxis().setTextColor(Color.parseColor("#A9A9A9"));
        WeekChart.getAxisLeft().setAxisLineColor(Color.parseColor("#00FF0000"));
        WeekChart.getAxisRight().setAxisLineColor(Color.parseColor("#00FF0000"));
        WeekChart.getXAxis().setAxisLineColor(Color.parseColor("#00FF0000"));
        WeekChart.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorPrimary));
        WeekChart.animateXY(1000, 1000);


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
