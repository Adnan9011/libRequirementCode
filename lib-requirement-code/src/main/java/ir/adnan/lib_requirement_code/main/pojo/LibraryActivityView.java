package ir.adnan.lib_requirement_code.main.pojo;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

import ir.adnan.lib_requirement_code.view.LibraryTextView;

/**
 * Created by Adnan on 6/28/2017.
 */

public class LibraryActivityView {

    //Activity ? Class
    private Activity activity;

    //View Toolbar
    private Toolbar toolbar;
    private LibraryTextView toolbarTitle;
    private LinearLayout imageNavigation;
    private DrawerLayout drawer;
    //Layout
    private RelativeLayout retryNetwork;
    private View coordinateLayout;

    private int idFragment;

    //
    public LibraryActivityView (Activity activity) {
        this.activity = activity;
    }
    //
    public void setToolbar (int idToolbar) {
        toolbar = (Toolbar) activity.findViewById(idToolbar);
    }
    public Toolbar getToolbar () {
        return toolbar;
    }
    //
    public void setToolbarTitle (int idToolbarTitle) {
        toolbarTitle = (LibraryTextView) activity.findViewById(idToolbarTitle);
    }
    public LibraryTextView getToolbarTitle () {
        return toolbarTitle;
    }
    //
    public void setImageNavigation (int idImageNavigation) {
        this.imageNavigation = (LinearLayout) activity.findViewById(idImageNavigation);
    }
    public LinearLayout getImageNavigation () {
        return this.imageNavigation;
    }
    //
    public void setDrawer (int idDrawer) {
        this.drawer = (DrawerLayout) activity.findViewById(idDrawer);
    }
    public DrawerLayout getDrawer () {
        return this.drawer;
    }
    //
    public void setFragment (int idFragment) {
        this.idFragment = idFragment ;
    }
    public int getFragment () {
        return idFragment;
    }
    //
    public void setCoordinateLayout (int idContainer) {
        coordinateLayout = activity.findViewById(idContainer);
    }
    public View getCoordinateLayout() {
        return coordinateLayout;
    }
    //
    public void setRetryNetwork (int idRetryNetwork) {
        this.retryNetwork = (RelativeLayout) activity.findViewById(idRetryNetwork);
    }
    public RelativeLayout getRetryNetwork () {
        return this.retryNetwork;
    }
    //
}
