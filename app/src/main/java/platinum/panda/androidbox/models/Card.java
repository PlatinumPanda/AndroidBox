package platinum.panda.androidbox.models;

import com.andtinder.model.CardModel;

/**
 * Created by sihrc on 8/28/14.
 */
public class Card extends CardModel {
	public Card(String title, String description, String imageUrl) {
		super();
		setTitle(title);
		setDescription(description);

		//setOnClickListener(getCardOnClickListener());
		//setCardImageDrawable();
		//setCardDislikeImageDrawable();
		//setCardLikeImageDrawable();
		//setOnCardDimissedListener();
	}

	private OnClickListener getCardOnClickListener() {
		return new OnClickListener() {
			@Override
			public void OnClickListener() {

			}
		};
	}
}
