package com.fokakefir.spaceship.gui.viewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.fokakefir.spaceship.R;

public class ImageAdapter extends PagerAdapter {

    // region 1. Decl and Init

    private Context context;
    private int[] imageIds = new int[]{
            R.drawable.ic_door,
            R.drawable.ic_commander,
            R.drawable.ic_medical
    };

    // endregion

    // region 2. Constructor

    public ImageAdapter(Context context) {
        this.context = context;
    }


    // endregion

    // region 3. Methods

    @Override
    public int getCount() {
        return this.imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(this.context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(this.imageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    // endregion
}
