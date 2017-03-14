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
 * limitations under the License
 */

package com.android.voicemailomtp;

import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import android.telephony.VisualVoicemailService;
import android.telephony.VisualVoicemailSms;

import com.android.voicemailomtp.sync.OmtpVvmSourceManager;

public class OmtpService extends VisualVoicemailService {

    private static String TAG = "VvmOmtpService";

    public static final String ACTION_SMS_RECEIVED = "com.android.vociemailomtp.sms.sms_received";

    public static final String EXTRA_VOICEMAIL_SMS = "extra_voicemail_sms";

    @Override
    public void onCellServiceConnected(VisualVoicemailTask task,
            final PhoneAccountHandle phoneAccountHandle) {
        VvmLog.i(TAG, "onCellServiceConnected");
        ActivationTask
                .start(OmtpService.this, phoneAccountHandle, null);
        task.finish();
    }

    @Override
    public void onSmsReceived(VisualVoicemailTask task, final VisualVoicemailSms sms) {
        VvmLog.i(TAG, "onSmsReceived");
        Intent intent = new Intent(ACTION_SMS_RECEIVED);
        intent.setPackage(getPackageName());
        intent.putExtra(EXTRA_VOICEMAIL_SMS, sms);
        sendBroadcast(intent);
        task.finish();
    }

    @Override
    public void onSimRemoved(final VisualVoicemailTask task,
            final PhoneAccountHandle phoneAccountHandle) {
        VvmLog.i(TAG, "onSimRemoved");
        OmtpVvmSourceManager.getInstance(OmtpService.this).removeSource(phoneAccountHandle);
        task.finish();
    }

    @Override
    public void onStopped(VisualVoicemailTask task) {
        VvmLog.i(TAG, "onStopped");
    }
}
