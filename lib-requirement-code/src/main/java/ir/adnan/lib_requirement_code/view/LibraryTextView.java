package ir.adnan.lib_requirement_code.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import ir.adnan.lib_requirement_code.core.Static;


/**
 * Created by adnan on 12/28/16.
 */
@SuppressLint("AppCompatCustomView")
public class LibraryTextView extends TextView {
    public LibraryTextView(Context context) {
        super(context);

        setTypeface(Static.getMainTypeface(context));
    }

    public LibraryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(Static.getMainTypeface(context));
    }

    public LibraryTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setTypeface(Static.getMainTypeface(context));
    }
}
