package org.cryptomator.ui.mainwindow;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.cryptomator.common.vaults.Vault;
import org.cryptomator.ui.common.FxController;
import org.cryptomator.ui.common.VaultService;
import org.cryptomator.ui.fxapp.FxApplicationWindows;
import org.cryptomator.ui.stats.VaultStatisticsComponent;

import javax.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.stage.Stage;

@MainWindowScoped
public class VaultDetailUnlockedController implements FxController {

	private final ReadOnlyObjectProperty<Vault> vault;
	private final FxApplicationWindows appWindows;
	private final VaultService vaultService;
	private final Stage mainWindow;
	private final LoadingCache<Vault, VaultStatisticsComponent> vaultStats;
	private final VaultStatisticsComponent.Builder vaultStatsBuilder;

	@Inject
	public VaultDetailUnlockedController(ObjectProperty<Vault> vault, FxApplicationWindows appWindows, VaultService vaultService, VaultStatisticsComponent.Builder vaultStatsBuilder, @MainWindow Stage mainWindow) {
		this.vault = vault;
		this.appWindows = appWindows;
		this.vaultService = vaultService;
		this.mainWindow = mainWindow;
		this.vaultStats = CacheBuilder.newBuilder().weakValues().build(CacheLoader.from(this::buildVaultStats));
		this.vaultStatsBuilder = vaultStatsBuilder;
	}

	private VaultStatisticsComponent buildVaultStats(Vault vault) {
		return vaultStatsBuilder.vault(vault).build();
	}

	@FXML
	public void revealAccessLocation() {
		vaultService.reveal(vault.get());
	}

	@FXML
	public void lock() {
		appWindows.startLockWorkflow(vault.get(), mainWindow);
	}

	@FXML
	public void showVaultStatistics() {
		vaultStats.getUnchecked(vault.get()).showVaultStatisticsWindow();
	}

	/* Getter/Setter */

	public ReadOnlyObjectProperty<Vault> vaultProperty() {
		return vault;
	}

	public Vault getVault() {
		return vault.get();
	}

}
