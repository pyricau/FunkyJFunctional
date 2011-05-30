# Are you Funky?

There is no such thing as functional programming in Java, but sometimes we emulate it using anonymous classes.

FunkyJFunctional provides a **new** way to do **functional** programming in Java, using some Java syntactic sugar: **method local class** declarations and **init blocks**.

FunkyJFunctional **integrates** with many different **frameworks**. If not already available, feel free to implement a new funky module for the framework you love!

A few examples with different frameworks:

* Classic Java, with a funky [Runnable](http://download.oracle.com/javase/6/docs/api/java/lang/Runnable.html)

``` java
public void sayHelloInExecutor(ExecutorService executor) {
	class Hello {{ System.out.println("Hello Funky World"); }}
	executor.execute(withRun(Hello.class));
}
```

* [Guava](http://code.google.com/p/guava-libraries/), with a funky [Predicate](http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Predicate.html) (Guava is the former google collections)

``` java
public Iterable<User> filterMinors(Iterable<User> users) {
	class Minor extends Pred<User> {{ out = in.getAge() < 18; }}
	return filter(users, withPred(Minor.class));
}
```

* [FEST-Assert](http://docs.codehaus.org/display/FEST/Fluent+Assertions+Module), with a funky [Condition](http://docs.codehaus.org/display/FEST/Extending+FEST-Assert+with+Custom+Conditions)

``` java
@Test
public void getMajorUsers_returns_no_minor_User() {
	List<User> majorUsers = userService.getMajorUsers();
	class Minor extends Cond<User> {{ out = in.getAge() < 18; }}
	assertThat(majorUsers).noneIs(Minor.class);
}
```

* [Wicket](http://wicket.apache.org/), with a funky  [AbstractReadOnlyModel](http://wicket.apache.org/apidocs/1.4/org/apache/wicket/model/AbstractReadOnlyModel.html)

``` java
class FortyTwo extends AROM<String> {{ out = "42"; }}
AbstractReadOnlyModel<String> readOnlyModel = withAROM(FortyTwo.class);
```

* [Swing](http://java.sun.com/javase/technologies/desktop/), with a funky  [ActionListener](http://download.oracle.com/javase/6/docs/api/java/awt/event/ActionListener.html)

``` java
class BtonClick extends ActL {{ doSomething(e); }}
jButton.addActionListener(withActL(BtonClick.class));
```

## Informations

* It's pure Java, no magic involved! See [How it works](https://github.com/pyricau/FunkyJFunctional/wiki/How-it-works) for details.

## Help

[![Javadoc](https://github.com/pyricau/FunkyJFunctional/raw/master/javadoc_screenshot.png)](http://pyricau.github.com/FunkyJFunctional/javadoc/releases/1.0/index.html?info/piwai/funkyjfunctional/Funky.html)
[![Google Group](http://global742.org/sites/default/files/google-groups-logo.png)](https://groups.google.com/group/funkyjfunctional)

Looking for the documentation? Have a look at the [Funky javadoc](http://pyricau.github.com/FunkyJFunctional/javadoc/releases/1.0/index.html?info/piwai/funkyjfunctional/Funky.html)!

Any question? Please ask them on the dedicated [Google Group](https://groups.google.com/group/funkyjfunctional).

The latest release is the 1.0 version, see the [release notes](https://github.com/pyricau/FunkyJFunctional/wiki/Release-Notes) for more information.

# Quality

FunkyJFunctional has continuous integration thanks to **CloudBees** DEV@Cloud [free plan](http://www.cloudbees.com/foss/foss-dev.cb) for [FOSS](http://en.wikipedia.org/wiki/Free_and_open_source_software) projects.

[![Built on CloudBees](http://static-www.cloudbees.com/images/badges/CBbadge_builton_125.png)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)

## Build time trend & Test Result Trend
[![Build Trend](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/buildTimeGraph/png?width=400&height=200)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)
[![Test result trend](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/test/trend?width=400&height=200)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)

Note that **all modules** have **100% code coverage**.


# Detailed example

The classical way to use a Guava [Predicate](http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Predicate.html):

``` java
List<Integer> values = Arrays.asList(16, 21);
Predicate<Integer> minorPredicate = new Predicate<Integer>() {
	@Override
	public boolean apply(Integer input) {
		return input < 18;
	}
};
Iterable<Integer> minors = Iterables.filter(values, minorPredicate);
System.out.println(minors); // prints [16]
```
	
With FunkyJFunctional our predicate is [much shorter](https://github.com/pyricau/FunkyJFunctional/blob/master/funkyjfunctional-demo/src/test/java/info/piwai/funkyjfunctional/demo/guava/PredDemo.java):

``` java
List<Integer> values = Arrays.asList(16, 21);
class Minor extends Pred<Integer> {{ out = in < 18; }}
Iterable<Integer> minors = Iterables.filter(values, FunkyGuava.withPred(Minor.class));
System.out.println(minors); // prints [16]
```

With static imports, it's even shorter:

``` java
import static info.piwai.funkyjfunctional.FunkyGuava.*;
import static com.google.common.collect.Iterables.*;
import static java.util.Arrays.*;
// [...]
class Minor extends Pred<Integer> {{ out = in < 18; }}
Iterable<Integer> minors = filter(asList(16, 21), withPred(Minor.class));
System.out.println(minors); // prints [16]
```

And... [much more](http://pyricau.github.com/FunkyJFunctional/javadoc/releases/1.0/index.html?info/piwai/funkyjfunctional/Funky.html)!

Sounds funky? We think it is ;-). 
	
# Maven funky artifacts

There are several artifacts that you may want to use, depending on your needs.

Here is a list:

* **funkyjfunctional-guava** for Funky Guava
* **funkyjfunctional-wicket** for Funky Wicket
* **funkyjfunctional-java** for Funky Java
* **funkyjfunctional-swing** for Funky Swing
* **funkyjfunctional-fest-assert** for Funky FEST-Assert
* **funkyjfunctional-core** FunkyJFunctional's core, if you want to create your own Funky implementation.

Don't forget to read the [release notes](https://github.com/pyricau/FunkyJFunctional/wiki/Release-Notes)!

The following configuration examples are for **funkyjfunctional-guava**. You should just switch the **artifactId** to use other modules.

## FJF-Guava releases, for the serious funky guys

You will need the following repository in your Maven project:

``` xml
<repositories>
	<repository>
		<id>funkyjfunctional-releases</id>
		<url>https://github.com/pyricau/FunkyJFunctional/raw/master/releases</url>
	</repository>
</repositories>
```
	
As well as the following dependency:

``` xml
<dependency>
	<groupId>info.piwai.funkyjfunctional</groupId>
	<artifactId>funkyjfunctional-guava</artifactId>
	<version>1.0</version>
</dependency>
```
	
## FJF-Guava snapshots, for Chuck Norris and his funky friends

You will need the following repository in your Maven project:

``` xml
<repositories>
	<repository>
		<id>funkyjfunctional-snapshots</id>
		<url>https://github.com/pyricau/FunkyJFunctional/raw/master/snapshots</url>
	</repository>
</repositories>
```

As well as the following dependency:

``` xml
<dependency>
	<groupId>info.piwai.funkyjfunctional</groupId>
	<artifactId>funkyjfunctional-guava</artifactId>
	<version>1.1-SNAPSHOT</version>
</dependency>
```

#License

* [Apache License, v2.0](http://www.apache.org/licenses/LICENSE-2.0.html)


# Other funky ways 

* [jcurry](http://code.google.com/p/jcurry/) uses the awesome [projectlombok](http://projectlombok.org/) funkyness power.
* [lambdaj](http://code.google.com/p/lambdaj/) is a funky alernative to [Guava](http://code.google.com/p/guava-libraries/).