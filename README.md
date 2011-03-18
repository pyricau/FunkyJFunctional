There is no such thing as functional programming is Java, but sometimes we emulate it using anonymous classes.

FunkyJFunctional provides a new way to do functional programming in Java. It integrates with [Guava](http://code.google.com/p/guava-libraries/), and uses some Java syntaxic sugar : method local class declarations and init blocks.

With Guava:

	List<Integer> values = Arrays.asList(16, 21);
	Predicate<Integer> adult = new Predicate<Integer>() {
		@Override
		public boolean apply(Integer input) {
			return input > 18;
		}
	};
	Iterable<Integer> adults = Iterables.filter(values, adult);
	
With FunkyJFunctional our [predicates](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/PredTest.java) are like this:	
	
	List<Integer> values = Arrays.asList(16, 21);
	class Adult extends P<Integer> {{r = t > 18;}};
	Iterable<Integer> adults = Iterables.filter(values, Pred.with(Adult.class));
	
... with static imports it reads like this
	
	Iterable<Integer> adults = filter(values, with(Adult.class));
	
We also provide  [functions](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/FuncTest.java) :

	List<Integer> values = Arrays.asList(42, 69);
	class Price extends F<Integer, String> {{t = f+"$";}};
	List<String> prices = Lists.transform(values, Func.with(Price.class));

... with static imports it reads like this

	List<String> prices = transform(values, with(Price.class));
	
And [comparators](https://github.com/pyricau/FunkyJFunctional/blob/master/src/test/java/info/piwai/funkyjfunctional/CompTest.java) :

	Person john = new Person("John");
	Person joe = new Person("Joe");
	
	List<Person> persons = Arrays.asList(john, joe);
	class Sort extends Comp<Person> {{r = t1.getName().compareTo(t2.getName());}};
	List<Person> sortedPersons = Ordering.from(C.with(Sort.class)).sortedCopy(persons);;
	
... with static imports it reads like this
	
	Ordering<Person> ordering = from(with(Sort.class)).sortedCopy(persons);
	
Sounds funky ? I think it is ;-). 