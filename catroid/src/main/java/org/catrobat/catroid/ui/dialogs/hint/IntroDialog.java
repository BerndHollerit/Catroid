package org.catrobat.catroid.ui.dialogs.hint;

import android.app.Fragment;
import android.view.Window;
import android.widget.TextView;

interface IntroDialog {
	TextView getTitleTextView();
	TextView getContentTextView();
	Fragment getFragment();
	Window getWindow();
}
