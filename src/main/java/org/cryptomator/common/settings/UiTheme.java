package org.cryptomator.common.settings;

import org.apache.commons.lang3.SystemUtils;

public enum UiTheme {
	LIGHT("preferences.interface.theme.light"), //
	DARK("preferences.interface.theme.dark"), //
	AUTOMATIC("preferences.interface.theme.automatic");

	public static UiTheme[] applicableValues() {
		if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_WINDOWS) {
			return values();
		} else {
			return new UiTheme[]{LIGHT, DARK};
		}
	}

	private final String displayName;

	UiTheme(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
