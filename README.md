# Java-Jasmine

[![Build Status](https://travis-ci.org/bvkatwijk/Java-Jasmine.svg?branch=master)](https://travis-ci.org/bvkatwijk/Java-Jasmine)
[![Code Climate](https://img.shields.io/codeclimate/github/kabisaict/flow.svg)](https://codeclimate.com/github/bvkatwijk/Java-Jasmine)

## Summary

## Syntax

Extending the JasmineTest class provides convenience methods to implement tests the same way you would in JavaScript:

```
public class ExampleTest extends JasmineTest {{

	describe("A suite", () -> {
		it("should run its testcase", () -> {
			Assert.assertEquals(true, 1 == 1);
		});
	});

}}
```

## Usage

Extending the JasmineTest class also provides a custom JUnit runner which makes the class runnable as a JUnit test class:

![Eclipse JUnit Report](./screenshots/example-test-results.png)


