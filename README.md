# Are you Funky?

There is no such thing as functional programming in Java, but sometimes we emulate it using anonymous classes.

FunkyJFunctional provides a new way to do functional programming in Java. It integrates with [Guava](http://code.google.com/p/guava-libraries/) (the former google collections), and uses some Java syntaxic sugar: method local class declarations and init blocks.

In short, two syntax examples:

* A funky Runnable: ```class Hello {{ System.out.println("Hello Funky World"); }}```

* A funky Predicate: ```class Adult extends Pred<Integer> {{ r = t > 18; }}```

Looking for the documentation? Have a look at the [Funky javadoc](http://pyricau.github.com/FunkyJFunctional/javadoc/snapshot/info/piwai/funkyjfunctional/Funky.html)!

Any question? Please ask them on the dedicated [Google Group](https://groups.google.com/group/funkyjfunctional).

# Detailed examples

With Guava:

	List<Integer> values = Arrays.asList(16, 21);
	Predicate<Integer> adult = new Predicate<Integer>() {
		@Override
		public boolean apply(Integer input) {
			return input > 18;
		}
	};
	Iterable<Integer> adults = Iterables.filter(values, adult);
	
With FunkyJFunctional our [predicate](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/PredTest.java) is much shorter:
	
	List<Integer> values = Arrays.asList(16, 21);
	class Adult extends Pred<Integer> {{ r = t > 18; }}
	Iterable<Integer> adults = Iterables.filter(values, Funky.withPred(Adult.class));
	System.out.println(adults); // prints [21]
	
With static imports, it's even shorter:

    import static info.piwai.funkyjfunctional.Funky.*;
    import static com.google.common.collect.Iterables.*;
	// [...]
	Iterable<Integer> adults = filter(values, withPred(Adult.class));
	
Sounds funky? We think it is ;-). 
	
We also provide  [functions](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/FuncTest.java):

	List<Integer> values = Arrays.asList(42, 69);
	class Price extends Func<Integer, String> {{ r = t + "$"; }}
	List<String> prices = transform(values, withFunc(Price.class));
	System.out.println(prices); // prints [42$, 69$]
	
And... [much more](http://pyricau.github.com/FunkyJFunctional/javadoc/snapshot/info/piwai/funkyjfunctional/Funky.html)!
    
# Maven funky artifacts

## Releases, for the serious funky guys

You will need the following repository in your Maven project:

	<repositories>
		<repository>
			<id>funkyjfunctional-releases</id>
			<url>https://github.com/pyricau/FunkyJFunctional/raw/master/releases</url>
		</repository>
	</repositories>
	
As well as the following dependency:

	<dependency>
		<groupId>info.piwai.funkyjfunctional</groupId>
		<artifactId>funkyjfunctional</artifactId>
		<version>0.2</version>
	</dependency>
	
## Snapshots, for Chuck Norris and his funky friends

You will need the following repository in your Maven project:

	<repositories>
		<repository>
			<id>funkyjfunctional-snapshots</id>
			<url>https://github.com/pyricau/FunkyJFunctional/raw/master/snapshots</url>
		</repository>
	</repositories>

As well as the following dependency:

	<dependency>
		<groupId>info.piwai.funkyjfunctional</groupId>
		<artifactId>funkyjfunctional</artifactId>
		<version>0.3-SNAPSHOT</version>
	</dependency>

# Other funky ways 

* [jcurry](http://code.google.com/p/jcurry/) uses the awesome [projectlombok](http://projectlombok.org/) funkyness power.
* [lambdaj](http://code.google.com/p/lambdaj/) is a funky alernative to [Guava](http://code.google.com/p/guava-libraries/).
