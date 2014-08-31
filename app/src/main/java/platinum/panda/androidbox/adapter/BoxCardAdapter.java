package platinum.panda.androidbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.andtinder.model.CardModel;
import com.andtinder.view.CardStackAdapter;

import java.util.List;

import platinum.panda.androidbox.PandaBox;
import platinum.panda.androidbox.R;
import platinum.panda.androidbox.models.Card;
import platinum.panda.androidbox.utils.NullCheck;
import platinum.panda.androidbox.views.activities.MainActivity;

/**
 * Created by sihrc on 8/28/14.
 */
public class BoxCardAdapter extends CardStackAdapter<Card> {
	// Context
	Context context;

	public BoxCardAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected View getCardView(int position, Card model, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.card, parent, false);

			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.card_title);
			holder.description = (TextView) convertView.findViewById(R.id.card_description);
			holder.price = (TextView) convertView.findViewById(R.id.card_price_condition);
			holder.imageView = (NetworkImageView) convertView.findViewById(R.id.card_image);
			holder.logo = (ImageView) convertView.findViewById(R.id.card_logo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(model.title);
		holder.description.setText(NullCheck.stringChecker(model.description, context.getString(R.string.no_description)));
		holder.logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ebay_logo));
		holder.imageView.setImageUrl(model.imageUrl, PandaBox.app.getImageLoader());
		holder.price.setText(String.format(context.getString(R.string.card_price_condition), model.price, model.condition));

		return convertView;
	}

	/**
	 * Add Cards to the card feed
	 *
	 * @param cards - list of cards to add
	 */
	public void addCards(List<Card> cards) {
		for (Card card : cards) {
			add(card);
		}
	}

	private static class ViewHolder {
		TextView title, description, price;
		NetworkImageView imageView;
		ImageView logo;
	}
}
