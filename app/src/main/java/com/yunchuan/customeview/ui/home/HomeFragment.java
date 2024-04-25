package com.yunchuan.customeview.ui.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.yunchuan.customeview.R;
import com.yunchuan.customeview.databinding.FragmentHomeBinding;
import com.yunchuan.customeview.view.SlashedBarChartRenderer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        initView();
        return root;
    }

    private void initView() {
        initChar();
        BarDataSet dataSet = new BarDataSet(getData(), "");
        dataSet.setValueTextColor(Color.WHITE);//修改折线处文字颜色
        dataSet.setValueTextSize(18f);//修改折现处文字大小
        dataSet.setBarShadowColor(requireActivity().getColor(R.color.sha));//设置阴影的颜色
       dataSet.setColor(requireActivity().getColor(R.color.char_color));

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int)value+"";
            }
        });
        BarData barData=new BarData(dataSet);
        barData.setBarWidth(0.4f);//设置柱块宽度
        binding.barChart.setData(barData);
        setX();
        setY();
    }
    private void initChar(){
        binding.barChart.setDrawBarShadow(true);//显示阴影
binding.barChart.getDescription().setEnabled(false);
binding.barChart.getLegend().setEnabled(false);
//       binding.barChart.setRenderer(new SlashedBarChartRenderer(binding.barChart,binding.barChart.getAnimator(),binding.barChart.getViewPortHandler() ));

    }

    private void setY() {

            //不显示右侧的Y轴
            binding.barChart.getAxisRight().setEnabled(false);
            YAxis yAxis= binding.barChart.getAxisLeft();
//        yAxis.setDrawLabels(false);//去掉label
            yAxis.setTextColor(Color.WHITE);
            yAxis.setAxisMaximum(10);
            yAxis.enableGridDashedLine(5f,5f,0f);//设置虚线
            yAxis.setGridLineWidth(1f);

            yAxis.setAxisMinimum(0);
            yAxis.setDrawAxisLine(false);//不设置最左边的竖线

    }

    private void setX() {
        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setDrawAxisLine(true);//是否显示X轴线
        xAxis.enableGridDashedLine(10f,10f,0f);//设置虚线
        xAxis.setGridLineWidth(2f);//设置虚线宽度
        xAxis.setAxisLineColor(Color.parseColor("#cccccc"));// 设置x轴线的颜色
// 是否绘制x方向网格线
        xAxis.setDrawGridLines(true);
        //x方向网格线的颜色
        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));

        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置x轴文字的大小
        xAxis.setTextSize(12);
       xAxis.setTextColor(Color.WHITE);//设置X轴字体颜色

        String[] days={"一楼","二楼","四楼","五楼"};
        xAxis.setLabelCount(days.length);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return days[(int) value];
            }
        });


    }

    private List<BarEntry> getData() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 7));
        entries.add(new BarEntry(1, 6));
        entries.add(new BarEntry(2, 8));
        entries.add(new BarEntry(3, 4));
        return entries;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}