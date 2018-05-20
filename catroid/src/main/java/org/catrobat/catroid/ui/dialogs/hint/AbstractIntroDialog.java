package org.catrobat.catroid.ui.dialogs.hint;

/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.catrobat.catroid.R;
import org.catrobat.catroid.utils.SnackbarUtil;

import java.util.Queue;

public abstract class AbstractIntroDialog extends Dialog implements View.OnClickListener, IntroDialog {
	static final int NONE = -1;

	private Queue<IntroSlide> introSlides;
	private TextView introTitle;
	private TextView introSummary;
	private Fragment fragment;

	public AbstractIntroDialog(Fragment fragment, int style) {
		super(fragment.getActivity(), style);
		this.fragment = fragment;
	}

	abstract void updateIntroSlides();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setCanceledOnTouchOutside(false);

		setContentView(R.layout.dialog_formula_editor_intro);

		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

		Drawable background = new ColorDrawable(Color.BLACK);
		background.setAlpha(200);
		getWindow().setBackgroundDrawable(background);

		getWindow().setDimAmount(0f);

		introTitle = findViewById(R.id.intro_dialog_title);
		introSummary = findViewById(R.id.intro_dialog_summary);

		(findViewById(R.id.intro_dialog_dismiss_all_button)).setOnClickListener(this);
		(findViewById(R.id.intro_dialog_got_it_button)).setOnClickListener(this);

		nextSlide();
	}

	@Override
	public void onBackPressed() {
		updateIntroSlides();
		SnackbarUtil.setHintShown(fragment.getActivity(),
				introSlides.peek().summaryStringId);
		super.onBackPressed();
	}

	void nextSlide() {
		if (introSlides.isEmpty()) {
			onBackPressed();
		} else {
			introSlides.remove().applySlide();
		}
	}

	void setIntroSlides(Queue<IntroSlide> introSlides) {
		this.introSlides = introSlides;
	}

	@Override
	public TextView getTitleTextView() {
		return introTitle;
	}

	@Override
	public TextView getContentTextView() {
		return introSummary;
	}

	@Override
	public Window getWindow() {
		return super.getWindow();
	}

	@Override
	public Fragment getFragment() {
		return fragment;
	}
}
