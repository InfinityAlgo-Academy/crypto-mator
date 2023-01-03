package org.cryptomator.common.mount;

import org.cryptomator.integrations.mount.MountService;

import java.util.Optional;

public class MountUtil {

	public static Optional<MountService> getDesiredMountService(String qualifiedServiceName) {
		return MountService.get().filter(serviceImpl -> serviceImpl.getClass().getName().equals(qualifiedServiceName)) //
				.findFirst();
	}

}
