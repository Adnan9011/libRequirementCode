package ir.adnan.lib_requirement_code.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.baselibrary.R;
import ir.adnan.lib_requirement_code.core.Static;

/**
 * Created by adnan on 12/5/16.
 */
public class SnackBar {

    public static Snackbar snackbar;

    public static void showWithAction(Context context, View view, String text) {
        // Create the Snackbar
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = LayoutInflater.from(context).inflate(R.layout.custom_snackbar, null);
        // Configure the view
        Button button = (Button) snackView.findViewById(R.id.snackbar_action);
        button.setVisibility(View.VISIBLE);
        button.setText("باشه");
        button.setTypeface(Static.getMainTypeface(context));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
        textViewTop.setText(text);
        textViewTop.setTypeface(Static.getMainTypeface(context));
        textViewTop.setTextColor(Color.WHITE);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Show the Snackbar
        snackbar.show();
    }

    public static void showTimeLong(Context context, View view, String text) {
        // Create the Snackbar
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = LayoutInflater.from(context).inflate(R.layout.custom_snackbar, null);
        // Configure the view
        Button button = (Button) snackView.findViewById(R.id.snackbar_action);
        button.setVisibility(View.GONE);
//        button.setText("حله");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//            }
//        });

        TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
        textViewTop.setText(text);
        textViewTop.setTypeface(Static.getMainTypeface(context));
        textViewTop.setTextColor(Color.WHITE);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // Show the Snackbar
        snackbar.show();
    }

    public static void showTimeLongExit(Context context, View view, String text) {
        // Create the Snackbar
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = LayoutInflater.from(context).inflate(R.layout.custom_snackbar, null);
        // Configure the view
        Button button = (Button) snackView.findViewById(R.id.snackbar_action);
        button.setVisibility(View.GONE);
//        button.setText("حله");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//            }
//        });

        TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
        textViewTop.setText(text);
        textViewTop.setTypeface(Static.getMainTypeface(context));
        textViewTop.setTextColor(Color.WHITE);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // Show the Snackbar
        snackbar.show();
    }

    public static void dismissSnack() {
        if (snackbar != null && snackbar.isShownOrQueued())
            snackbar.dismiss();
    }
}
