package br.com.alura.challenge.back.feature;

import br.com.alura.challenge.back.domain.Profile;

public class ProfileScenarioFactory {
    
    public static final Profile PROFILE = loadProfile();

	private static Profile loadProfile() {
		return new Profile(3L, "TESTE");
	}
}
