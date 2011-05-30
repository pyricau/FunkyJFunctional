# Are you Funky?

There is no such thing as functional programming in Java, but sometimes we emulate it using anonymous classes.

**FunkyJFunctional** (aka **FJF**) provides a **new** way to do **functional** programming in Java, using some Java syntactic sugar: **method local class** declarations and **init blocks**.

FJF **integrates** with many different **frameworks**. If not already available, feel free to implement a new funky module for the framework you love!

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

Sounds funky? We think it is ;-). 

## Informations

* It's **pure Java**, no magic involved. See [How it works](https://github.com/pyricau/FunkyJFunctional/wiki/How-it-works) for details.
* **All modules** have **100% code coverage**
* To use FJF, [Download](https://github.com/pyricau/FunkyJFunctional/wiki/Maven) the **Maven** artifacts.
* See the [Detailed example](https://github.com/pyricau/FunkyJFunctional/wiki/Detailed-example) to get into details.
* The latest release is the **1.0** version, see the [release notes](https://github.com/pyricau/FunkyJFunctional/wiki/Release-Notes) for more information.
* Licensed under the [Apache License, v2.0](http://www.apache.org/licenses/LICENSE-2.0.html)


## Help

[![Javadoc](https://github.com/pyricau/FunkyJFunctional/raw/master/javadoc_screenshot.png)](http://pyricau.github.com/FunkyJFunctional/javadoc/releases/1.0/index.html?info/piwai/funkyjfunctional/Funky.html)
[![Google Group](http://global742.org/sites/default/files/google-groups-logo.png)](https://groups.google.com/group/funkyjfunctional)

Looking for the documentation? Have a look at the [Funky javadoc](http://pyricau.github.com/FunkyJFunctional/javadoc/releases/1.0/index.html?info/piwai/funkyjfunctional/Funky.html)!

Any question? Please ask them on the dedicated [Google Group](https://groups.google.com/group/funkyjfunctional).

# Continuous Integration

FJF has continuous integration thanks to **CloudBees** DEV@Cloud [free plan](http://www.cloudbees.com/foss/foss-dev.cb) for [FOSS](http://en.wikipedia.org/wiki/Free_and_open_source_software) projects.

[![Built on CloudBees](http://static-www.cloudbees.com/images/badges/CBbadge_builton_125.png)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)

## Build time trend & Test Result Trend
[![Build Trend](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/buildTimeGraph/png?width=400&height=200)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)
[![Test result trend](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/test/trend?width=400&height=200)](https://pyricau.ci.cloudbees.com/job/FunkyJFunctional-CI/)