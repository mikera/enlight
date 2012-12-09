package enlight;

import java.util.Arrays;
import java.util.List;

import mikera.cljunit.ClojureTest;

public class ClojureTests extends ClojureTest {
	@Override
	public List<String> namespaces() {
		return Arrays.asList(new String[] {
			"enlight.test-core"
		});
	}
}
