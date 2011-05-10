# Are you Funky?

There is no such thing as functional programming in Java, but sometimes we emulate it using anonymous classes.

FunkyJFunctional provides a new way to do functional programming in Java, using some Java syntaxic sugar: method local class declarations and init blocks.

FunkyJFunctional also provides a [Guava](http://code.google.com/p/guava-libraries/) module (Guava is the former google collections).

Short syntax examples:

* A funky Java [Runnable](http://download.oracle.com/javase/6/docs/api/java/lang/Runnable.html): 
``` java
class Hello {{ System.out.println("Hello Funky World"); }}
```

``` java
class Test {}
```

* A funky Swing [ActionListener](http://download.oracle.com/javase/6/docs/api/java/awt/event/ActionListener.html): ```class BtonClick extends ActL{{ buttonClicked(e); }}```

* A funky Guava [Predicate](http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Predicate.html): ```class Adult extends Pred<Integer> {{ r = t > 18; }}```

* A funky Wicket [AbstractReadOnlyModel](http://wicket.apache.org/apidocs/1.4/org/apache/wicket/model/AbstractReadOnlyModel.html): ```class FortyTwo extends ARON<String> {{ r = "42"; }}```

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
	
With FunkyJFunctional our [predicate](https://github.com/pyricau/FunkyJFunctional/blob/master/funkyjfunctional-guava/src/test/java/info/piwai/funkyjfunctional/guava/test/PredTest.java) is much shorter:
	
	List<Integer> values = Arrays.asList(16, 21);
	class Adult extends Pred<Integer> {{ r = t > 18; }}
	Iterable<Integer> adults = Iterables.filter(values, FunkyGuava.withPred(Adult.class));
	System.out.println(adults); // prints [21]
	
With static imports, it's even shorter:

    import static info.piwai.funkyjfunctional.FunkyGuava.*;
    import static com.google.common.collect.Iterables.*;
	// [...]
	Iterable<Integer> adults = filter(values, withPred(Adult.class));
	
Sounds funky? We think it is ;-). 
	
We also provide Guava [functions](https://github.com/pyricau/FunkyJFunctional/blob/master/funkyjfunctional-guava/src/test/java/info/piwai/funkyjfunctional/guava/test/FuncTest.java):

	List<Integer> values = Arrays.asList(42, 69);
	class Price extends Func<Integer, String> {{ r = t + "$"; }}
	List<String> prices = transform(values, withFunc(Price.class));
	System.out.println(prices); // prints [42$, 69$]
	
And... [much more](http://pyricau.github.com/FunkyJFunctional/javadoc/snapshot/info/piwai/funkyjfunctional/Funky.html)!
    
# Maven funky artifacts

There are several artifacts that you may want to use, depending on your needs.

Here is a list:

* funkyjfunctional-guava for Funky Guava
* funkyjfunctional-wicket for Funky Wicket
* funkyjfunctional-java for Funky Java
* funkyjfunctional-swing for Funky Swing
* funkyjfunctional-core FunkyJFunctional's core, if you want to create your own Funky implementation.

The following configuration examples are for funkyjfunctional-guava. You should just switch the artifactId to use other implementations.

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
		<artifactId>funkyjfunctional-guava</artifactId>
		<version>0.3</version>
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
		<artifactId>funkyjfunctional-guava</artifactId>
		<version>0.4-SNAPSHOT</version>
	</dependency>

# Other funky ways 

* [jcurry](http://code.google.com/p/jcurry/) uses the awesome [projectlombok](http://projectlombok.org/) funkyness power.
* [lambdaj](http://code.google.com/p/lambdaj/) is a funky alernative to [Guava](http://code.google.com/p/guava-libraries/).
