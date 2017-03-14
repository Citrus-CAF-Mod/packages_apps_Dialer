/*
 * Copyright (C) 2017 The Android Open Source Project
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
 * limitations under the License
 */

// Generated by the protocol buffer compiler.  DO NOT EDIT!

package com.android.dialer.callintent.nano;

@SuppressWarnings("hiding")
public final class CallInitiationType extends
    com.google.protobuf.nano.ExtendableMessageNano<CallInitiationType> {

  // enum Type
  public interface Type {
    public static final int UNKNOWN_INITIATION = 0;
    public static final int INCOMING_INITIATION = 1;
    public static final int DIALPAD = 2;
    public static final int SPEED_DIAL = 3;
    public static final int REMOTE_DIRECTORY = 4;
    public static final int SMART_DIAL = 5;
    public static final int REGULAR_SEARCH = 6;
    public static final int CALL_LOG = 7;
    public static final int CALL_LOG_FILTER = 8;
    public static final int VOICEMAIL_LOG = 9;
    public static final int CALL_DETAILS = 10;
    public static final int QUICK_CONTACTS = 11;
    public static final int EXTERNAL_INITIATION = 12;
    public static final int LAUNCHER_SHORTCUT = 13;
    public static final int CALL_COMPOSER = 14;
    public static final int MISSED_CALL_NOTIFICATION = 15;
    public static final int CALL_SUBJECT_DIALOG = 16;
  }

  private static volatile CallInitiationType[] _emptyArray;
  public static CallInitiationType[] emptyArray() {
    // Lazily initializes the empty array
    if (_emptyArray == null) {
      synchronized (
          com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
        if (_emptyArray == null) {
          _emptyArray = new CallInitiationType[0];
        }
      }
    }
    return _emptyArray;
  }

  // @@protoc_insertion_point(class_scope:com.android.dialer.callintent.CallInitiationType)

  public CallInitiationType() {
    clear();
  }

  public CallInitiationType clear() {
    unknownFieldData = null;
    cachedSize = -1;
    return this;
  }

  @Override
  public CallInitiationType mergeFrom(
          com.google.protobuf.nano.CodedInputByteBufferNano input)
      throws java.io.IOException {
    while (true) {
      int tag = input.readTag();
      switch (tag) {
        case 0:
          return this;
        default: {
          if (!super.storeUnknownField(input, tag)) {
            return this;
          }
          break;
        }
      }
    }
  }

  public static CallInitiationType parseFrom(byte[] data)
      throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
    return com.google.protobuf.nano.MessageNano.mergeFrom(new CallInitiationType(), data);
  }

  public static CallInitiationType parseFrom(
          com.google.protobuf.nano.CodedInputByteBufferNano input)
      throws java.io.IOException {
    return new CallInitiationType().mergeFrom(input);
  }
}
