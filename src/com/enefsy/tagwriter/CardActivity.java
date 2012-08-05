package com.enefsy.tagwriter;

import com.enefsy.tagwriter.R;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

public class CardActivity extends Activity {
	private ImageView mCardView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_activity);
		
		// ImageView that we'll use to display cards
        mCardView = (ImageView)findViewById(R.id.card_view);
        
        // see if app was started from a tag and show game console
        Intent intent = getIntent();
        if(intent.getType() != null && intent.getType().equals(MimeType.NFC_UID)) {
        	Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            NdefRecord cardRecord = msg.getRecords()[0];
            String consoleName = new String(cardRecord.getPayload());
            displayCard(consoleName);
        }
	}
	
	private void displayCard(String consoleName) {
		int cardResId = 0;
		if(consoleName.equals("nes")) cardResId = R.drawable.nes;
		else if(consoleName.equals("snes")) cardResId = R.drawable.snes;
		else if(consoleName.equals("megadrive")) cardResId = R.drawable.megadrive;
		else if(consoleName.equals("mastersystem")) cardResId = R.drawable.mastersystem;
		mCardView.setImageResource(cardResId);
	}
}
