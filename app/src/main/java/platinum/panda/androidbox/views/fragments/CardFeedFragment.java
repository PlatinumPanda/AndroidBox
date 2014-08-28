package platinum.panda.androidbox.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.CardStackAdapter;
import com.andtinder.view.SimpleCardStackAdapter;

import platinum.panda.androidbox.models.Card;
import platinum.panda.androidbox.models.enums.Tag;
import platinum.panda.androidbox.views.activities.MainActivity;
import platinum.panda.androidbox.R;
import platinum.panda.androidbox.adapter.BoxCardAdapter;

/**
 * Created by sihrc on 8/28/14.
 */
public class CardFeedFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String TAG = CardFeedFragment.class.getSimpleName();
	private MainActivity activity;
	//private BoxCardAdapter cardAdapter;
	private CardStackAdapter cardAdapter;

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static CardFeedFragment newInstance() {
		CardFeedFragment fragment = new CardFeedFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	//Empty Public Constructor
	public CardFeedFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.card_feed_fragment, container, false);

//		cardAdapter = new BoxCardAdapter(activity);
		cardAdapter = new SimpleCardStackAdapter(activity);

		//FIXME Hard coded
		Card test = new Card("Test", "Testing Description", "https://avatars0.githubusercontent.com/u/8574675?v=2&s=200");
		test.setCardImageDrawable(getResources().getDrawable(R.drawable.panda));
		cardAdapter.add(test);

		CardContainer cardContainer = (CardContainer) view.findViewById(R.id.main_card_feed);
		cardContainer.setOrientation(Orientations.Orientation.Ordered);
		cardContainer.setAdapter(cardAdapter);

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
		this.activity.onSectionAttached(Tag.CardFeedFragment);
	}
}
