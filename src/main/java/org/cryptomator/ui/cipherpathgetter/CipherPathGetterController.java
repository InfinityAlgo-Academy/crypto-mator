package org.cryptomator.ui.cipherpathgetter;

import org.cryptomator.common.vaults.Vault;
import org.cryptomator.ui.common.FxController;

import javax.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Path;

public class CipherPathGetterController implements FxController {

	private final Stage window;
	private final Vault vault;

	@FXML
	private TextField cleartextPath;
	@FXML
	private TextField ciphertextPath;


	@Inject
	public CipherPathGetterController(@CipherPathGetterWindow Stage window, @CipherPathGetterWindow Vault vault) {
		this.window = window;
		this.vault = vault;
	}

	@FXML
	public void init() {

	}

	@FXML
	public void decrypt() {
		try {
			Path dataCipherPath = vault.getCiphertextPath(cleartextPath.getText());
			ciphertextPath.setText(dataCipherPath.toString());
		} catch (IOException e) {
			ciphertextPath.setText("ERROR");
		}
	}

}
