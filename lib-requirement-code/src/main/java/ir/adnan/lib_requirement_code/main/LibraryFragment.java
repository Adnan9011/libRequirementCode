package ir.adnan.lib_requirement_code.main;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Adnan on 7/15/2017.
 */

public class LibraryFragment extends Fragment {

    protected void hideKeyboardFromFragment() {
        InputMethodManager inputManager =
                (InputMethodManager) getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected View getViewFromInflater (LayoutInflater inflater ,int layout , ViewGroup container) {
        return inflater.inflate(layout, container, false);
    }

    protected View getViewFromInflater (LayoutInflater inflater ,int layout , ViewGroup container , boolean attachToRoot) {
        return inflater.inflate(layout, container, attachToRoot);
    }

}
