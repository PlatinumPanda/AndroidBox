package platinum.panda.androidbox.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.VolleyError;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;

import org.json.JSONException;
import org.json.JSONObject;

import platinum.panda.androidbox.R;
import platinum.panda.androidbox.adapter.BoxCardAdapter;
import platinum.panda.androidbox.callback.JSONCallback;
import platinum.panda.androidbox.models.Card;
import platinum.panda.androidbox.models.enums.Tag;
import platinum.panda.androidbox.network.API;
import platinum.panda.androidbox.utils.BoxToast;
import platinum.panda.androidbox.utils.CardsParser;
import platinum.panda.androidbox.views.activities.MainActivity;

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
	private BoxCardAdapter cardAdapter;
	private CardContainer cardContainer;

	//Empty Public Constructor
	public CardFeedFragment() {
	}

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

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
		this.activity.onSectionAttached(Tag.CardFeedFragment);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.card_feed_fragment, container, false);

		cardAdapter = new BoxCardAdapter(activity);

		Card test = new Card();
		test.description = "Description";
		test.title = "Title";
		test.price = "4.99";
		test.condition = "shit";
		test.setCardImageDrawable(getResources().getDrawable(R.drawable.panda));
		cardAdapter.add(test);

		cardContainer = (CardContainer) view.findViewById(R.id.main_card_feed);
		cardContainer.setOrientation(Orientations.Orientation.Ordered);
		cardContainer.setAdapter(cardAdapter);

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.feed, menu);
		SearchView view = (SearchView) menu.findItem(R.id.search).getActionView();
		setupSearchView(view);
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void setupSearchView(SearchView view) {
		view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {
				API.getQueryResults(s, new JSONCallback() {
					@Override
					public void done(JSONObject response) throws JSONException {
						cardAdapter.addCards(CardsParser.parse(response));
						cardContainer.setAdapter(cardAdapter);
					}

					@Override
					public void error(VolleyError error) throws JSONException {
						BoxToast.showShort(activity, R.string.query_error);
					}
				});
				return true;
			}

			@Override
			public boolean onQueryTextChange(String s) {
				return false;
			}
		});
	}
}
