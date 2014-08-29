package platinum.panda.androidbox.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import platinum.panda.androidbox.models.Card;

import static platinum.panda.androidbox.utils.NullCheck.getJSONString;

/**
 * Created by sihrc on 8/28/14.
 */
public class CardsParser {
	public static List<Card> parse(JSONObject object) {
		try {
			List<Card> cards = new ArrayList<Card>(Integer.parseInt(object.getString("numResults")));
			JSONArray cardArray = object.getJSONArray("results");
			JSONObject jCard;

			//Loop through results array and extract cards
			for (int i = 0; i < cardArray.length(); i++) {
				jCard = cardArray.getJSONObject(i);

				Card card = new Card();

				card.id = getJSONString(jCard, "itemId", "");
				card.title = getJSONString(jCard, "title", "untitled");
				card.imageUrl = getJSONString(jCard, "galleryPlusPictureURL", getJSONString(jCard, "galleryURL", ""));
				card.description = getJSONString(jCard, "subtitle", "");
				card.condition = getJSONString(jCard, "conditionBox", "New");
				card.price = getJSONString(jCard, "priceBox", "");

				cards.add(card);
			}

			return cards;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList<Card>(0);
	}

}
