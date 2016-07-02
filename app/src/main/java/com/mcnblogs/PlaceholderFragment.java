package com.mcnblogs;

import com.mcnblogs.Fragment.HomePageFragment;
import com.mcnblogs.Fragment.NewsFragment;
import com.mcnblogs.Fragment.ReadFragment;
import com.mcnblogs.Fragment.SetingFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements OnClickListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private final String ARG_SECTION_NUMBER = "section_number";

	private RelativeLayout message;// ��ҳ��ť
	private RelativeLayout contacts;// ���°�ť
	private RelativeLayout news;// ���Ű�ť
	private RelativeLayout setting;// ���ð�ť

	android.app.FragmentManager fragmentManager;

	/**
	 * ��ҳ��Ƭ
	 */
	HomePageFragment myHomePageFra;

	/**
	 * ������Ƭ
	 */
	NewsFragment myNewsFra;

	/**
	 * �Ķ���Ƭ
	 */
	ReadFragment myReadFra;

	/**
	 * ������Ƭ
	 */
	SetingFragment mySetingFra;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		message = (RelativeLayout) view.findViewById(R.id.message_layout);
		contacts = (RelativeLayout) view.findViewById(R.id.contacts_layout);
		news = (RelativeLayout) view.findViewById(R.id.news_layout);
		setting = (RelativeLayout) view.findViewById(R.id.setting_layout);

		message.setOnClickListener(listener);
		contacts.setOnClickListener(listener);
		news.setOnClickListener(listener);
		setting.setOnClickListener(listener);

		fragmentManager = getFragmentManager();
		return view;
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			FragmentTransaction tran = fragmentManager.beginTransaction();
			HideFragement(tran);
			message.setBackground(null);
			contacts.setBackground(null);
			news.setBackground(null);
			setting.setBackground(null);

			int ClickInt = v.getId();
			switch (ClickInt) {
			case R.id.message_layout:
				// ����ѡ��
				message.setBackground(getResources().getDrawable(
						R.drawable.home_btn_bg_d));

				if (myHomePageFra == null) {
					myHomePageFra = new HomePageFragment();
					tran.add(R.id.content, myHomePageFra);
				} else {
					tran.show(myHomePageFra);
				}

				break;
			case R.id.contacts_layout:
				// ����ѡ��
				contacts.setBackground(getResources().getDrawable(
						R.drawable.home_btn_bg_d));

				if (myReadFra == null) {
					myReadFra = new ReadFragment();
					tran.add(R.id.content, myReadFra);
				} else {
					tran.show(myReadFra);
				}

				break;
			case R.id.news_layout:
				// ����ѡ��
				news.setBackground(getResources().getDrawable(
						R.drawable.home_btn_bg_d));

				if (myNewsFra == null) {
					myNewsFra = new NewsFragment();
					tran.add(R.id.content, myNewsFra);
				} else {
					tran.show(myNewsFra);
				}

				break;
			case R.id.setting_layout:
				// ����ѡ��
				setting.setBackground(getResources().getDrawable(
						R.drawable.home_btn_bg_d));

				if (mySetingFra == null) {
					mySetingFra = new SetingFragment();
					tran.add(R.id.content, mySetingFra);
				} else {
					tran.show(mySetingFra);
				}

				break;
			}
			tran.commit();
		}
	};
	
	/**
	 * ������Ƭ
	 * @param tran
	 */
	private void HideFragement(FragmentTransaction tran){
		if(myHomePageFra!=null){
			tran.hide(myHomePageFra);
		}
		if(myReadFra!=null){
			tran.hide(myReadFra);
		}
		if(myNewsFra!=null){
			tran.hide(myNewsFra);
		}
		if(mySetingFra!=null){
			tran.hide(mySetingFra);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������

	}

	// @Override
	// public void onAttach(Activity activity) {
	// super.onAttach(activity);
	// ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	// ARG_SECTION_NUMBER));
	// }
}
