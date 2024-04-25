package com.yunchuan.customeview.ui.notifications;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.yunchuan.customeview.R;
import com.yunchuan.customeview.databinding.FragmentNotificationsBinding;

import org.w3c.dom.Entity;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        initView();
        return root;
    }

    private void initView() {
        ArrayList<Entry> entities=new ArrayList<>();
        entities.add(new Entry(0,30));
        entities.add(new Entry(1,50f));
//        entities.add(new Entry(1,50f));
        entities.add(new Entry(2,30f));
        entities.add(new Entry(3,80f));
//        entities.add(new Entry(4,100f));
//        entities.add(new Entry(5,0f));

        initLineChar();
        setYAxis();

        LineDataSet lineDataSet=new LineDataSet(entities,"");
        //修改折线的颜色
        lineDataSet.setColor(requireActivity().getResources().getColor(R.color.dot_color));
        lineDataSet.setLineWidth(1f);//修改折线的宽度

        lineDataSet.setValueTextColor(Color.WHITE);//修改折线处文字颜色
        lineDataSet.setValueTextSize(18f);//修改折现处文字大小
        lineDataSet.setCircleColor(requireActivity().getResources().getColor(R.color.dot_color));//设置圆点颜色
        lineDataSet.setDrawCircles(true);
        lineDataSet.setHighlightEnabled(false);//不显示定位线
        lineDataSet.setCircleHoleColor(Color.parseColor("#ffffff"));
        lineDataSet.setCircleHoleRadius(8f);//设置圆点半径
//        lineDataSet.setFillColor(requireActivity().getResources().getColor(R.color.fill_color));//设置折线下方填充颜色
        Drawable drawable=getResources().getDrawable(R.drawable.gradient_drawable);//设置折线下方填充颜色 渐变色
        lineDataSet.setFillDrawable(drawable);
        lineDataSet.setDrawFilled(true);//设置折线下方是否填充
        LineData data=new LineData(lineDataSet);
        binding.lineChart.setData(data);
    }

    private void initLineChar(){
        //设置虚线
        binding.lineChart.getAxisLeft().enableGridDashedLine(10f,10f,0f);
        binding.lineChart.getAxisLeft().setDrawZeroLine(true);

        binding.lineChart.getAxisRight().setDrawGridLines(false);
        binding.lineChart.getDescription().setEnabled(false);//不显示描述
        binding.lineChart.setSaveEnabled(false);//不可以缩放
        binding.lineChart.setDrawBorders(false);


        binding.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴位置
        binding.lineChart.getXAxis().setDrawGridLines(false);//不绘制X网格线
        binding.lineChart.getXAxis().setTextColor(Color.WHITE);//设置X轴字体颜色
        binding.lineChart.getXAxis().setTextSize(11f);//设置X轴字体大小
                binding.lineChart.getXAxis().setGranularity(1f);

        final String days[]={"一楼","二楼","四楼","五楼"};
        binding.lineChart.getXAxis().setAxisMaximum(3.2f);
        binding.lineChart.getXAxis().setAxisMinimum(-0.2f);
        binding.lineChart.getXAxis().setLabelCount(days.length);
//        binding.lineChart.getXAxis().setValueFormatter(new );
        binding.lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
//                Log.e("mxyang",value+"");
                return days[(int) value];
//                return "1";
            }


        });
//        binding.lineChart.getXAxis().setXOffset(-55f);
//                binding.lineChart.getXAxis().setCenterAxisLabels(true);//设置标签居中
        binding.lineChart.getXAxis().setDrawLabels(true);


//        binding.lineChart.getXAxis().setGranularityEnabled(true);//是否启用间隔
//        binding.lineChart.getXAxis().setGranularity(1f);

    }

    private void setYAxis(){
        //不显示右侧的Y轴
        binding.lineChart.getAxisRight().setEnabled(false);
        YAxis yAxis= binding.lineChart.getAxisLeft();
//        yAxis.setDrawLabels(false);//去掉label
        yAxis.setTextColor(Color.WHITE);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
        yAxis.setDrawAxisLine(false);//不设置最左边的竖线

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}