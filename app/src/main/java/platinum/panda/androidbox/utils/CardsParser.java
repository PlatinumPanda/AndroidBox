package platinum.panda.androidbox.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import platinum.panda.androidbox.models.Card;

/**
 * Created by sihrc on 8/28/14.
 */
public class CardsParser {
	public static List<Card> parse(JSONObject object) {
		try {
			List<Card> cards = new ArrayList<Card>(Integer.parseInt(object.getString("numResults")));
			JSONArray cardArray = object.getJSONArray("results");
			JSONObject jCard;
			for (int i = 0; i < cardArray.length(); i++) {
				jCard = cardArray.getJSONObject(i);

				Card card = new Card();

				card.id = getString(jCard, "itemId", "");
				card.title = getString(jCard, "title", "untitled");
				card.imageUrl = getString(jCard, "galleryPlusPictureURL", getString(jCard, "galleryURL", ""));
				card.description = getString(jCard, "subtitle", "");
//				card.condition = jCard.getString("");
				card.condition = "New"; //FIXME - CONDITION
				card.price = "$9.99"; //FIXME - PRICE ON SERVER
//				card.price = jCard.getString("");

				cards.add(card);
			}

			return cards;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList<Card>(0);
	}

	private static String getString(JSONObject json, String field, String def) throws JSONException {
		if (json.has(field)) {
			return json.getString(field);
		} else {
			return def;
		}
	}
}
