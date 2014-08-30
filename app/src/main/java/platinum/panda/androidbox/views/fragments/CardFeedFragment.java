package platinum.panda.androidbox.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import platinum.panda.androidbox.utils.BoxUtils;
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
	 * Returns a new instance of this fragment
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

		cardContainer = (CardContainer) view.findViewById(R.id.main_card_feed);
		cardContainer.setOrientation(Orientations.Orientation.Ordered);
		cardContainer.setAdapter(cardAdapter);

		cardTestingSetup(); //FIXME remove after testing

		cardContainer.updateTopView();

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.feed, menu);
		MenuItem searchItem = menu.findItem(R.id.search);
		setupSearchView(searchItem);
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void setupSearchView(final MenuItem searchItem) {
		final SearchView view = (SearchView) searchItem.getActionView();
		view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {
				searchItem.collapseActionView();
				BoxUtils.hideKeyboard(activity, view);
				activity.setActionBarTitle('\"' + s + '\"');
				API.getQueryResults(s, new JSONCallback() {
					@Override
					public void done(JSONObject response) throws JSONException {
						cardAdapter.addCards(CardsParser.parse(response));
						cardContainer.updateTopView();
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

	private void cardTestingSetup() {
		/** TESTS FIXME REMOVE **/
		Card test1 = new Card();
		test1.description = "Description";
		test1.title = "Title";
		test1.price = "4.99";
		test1.condition = "shit";

		Card test2 = new Card();
		test2.description = "Description";
		test2.title = "Title";
		test2.price = "5.99";
		test2.condition = "shit";

		Card test3 = new Card();
		test3.description = "Description";
		test3.title = "Title";
		test3.price = "6.99";
		test3.condition = "shit";

		test1.setCardImageDrawable(getResources().getDrawable(R.drawable.panda));
		test2.setCardImageDrawable(getResources().getDrawable(R.drawable.panda));
		test3.setCardImageDrawable(getResources().getDrawable(R.drawable.panda));

		cardAdapter.add(test1);
		cardAdapter.add(test2);
		cardAdapter.add(test3);
	}
}
