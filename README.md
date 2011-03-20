# Are you Funky?

There is no such thing as functional programming is Java, but sometimes we emulate it using anonymous classes.

FunkyJFunctional provides a new way to do functional programming in Java. It integrates with [Guava](http://code.google.com/p/guava-libraries/) (the former google collections), and uses some Java syntaxic sugar: method local class declarations and init blocks.

In short, two syntax examples:

* A funky Runnable: ```class Hello {{ System.out.println("Hello Funky World"); }}```

* A funky Predicate: ```class Adult extends Pred<Integer> {{ r = t > 18; }}```

Looking for the documentation? Have a look at the <a href="http://pyricau.github.com/FunkyJFunctional/javadoc/snapshot/info/piwai/funkyjfunctional/Funky.html">Funky javadoc</a>!

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
	
... with static imports:
	
	Iterable<Integer> adults = filter(values, withPred(Adult.class));
	
Sounds funky? We think it is ;-). 
	
We also provide  [functions](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/FuncTest.java):

	List<Integer> values = Arrays.asList(42, 69);
	class Price extends Func<Integer, String> {{ t = f + "$"; }}
	List<String> prices = Lists.transform(values, Funky.withFunc(Price.class));

... with static imports:

	List<String> prices = transform(values, withFunc(Price.class));
	
And [comparators](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/CompTest.java):

	Person john = new Person("John");
	Person joe = new Person("Joe");
	
	List<Person> persons = Arrays.asList(john, joe);
	class Sort extends Comp<Person> {{ r = t1.getName().compareTo(t2.getName()); }}
	List<Person> sortedPersons = Ordering.from(Funky.withComp(Sort.class)).sortedCopy(persons);;
	
... with static imports:
	
	Ordering<Person> ordering = from(withComp(Sort.class)).sortedCopy(persons);
	
And [runnables](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/RunTest.java):

    class Hello {{ System.out.println("Hello World"); }}
    Runnable runnable = Funky.withRun(Hello.class);
    runnable.run();
    
And [callables](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/apitest/CallTest.java):

    class FortyTwo extends Call<Integer> {{ r = 42; }}
    Callable<Integer> callable = withCall(FortyTwo.class);
    Integer result = callable.call();
    
# Maven funky artifact

You will need one of the following repositories in your Maven project:
	<repositories>
		<!-- For the serious funky guys -->
		<repository>
			<id>funkyjfunctional-releases</id>
			<url>https://github.com/pyricau/FunkyJFunctional/raw/master/releases</url>
		</repository>
		<!-- For Chuck Norris and his funky friends -->
		<repository>
			<id>funkyjfunctional-snapshots</id>
			<url>https://github.com/pyricau/FunkyJFunctional/raw/master/snapshots</url>
		</repository>
	</repositories>
As well as the following dependency:
	<dependency>
		<groupId>info.piwai.funkyjfunctional</groupId>
		<artifactId>funkyjfunctional</artifactId>
		<version>0.2</version>
	</dependency>	

# Other funky ways 

* [jcurry](http://code.google.com/p/jcurry/) uses the awesome [projectlombok](http://projectlombok.org/) funkyness power.
* [lambdaj](http://code.google.com/p/lambdaj/) is a funky alernative to [Guava](http://code.google.com/p/guava-libraries/).
