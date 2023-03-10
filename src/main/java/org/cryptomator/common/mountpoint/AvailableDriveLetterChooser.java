package org.cryptomator.common.mountpoint;

import org.apache.commons.lang3.SystemUtils;
import org.cryptomator.common.vaults.Volume;
import org.cryptomator.common.vaults.WindowsDriveLetters;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.Optional;

class AvailableDriveLetterChooser implements MountPointChooser {

	private final WindowsDriveLetters windowsDriveLetters;

	@Inject
	public AvailableDriveLetterChooser(WindowsDriveLetters windowsDriveLetters) {
		this.windowsDriveLetters = windowsDriveLetters;
	}

	@Override
	public boolean isApplicable(Volume caller) {
		return SystemUtils.IS_OS_WINDOWS;
	}

	@Override
	public Optional<Path> chooseMountPoint(Volume caller) {
		return this.windowsDriveLetters.getDesiredAvailableDriveLetterPath();
	}
}
