package platinum.panda.androidbox.models.enums;

import platinum.panda.androidbox.views.fragments.CardFeedFragment;

/**
 * Created by sihrc on 8/28/14.
 */
public enum Tag {
	CardFeedFragment(CardFeedFragment.class.toString()),
	MyBoxFragment(CardFeedFragment.class.toString()),
	SettingsFragment(CardFeedFragment.class.toString());

	private String name;

	private Tag(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
