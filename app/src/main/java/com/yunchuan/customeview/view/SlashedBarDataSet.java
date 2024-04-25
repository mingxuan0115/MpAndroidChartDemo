//package com.yunchuan.customeview.view;
//
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//
//import java.util.List;
//
//// 创建一个自定义的 DataSet，用于斜杠填充
//public class SlashedBarDataSet extends BarDataSet {
//
//    private Drawable slashDrawable;
//
//    public SlashedBarDataSet(List<BarEntry> yVals, String label) {
//        super(yVals, label);
//    }
//
//    // 设置斜杠的 Drawable
//    public void setSlashDrawable(Drawable drawable) {
//        this.slashDrawable = drawable;
//    }
//
//
//
//    @Override
//    public void drawValues(Canvas c, float posX, float posY, float anchorX, float anchorY, Paint paint) {
//        // 在这里绘制斜杠
//        if (slashDrawable != null) {
//            slashDrawable.setBounds((int) (posX - slashDrawable.getIntrinsicWidth() / 2),
//                    (int) (posY - slashDrawable.getIntrinsicHeight() / 2),
//                    (int) (posX + slashDrawable.getIntrinsicWidth() / 2),
//                    (int) (posY + slashDrawable.getIntrinsicHeight() / 2));
//            slashDrawable.draw(c);
//        }
//        super.drawValues(c, posX, posY, anchorX, anchorY, paint);
//    }
//}
//
