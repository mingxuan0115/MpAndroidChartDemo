package com.yunchuan.customeview.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class SlashedBarChartRenderer extends BarChartRenderer {

    private Path slashPath = new Path();

    public SlashedBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    public void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        super.drawDataSet(c, dataSet, index);

        if (mChart.getBarData().getDataSetCount() <= index) {
            return;
        }

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
        float phaseX = mAnimator.getPhaseX();
        float phaseY = mAnimator.getPhaseY();

        mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));

        boolean isSingleColor = dataSet.getColors().size() == 1;

        final float barWidth = mChart.getBarData().getBarWidth() / 2f;

        for (int i = 0; i < dataSet.getEntryCount(); i++) {
            BarEntry e = dataSet.getEntryForIndex(i);
            float x = e.getX();
            float y = e.getY();

            if (!isSingleColor) {
                mRenderPaint.setColor(dataSet.getColor(i));
            }

            float[] vals = e.getYVals();
            if (vals == null) {
                float bottom = x - barWidth;
                float top = x + barWidth;
                float yStart = y >= 0 ? y : 0;
                float yEnd = y <= 0 ? y : 0;

                if (yStart > 0) {
                    slashPath.reset();
                    slashPath.moveTo(bottom, yStart);
                    slashPath.lineTo(top, yEnd);
                    c.drawPath(slashPath, mRenderPaint); // 绘制斜杠
                }
            } else {
                // Stacked bars
                float posY = 0f;
                float negY = -e.getNegativeSum();

                // draw the negative bars
                for (int k = 0; k < vals.length; k++) {
                    float value = vals[k];
                    if (value == 0.0f && (posY == 0.0f || negY == 0.0f))
                        continue;
                    float bottom, top;
                    if (value >= 0.0f) {
                        posY += value;
                        top = posY;
                        bottom = posY - value;
                    } else {
                        top = negY;
                        bottom = negY + value;
                        negY += value;
                    }

                    bottom *= phaseY;
                    top *= phaseY;

                    slashPath.reset();
                    slashPath.moveTo(x - barWidth, top);
                    slashPath.lineTo(x + barWidth, bottom);
                    c.drawPath(slashPath, mRenderPaint); // 绘制斜杠
                }
            }
        }
    }
}

