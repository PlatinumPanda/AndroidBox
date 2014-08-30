package platinum.panda.androidbox.models;

import com.andtinder.model.CardModel;

/**
 * Created by sihrc on 8/28/14.
 */
public class Card extends CardModel {
	public String id, title, description, price, condition, imageUrl = "";

	public Card(){}
		//setOnClickListener(getCardOnClickListener());
		//setCardImageDrawable();
		//setCardDislikeImageDrawable();
		//setCardLikeImageDrawable();
		//setOnCardDimissedListener();

	private OnClickListener getCardOnClickListener() {
		return new OnClickListener() {
			@Override
			public void OnClickListener() {

			}
		};
	}

	@Override
	public String toString() {
		return title + ' ' + description + ' ' + price + ' ' + imageUrl;
	}
}
