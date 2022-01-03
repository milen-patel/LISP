# C-LISP Extended Interpreter
An extension of CLISP that supports functions, lambdas, currying, and more.

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

+ Demo Test File
+ Explain project structure
+ Give credit for jar
+ How to setup and run
+ Prohibit Copying
+ Add code snippings
+ Explain SExpr imutability, atomic vs composite