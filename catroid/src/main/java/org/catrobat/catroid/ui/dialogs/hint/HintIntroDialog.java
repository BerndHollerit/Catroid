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

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.catrobat.catroid.R;
import org.catrobat.catroid.utils.SnackbarUtil;

import java.util.LinkedList;
import java.util.Queue;

public class HintIntroDialog extends AbstractIntroDialog {
	private IntroSlide slide;

	private HintIntroDialog(Fragment fragment, int style, int messageId) {
		super(fragment, style);
		slide = new IntroSlide(R.string.hint, messageId, NONE, this);
		updateIntroSlides();
	}

	public static void showHint(Fragment fragment, int style, int messageId) {
		if (SnackbarUtil.shouldHintBeShown(fragment.getActivity(), messageId)) {
			(new HintIntroDialog(fragment, style, messageId)).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.intro_dialog_dismiss_all_button)).setText(R.string.skip);
		((TextView) findViewById(R.id.intro_dialog_got_it_button)).setText(R.string.got_it);
	}

	@Override
	void updateIntroSlides() {
		Queue<IntroSlide> introSlides = new LinkedList<>();
		introSlides.add(slide);
		setIntroSlides(introSlides);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.intro_dialog_dismiss_all_button:
				onBackPressed();
				break;
			case R.id.intro_dialog_got_it_button:
				nextSlide();
				break;
			default:
				break;
		}
	}
}