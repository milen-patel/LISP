# C-LISP Extended Interpreter
An extension of CLISP that supports functions, lambdas, currying, and more.
## Getting Started
To test out this program, clone the repository and build the project. The executable main is found in `src/mainPackage/Runner.java`. 

Whenever an input is given to the interpreter, it will be parsed into an S-Expression (read about S-Expressions here: https://en.wikipedia.org/wiki/S-expression). Assuming no structural errors, the instruction will then be parsed and evaluated. I take no credit for the parsing step of this program as that functionality was implemented in the `Parser.jar` included in the repository. My work on this project was to focus on the implementation of operators and functions.

S-Expressions are immutable and each S-Expression is either atomic or composite. An atomic S-Expression contains a single value such as `5`, `"hello"`, or `T`. By constrast, a composite S-Expression contains a head and tail, both of which point to other S-Expressions which themselves may be atomic or composite. This structure allows us to build out trees representing valid instructions to the interpreter. By traversion this (effeectively) binary tree, the interpreter can then recursively evaluate all input.
## Operator Support

### Arithmetic
The interpreter supports basic arithmetic expressions (addition, subtraction, multiplication, and division).

`(+ 5 2)` evaluates to `7` \
`(* 4 5)` evaluates to `20`

Each of the 4 operators takes in exactly two arguements: any more or fewer arguements will cause a runtime error. Additionally, the arguements need not be real numbers themselves. As long as each arguement evaluates to a number (integer or decimal), the interpreter will work.

`(+ (* 5 2) (+ 4 4))` evaluates to `18`

### Relational
The interpreter supports the 4 main relational operators: less than, greater than, less than or equal to, and greater than or equal to.

Examples:
```
(< 5 4)
(< 4 5)
(< 4.5 5)
(< 4 4.5)
(< (+ 4 2) (+ 4 5))
(< (+ (- 5 4) (+ 2 (+ 1 1))) 7)

(> 5 4)
(> 4 5)
(> 4.5 5)
(> 4 4.5)
(> (+ 4 2) (+ 4 5))

(<= 5 4)
(<= 4 5)
(<= 4.5 5)
(<= 4 4.5)
(<= (+ 4 2) (+ 4 5))

(>= 5 4)
(>= 4 5)
(>= 4.5 5)
(>= 4 4.5)
(>= (+ 4 2) (+ 4 5))
```

### Logical
The interpreter also supports boolean operators `and`, `or`. Each of these operators takes in any number of inputs and evalutes to either `T` or `Nil`. In this LISP, `Nil` represents false and any non-nil item represents True (including `T` itself).

**Examples of True**: T, (+ 1 4), 6
**Examples of False**: Nil, (< 4 2)

`and` returns true if and only if all of its arguements, when evaluated, are true. 

`or` returns trues if and only if at least one of its arguements, when evaluated, is true.

There is also a `not` operator which takes in a single arguement, evaluates it, and then returns `T` if and only if its evaluated arguement is `Nil`.

Examples:
```
(and)
(and 3 4 5)
(and 1 2)
(and 1)
(and nil nil nil nil)
(and 3 4 5 nil)
(and (+ 3 2) (+ 3 4))
(and (+ 3 2) (< 3 1))


(or)
(or 1)
(or nil)
(or nil nil 3 4 5)
(or 3 nil nil nil)
(or (< 3 1) (< 4 1) (< 3 5))
(or (< 3 1) (< 4 1) (+ 3 5))

(not 1)
(not nil)
(not (< 3 4))
(not (> 3 4))
```
### Lists

The LISP interpreter has support for lists which contain 0 or more elements. Internally, lists are represents as a linked list ending in a Nil atom.

The `list` operator will evaluate all of its arguements, and store them in a list and then will return the list. Some examples of the operator being used follow below. If you provide zero arguements to the operator, an empty list will be returned.

It is important to note that the arguements are evaluated before being inserted to the list. Thus, the first call to list in the code block below will return (7).

```
(list (+ 5 2))
(list 3 2 1)
(list 3 2 nil)
(list nil)
(list)
(list (+ 3 2) (+ 3 (- 5 2)) (< 4 5))
```

### Quote 

The quote operator simply returns its arguements unevaluated. For example, `(quote (+ 3 2))` returns `(+ 3 2)` and not `5`.

 Some example usage:
 ```
(quote 3)
(quote nil)
(quote (+ 3 4))
(quote (list 2 3 4 5))
(quote (+ 3))
 ```
 
 The last example in the above chunk is interesting because `(+ 3)` on its own is not a valid input. As mentioned earlier, arithmetic operators expect exactly two arguements. However, the quote operator doesn't check that its arguement is valid LISP, it simply returns the arguement as is. (If you run this exact arguement in C-LISP you will find the same results).

 ### Eval

 The eval operator can be seen as the opposite of the quote evaluator, in a certain sense. The eval operator will return the result of evaluating its evaluated arguement. What this means is that the arguement is evaluated twice, and the second evaluation's result is what is returned to the caller. Expected behaviour follows when passing in a quoted arguement.

 `(eval (quote (+ 3 2)))` returns `5` since the evaluation of the arguement `(quote (+ 3 2))` returns `(+ 3 2)` and then the evaluation of that returns `5`.

 Following up on the example of `(+ 3)` in the quote operator, it is interesting to note that `(eval (quote (+ 3)))` will cause a run time error since the first evaluation returns `(+ 3)` and then the second evaluation fails since the plus operator expects two arguements.

 ```
(eval (quote nil))
(eval (quote 3))
(eval (quote (+ 3 4)))
(eval (quote (+ (- 6 5) (+ 3 4))))
 ```

 ### Load 
 The load operator takes in a string path as its single arguement. The path must be to a file containing valid lisp commands. The interpreter will then sequentially evaluate each of the commands in the file.

 If you build this project locally, you can execute `(load “test.lisp”)` to execute the test file that has been provided.

### Cond
The cond operator behaves similarly to a switch statements in a more modern language. cond takes in one or more pairs of the form `(A B)`, each pair must be separated by a space. The `A` of each tuple will be evaluated until the first one returns true. When that happens, cond will return the corresponding evaluation result of `B` for the first pair whose `A` is true. If none of the tuples' `A`'s evaluate to true, then  `Nil` is returned.

Examples:
```
(cond (T 1))
(cond (nil 3) (T 6))
(cond (nil 4) (T 7))
(cond (nil 5) (T 9))
(cond (nil 4) (nil 3) (nil 3) (T 1) (nil 65))
(cond (nil 5) ((< 4 1) 6) (T 11))
(cond ((> 2 1) 6) ((< 4 1) 6) (T 9))
(cond ((< 10 2) 8) ((> 4 2) 8) (nil 10))
```

### Setq

After implementing the elementary operators but before implementing functions, it was necesary to have a convenient way to save and refer to values: the use of the setq operator.

Now, it is important to note that whenever the interpreter is executing a command, it is executing the command in some environment. Each environment has a scope where we can lookup and store values. The setq operator allows to replace a value in execution environment's scope. Environments can be nested (which will become useful in functions later), so when we lookup a value we check our current environment, if we have a value for that variable name then we can return that. Otherwise, we must recursively check the parent environment.

Thus, setq takes in a variable name and an arguement, evalutes the arguement, and then assigns the resulting value to the variable name in this environment.

A trivial example would be as follows:
```
(setq A 5)
(setq B (+ A 6)))
(+ A B)
```
The first line maps A to 5 in our current environment's scope. The second line is going to assign B a value but while evaluating the arguement, A must be looked up and is evaluted to 5 as a consequence of the previous line. If we had not made that previous call, the second line would have crashed the program since A would not have been declared. The final line then returns 16.

### Lambdas
The interpreter supports more than basic operations in that there exists functions in the lisp. A function takes in zero or more arguements which are all evaluated left to right prior to any execution of the lambda body. The function body is a space separated list of lisp commands (there must be atleast one). Each of the commands will be evaluated in order and then the return value of applying a lambda will be the result of the last expression evaluated in the body.

When you apply a lambda (i.e. call a function), a new scope is created as a child of the caller's scope. All of the arguements are defined in the child scope and mapped to the evaluated values of the formal parameters from the lambda application.

Lambdas may modify environment state using setq and are also capable of returning lambdas themselves.

Here are some examples of defining lambdas and calling them at the same time. There is technically no need to save a function before calling it, and the following snippet demonstrates such behaviour. (All of the lambda applications below will return `42`.)
```
((lambda () (+ 41 (+ 0 1))))
((lambda (X) (* (+ 1 1) X)) 21)
((lambda (X Y) (+ X (+ Y 0))) 40 2)
```

In many cases, we will want to reuse lambdas and so we hope for a convient way to save lambdas instead of having to redefine them every time we want to call one. Fortunately, the setq operator discussed above can be used to save lambdas.

Below is an example of such behaviour. Notice that there is no application of the lambda, we are just defining what it is and saving it as a reference.
```
(setq LISTDERIVEDSAFE (lambda (Dist Dur Exh) 
	(or 
		(and (>= Dist 13) (<= Dur 30) (<= Exh 30)) 
		(and (>= Dist 6) (<= Dur 30) (<= Exh 10))
		(and (>= Dist 27) (<= Dur 30) (<= Exh 50))
		(and (>= Dist 13) (<= Dur 15) (<= Exh 50))
		(and (>= Dist 13) (<= Dur 15) (<= Exh 50))
		(and (>= Dist 13) (<= Dur 120) (<= Exh 10))
		(and (>= Dist 27) (<= Dur 120) (<= Exh 30))
		(and (>= Dist 6) (<= Dur 15) (<= Exh 30))
	)
))
```

### Funcall
If we safe a lambda using setq, that brings up the question of how we then call it? The correct answer is to use the funcall operator.

The funcall operator takes in a function name as its first arguement (which must have been defined using setq as explained above). The resulting arguements must then correspond to the number (and type) of arguements expected by the function. If the number of arguements provided does not match the number of arguements expecvted, a runtime error will occur. Below is a trivial example (which returns 4):

```
(setq f (lambda (X Y) (+ X Y)))
(funcall f 1 3)
```

### Function versus Lambda

The interpeter also supports functions which differ from lambdas. Lambda applications create a new child of the callers environment whereas function applications create a new child of the function definer's environment. Thus, when we define a function we also encapsulate the environment in which it was defined. This will support higher order functions which is discussed in the next section with an example.

### Scoping Concerns

Look at the code below. We define a *global* variable x and then also define x as a parameter of the lambda. In the lambda body when we execute (* x y) which version of x will the interpreter evaluate?

Recall above we said that every time we call a lambda, a new child environment is created and the evaluated formal parameters are stored in that environment.

So in this case, the parent scope maps x to 5, but the child scope maps x to the formal arguement given. So, in the body of the lambda, when we dereference x, we check our current scope (the child scope) and see that x is mapped to the formal parameter so we stop there. If our parameter names didn't contain and x and we still have the same body, then when we derefenced x, there would be no x in the child environment and so we would check the parent environment where x is mapped to 5 and then we would return.

It is also important to note that whenver a function execution ends, our scope goes up one level which is back to the parent scope in this case.

Looking at the same example below, we see that our lambda returns a lambda, so the child scope that is created in a call to timesGenerator is actually preserved and that enables higher order functions!
```
(setq x 5)
(setq timesGenerator
    (lambda (x)
  	(function
                (lambda (y) (* x y))
            )
    )
)
```

Consider the following commands executed after the above code:
```
(setq twice (funcall timesGenerator 2))
(funcall twice 5)
```
What is the result of the last line in this case?

Before we defined timesGenerator, we have our toplevel scope which contains {x -> 5, timesGenerator -> lambda}.

The call `(setq twice (funcall timesGenerator 2))` makes a funcall which as we said earlier makes a new scope.

{x -> 5, timesGenerator -> lambda}
{x -> 2} (Child scope associated with funcall)

So when we execute `(funcall twice 5)`, we execute timesGenerator again but the child environment has a reference of x to 2 which is discovered before the parent environemtn so x evaluates to 2 and then the overall funcall consequently returns 10.

### Currying
The intepreter also supports currying as which occurs in Standard ML. This feature, along with the function versus lambda discussion, is an extension of C-LISP and is unique to this interpreter.

An example is shown below:
```
(setq product3 (lambda (x y z) (* x (* y z))))
(setq product2 (curry product3 1))
(setq identity (curry product2 1))
```

### Academic Honesty
This project is used in certain offerings of UNC's undergrauate/graduate course on programming language concepts. This repository is strictly to demonstrate my work and any student enrolled in this course should not copy code from or clone this repository to avoid potential honor court issues.