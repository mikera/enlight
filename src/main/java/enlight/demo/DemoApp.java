package enlight.demo;

import mikera.cljutils.Clojure;

public class DemoApp {
	public static void main(String[] args) {
		Clojure.eval("(do " +
				        "(require 'enlight.samples.demo) " +
				        "(enlight.core/show enlight.samples.demo/EXAMPLE-SCENE))");
	}
}
