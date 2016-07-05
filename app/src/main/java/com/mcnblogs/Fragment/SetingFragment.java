package com.mcnblogs.Fragment;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.R;
import com.mcnblogs.core.Config;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.Header;

public class SetingFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_seting, container, false);

		return view;
	}
}
