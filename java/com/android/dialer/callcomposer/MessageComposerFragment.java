/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.dialer.callcomposer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/** Fragment used to compose call with message fragment. */
public class MessageComposerFragment extends CallComposerFragment
    implements OnClickListener, TextWatcher, OnTouchListener, OnLongClickListener {
  private static final String CHAR_LIMIT_KEY = "char_limit";

  public static final int NO_CHAR_LIMIT = -1;

  private EditText customMessage;
  private boolean isLongClick = false;
  private int charLimit;

  public static MessageComposerFragment newInstance(int charLimit) {
    MessageComposerFragment fragment = new MessageComposerFragment();
    Bundle args = new Bundle();
    args.putInt(CHAR_LIMIT_KEY, charLimit);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  public String getMessage() {
    return customMessage == null ? null : customMessage.getText().toString();
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    charLimit = getArguments().getInt(CHAR_LIMIT_KEY, NO_CHAR_LIMIT);

    View view = inflater.inflate(R.layout.fragment_message_composer, container, false);
    TextView urgent = (TextView) view.findViewById(R.id.message_urgent);
    customMessage = (EditText) view.findViewById(R.id.custom_message);

    urgent.setOnClickListener(this);
    customMessage.setOnTouchListener(this);
    customMessage.setOnLongClickListener(this);
    customMessage.addTextChangedListener(this);
    if (charLimit != NO_CHAR_LIMIT) {
      TextView remainingChar = (TextView) view.findViewById(R.id.remaining_characters);
      remainingChar.setText("" + charLimit);
      customMessage.setFilters(new InputFilter[] {new InputFilter.LengthFilter(charLimit)});
      customMessage.addTextChangedListener(
          new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
              remainingChar.setText("" + (charLimit - editable.length()));
            }
          });
    }
    view.findViewById(R.id.message_chat).setOnClickListener(this);
    view.findViewById(R.id.message_question).setOnClickListener(this);

    setTopView(urgent);
    return view;
  }

  @Override
  public void onClick(View view) {
    customMessage.setText(((TextView) view).getText());
    customMessage.setSelection(customMessage.getText().length());
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {}

  @Override
  public void afterTextChanged(Editable s) {
    getListener().composeCall(this);
  }

  /**
   * EditTexts take two clicks to dispatch an onClick() event, so instead we add an onTouchListener
   * to listen for them. The caveat to this is that it also requires listening for onLongClicks to
   * distinguish whether a MotionEvent came from a click or a long click.
   */
  @Override
  public boolean onTouch(View view, MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (isLongClick) {
        isLongClick = false;
      } else {
        getListener().showFullscreen(true);
      }
    }
    view.performClick();
    return false;
  }

  @Override
  public boolean onLongClick(View v) {
    isLongClick = true;
    return false;
  }

  @Override
  public boolean shouldHide() {
    return TextUtils.isEmpty(getMessage());
  }
}
