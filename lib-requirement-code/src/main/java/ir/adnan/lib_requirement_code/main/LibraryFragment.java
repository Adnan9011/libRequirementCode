package ir.adnan.lib_requirement_code.main;

import android.app.Fragment;
import android.content.Context;
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
}
