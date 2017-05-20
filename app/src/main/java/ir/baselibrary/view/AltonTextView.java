package ir.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import ir.baselibrary.core.Static;


/**
 * Created by adnan on 12/28/16.
 */
@SuppressLint("AppCompatCustomView")
public class AltonTextView extends TextView {
    public AltonTextView(Context context) {
        super(context);

        setTypeface(Static.getMainTypeface(context));
    }

    public AltonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(Static.getMainTypeface(context));
    }

    public AltonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setTypeface(Static.getMainTypeface(context));
    }
}
