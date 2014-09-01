package platinum.panda.androidbox.models.enums;

import platinum.panda.androidbox.views.fragments.CardFeedFragment;
import platinum.panda.androidbox.views.fragments.LoginFragment;

/**
 * Created by sihrc on 8/28/14.
 */
public enum Tag {
	CardFeedFragment(CardFeedFragment.class.getSimpleName()),
	MyBoxFragment(CardFeedFragment.class.getSimpleName()),
	SettingsFragment(CardFeedFragment.class.getSimpleName()),
	LoginFragment(LoginFragment.class.getSimpleName());

	private String name;

	private Tag(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
