# C-LISP Extended Interpreter
An extension of CLISP that supports functions, lambdas, currying, and more.

## Operator Support

### Arithmetic
The interpreter supports basic arithmetic expressions (addition, subtraction, multiplication, and division).

`(+ 5 2)` evaluates to `7` \
`(* 4 5)` evaluates to `20`

Additionally, nested arithmetic is supported in that operator arguements don't have to be literals but must evaluate to real numbers.

`(+ (* 5 2) (+ 4 4))` evaluates to `18`

### Relational
The interpreter supports the 4 main relational operators: less than, greater than, less than or equal to, and greater than or equal to.

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

+ Demo Test File
+ Explain project structure
+ Give credit for jar
+ Prohibit Copying
+ Add code snippings