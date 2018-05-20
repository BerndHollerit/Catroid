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

package org.catrobat.catroid.ui.dialogs.hint;

import android.view.View;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.fragment.FormulaEditorFragment;

import java.util.LinkedList;
import java.util.Queue;

public class FormulaEditorIntroDialog extends AbstractIntroDialog {

	private FormulaEditorFragment formulaEditorFragment;

	public FormulaEditorIntroDialog(FormulaEditorFragment formulaEditorFragment, int style) {
		super(formulaEditorFragment, style);
		this.formulaEditorFragment = formulaEditorFragment;
		updateIntroSlides();
	}

	@Override
	void updateIntroSlides() {
		setIntroSlides(getIntroDialogSlides());
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

	private Queue<IntroSlide> getIntroDialogSlides() {
		Queue<IntroSlide> introSlides = new LinkedList<>();
		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_formula_editor,
				R.string.formula_editor_intro_summary_formula_editor,
				NONE,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_input_field,
				R.string.formula_editor_intro_summary_input_field,
				R.id.formula_editor_edit_field,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_keyboard,
				R.string.formula_editor_intro_summary_keyboard,
				R.id.formula_editor_keyboard_7,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_compute,
				R.string.formula_editor_intro_summary_compute,
				R.id.formula_editor_keyboard_compute,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_object,
				R.string.formula_editor_intro_summary_object,
				R.id.formula_editor_keyboard_object,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_functions,
				R.string.formula_editor_intro_summary_functions,
				R.id.formula_editor_keyboard_function,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_logic,
				R.string.formula_editor_intro_summary_logic,
				R.id.formula_editor_keyboard_logic,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_device,
				R.string.formula_editor_intro_summary_device,
				R.id.formula_editor_keyboard_sensors,
				this));

		introSlides.add(new IntroSlide(
				R.string.formula_editor_intro_title_data,
				R.string.formula_editor_intro_summary_data,
				R.id.formula_editor_keyboard_data,
				this));
		return introSlides;
	}
}
