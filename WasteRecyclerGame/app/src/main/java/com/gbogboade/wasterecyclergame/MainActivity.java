package com.gbogboade.wasterecyclergame;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView plasticBasketImageView;
    private ImageView glassBasketImageView;
    private ImageView metalBasketImageView;
    private ImageView paperBasketImageView;
    private ImageView wasteImageView;

    private TextView positionTextView;

    private LayoutParams layoutParams;

    private static final String PLASTIC_BASKET_TAG = "plastic waste basket";
    private static final String GLASS_BASKET_TAG = "glass waste basket";
    private static final String METAL_BASKET_TAG = "metal waste basket";
    private static final String PAPER_BASKET_TAG = "papaer waste basket";
    private static final String WASTE_BASKET_TAG = "waste image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plasticBasketImageView = (ImageView) findViewById(R.id.plastic_imageView);
        glassBasketImageView = (ImageView) findViewById(R.id.glass_imageView);
        metalBasketImageView = (ImageView) findViewById(R.id.metal_imageView);
        paperBasketImageView = (ImageView) findViewById(R.id.paper_imageView);
        wasteImageView = (ImageView) findViewById(R.id.waste_imageView);
        positionTextView = (TextView) findViewById(R.id.position_textView);

        plasticBasketImageView.setTag(PLASTIC_BASKET_TAG);
        glassBasketImageView.setTag(GLASS_BASKET_TAG);
        paperBasketImageView.setTag(PAPER_BASKET_TAG);
        metalBasketImageView.setTag(METAL_BASKET_TAG);
        wasteImageView.setTag(WASTE_BASKET_TAG);

        wasteImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new
                        ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(wasteImageView);

                v.startDrag(dragData, myShadow, null, 0);
                return false;
            }
        });

        wasteImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int x_crod;
                int y_cord;

                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        x_crod = (int) event.getX();
                        y_cord = (int) event.getY();
                        setPosition(x_crod,y_cord);
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        x_crod = (int) event.getX();
                        y_cord = (int) event.getY();
                        setPosition(x_crod,y_cord);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        x_crod = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_crod;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        setPosition(x_crod,y_cord);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        x_crod = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_crod;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        setPosition(x_crod,y_cord);
                        break;
                    case DragEvent.ACTION_DROP:
                        x_crod = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_crod;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        setPosition(x_crod,y_cord);
                        break;
                    default: break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setPosition(int x, int y){
        positionTextView.setText(getString(R.string.position_textview, x,y));
    }
}




















