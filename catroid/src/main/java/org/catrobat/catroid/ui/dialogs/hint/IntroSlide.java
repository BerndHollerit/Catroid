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

import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import org.catrobat.catroid.R;

public class IntroSlide {
	int titleStringId;
	int summaryStringId;
	int targetViewId;
	IntroDialog introDialog;

	public IntroSlide(int titleStringId, int summaryStringId, int targetViewId, IntroDialog introDialog) {
		this.titleStringId = titleStringId;
		this.summaryStringId = summaryStringId;
		this.targetViewId = targetViewId;
		this.introDialog = introDialog;
	}

	void applySlide() {
		setText();
		if (targetViewId != AbstractIntroDialog.NONE) {
			setAnimation();
			setPosition();
		} else {
			introDialog.getWindow().setGravity(Gravity.CENTER);
		}
	}

	private void setText() {
		introDialog.getTitleTextView().setText(titleStringId);
		introDialog.getContentTextView().setText(summaryStringId);
	}

	private void setAnimation() {
		View targetView = introDialog.getFragment().getView().findViewById(targetViewId);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setRepeatCount(2);
		alphaAnimation.setFillAfter(true);
		targetView.startAnimation(alphaAnimation);
	}

	private int getViewYCoordinates(int resourceId) {
		Rect rectangle = new Rect();
		Window window = introDialog.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
		int statusBarHeight = rectangle.top;
		int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		int titleBarHeight = contentViewTop - statusBarHeight;

		int[] location = new int[2];
		View view = introDialog.getFragment().getView().findViewById(resourceId);
		view.getLocationOnScreen(location);
		return location[1] + titleBarHeight;
	}

	private void setPosition() {
		int keyboardLocation = getViewYCoordinates(R.id.formula_editor_keyboard_compute);
		int targetViewLocation = getViewYCoordinates(targetViewId);
		int toolbarLocation = getViewYCoordinates(R.id.formula_editor_brick_space);

		introDialog.getWindow().setGravity(Gravity.TOP);
		WindowManager.LayoutParams dialogLayoutParams = introDialog.getWindow().getAttributes();
		if (targetViewLocation >= keyboardLocation) {
			dialogLayoutParams.y = toolbarLocation;
		} else {
			dialogLayoutParams.y = keyboardLocation;
		}
		introDialog.getWindow().setAttributes(dialogLayoutParams);
	}
}
