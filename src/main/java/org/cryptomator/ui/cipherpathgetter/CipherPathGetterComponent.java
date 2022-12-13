package org.cryptomator.ui.cipherpathgetter;

import dagger.BindsInstance;
import dagger.Lazy;
import dagger.Subcomponent;
import org.cryptomator.common.vaults.Vault;
import org.cryptomator.ui.common.FxmlFile;
import org.cryptomator.ui.common.FxmlScene;

import javax.inject.Named;
import javafx.scene.Scene;
import javafx.stage.Stage;

@CipherPathGetterScoped
@Subcomponent(modules = {CipherPathGetterModule.class})
public interface CipherPathGetterComponent {

	@CipherPathGetterWindow
	Stage window();

	@FxmlScene(FxmlFile.CIPHERPATHGETTER)
	Lazy<Scene> cipherPathGetterScene();

	default void showCipherPathGetterWindow() {
		Stage stage = window();
		stage.setScene(cipherPathGetterScene().get());
		stage.sizeToScene();
		stage.show();
	}

	@Subcomponent.Factory
	interface Factory {
		CipherPathGetterComponent create(@BindsInstance @CipherPathGetterWindow Vault vault, @BindsInstance @Named("CipherPathWindowOwner") Stage owner);
	}
}
