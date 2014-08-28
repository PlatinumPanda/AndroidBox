package platinum.panda.androidbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;

import platinum.panda.androidbox.models.Card;

/**
 * Created by sihrc on 8/28/14.
 */
public class BoxCardAdapter extends CardStackAdapter{
	public BoxCardAdapter(Context context) {
		super(context);
	}

	@Override
	protected View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
		return null;
	}

	public void createCard(String title, String description, String imageUrl) {
		Card card = new Card(title, description, imageUrl);
		add(card);
	}
}
