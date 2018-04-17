package com.shishuheng.zreader.ui.popupWindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shishuheng.zreader.R;
import com.shishuheng.zreader.ui.filePicker.FilePicker;

public class PopupWindow extends AppCompatActivity{
    Activity activity;

    //悬浮按钮相关控件
    private android.widget.PopupWindow popupWindow;
    private Button addButton_popupWindow;
    private Button editButton_popupWindow;
    private View addButtonView;

    public PopupWindow(Activity activity) {
        this.activity = activity;
    }

    public void setting() {
        //设置悬浮按钮 PopupWindow参考 https://www.jianshu.com/p/825d1cc9fa79
        addButtonView = LayoutInflater.from(this).inflate(R.layout.addbutton_pop, null, false);
        popupWindow = new android.widget.PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(addButtonView);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
        addButton_popupWindow = addButtonView.findViewById(R.id.add_addButton);
        addButton_popupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilePicker filePicker = new FilePicker(activity, Environment.getExternalStorageDirectory().getAbsolutePath());
                filePicker.create().show();
                popupWindow.dismiss();
            }
        });
        editButton_popupWindow = activity.findViewById(R.id.edit_addButton);
    }

    public android.widget.PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public Button getAddButton_popupWindow() {
        return addButton_popupWindow;
    }

    public Button getEditButton_popupWindow() {
        return editButton_popupWindow;
    }

    public View getAddButtonView() {
        return addButtonView;
    }
}
