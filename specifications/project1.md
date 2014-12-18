Project 1 - Inverted Index
==========================

For this project, you will write a Java program that recursively processes all text files in a directory and build an inverted index to store a mapping from words to the documents (and positions within those documents) where the words were found. For example, suppose we have the following two documents:

```
catstory.txt: The cat ate the bird.
dogstory.txt: The fast dog ran.

```
The inverted index will contain the following mapping:


```
the
"catstory.txt", 1, 4
"dogstory.txt", 1

cat
"catstory.txt", 2

ate
"catstory.txt", 3

bird
"catstory.txt", 5

fast
"dogstory.txt", 2

dog
"dogstory.txt", 3

ran
"dogstory.txt", 4
```

