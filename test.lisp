( SETQ A 29)



( SETQ B (+ A 13))




B



((lambda () (+ 41 (+ 0 1))))

((lambda (X) (* (+ 1 1) X)) 21)

((lambda (X Y) (+ X (+ Y 0))) 40 2)

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

(setq x 5)

(setq timesGenerator
     (lambda (x)
  	(function
                (lambda (y) (* x y))
            )
    )
)

(setq twice (funcall timesGenerator 2))

(setq product3 (lambda (x y z) (* x (* y z))))
(setq product2 (curry product3 1))
(setq identity (curry product2 1))

(setq isList ( lambda (X) 
	(cond
		   ((eq X nil) nil)
		   (T (funcall isListHelper X))
	)
))

(setq isListHelper ( lambda (X) 
	(cond
		   ((atom X) (eq X nil))
		   (T (funcall isListHelper (cdr X)))
	)
))

(setq toStringAsSExpression 
	  (lambda (X)
			(cond 
				((atom X) (write-to-string X))
				(T (concatenate (quote String) "(" (funcall toString (car X)) " . " (funcall toString (cdr X)) ")"))
			)
		)
)

(setq toStringAsList
	  (lambda (X) (concatenate (quote String) "(" (funcall toStringAsListHelper X 1) ")"))
)

(setq toStringAsListHelper (lambda (X M)
	(cond ((atom (cdr X)) (concatenate (quote String) (cond ((eq M 1) "") (T " ")) (funcall toString (car X))))
			(T (concatenate (quote String) (cond ((eq M 1) "") (T " ")) (funcall toString (car X)) (funcall toStringAsListHelper (cdr x) 0)))
		  )
							 ))
(setq toString (lambda (X) (cond ((funcall isList X) (funcall toStringAsList X)) (T (funcall toStringAsSExpression X)))))
(setq numAtoms
	(lambda(X)
		(cond
			((atom X) 1)
			(T (+ (funcall numAtoms (car X)) (funcall numAtoms (cdr X))))
		)
	)
)

(setq filterList
	  (lambda (Handler L)
		(cond
		  ((eq L nil) nil)
		  (T (cons
			   (funcall Handler (car L))
			   (funcall filterList Handler (cdr L))
			   )
			 )
		  )
		)
	  )
(setq f1Handler (lambda (X) (atom X)))
(setq f1Clone (curry filterList f1Handler))
(setq f2Handler (lambda (X) (not X)))
(setq f2Clone (curry filterList f2Handler))
